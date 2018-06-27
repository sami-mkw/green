package jp.co.atraente.green.saml.servlet;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLDecoder;
import java.util.zip.DataFormatException;
import java.util.zip.Inflater;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.ssl.Base64;

import jp.co.atraente.green.saml.common.Utils;

public class RcvAuthnReq implements Servlet {

	@Override
	public void destroy() {

	}

	@Override
	public ServletConfig getServletConfig() {
		return null;
	}

	@Override
	public String getServletInfo() {
		return null;
	}

	@Override
	public void init(ServletConfig arg0) throws ServletException {
	}

	@Override
	public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {

		System.out.println("start RcvAuthnReq >>");

		HttpServletRequest tmpReq = (HttpServletRequest) req;
		HttpSession session = tmpReq.getSession(true);

		String authnReq = "";

		// -------------------------------------------------
		// AuthnRequest Base64デコード & 圧縮解除
		// -------------------------------------------------
		if (Utils.isEmpty((String) req.getParameter("SAMLRequest"))) {
			authnReq = (String) session.getAttribute("authnReq");
			if (Utils.isEmpty(authnReq)) {
				req.getRequestDispatcher("/jsp/error.jsp").forward(req, res);
			}
		} else {
			String samlRequest = URLDecoder.decode((String) req.getParameter("SAMLRequest"), "UTF-8");
			byte[] authnRequest = Base64.decodeBase64(samlRequest.getBytes());
			String out = "";
			Inflater decompresser = new Inflater();
			try (InputStream bis = new ByteArrayInputStream(authnRequest)) {
				final byte[] inpBuf = new byte[2048];
				final byte[] outBuf = new byte[2048];

				int rd;
				do {
					rd = bis.read(inpBuf);
					if (rd > 0) {
						decompresser.setInput(inpBuf, 0, rd);
					}
					while (!decompresser.finished()) {
						int siz = decompresser.inflate(outBuf);
						if (siz > 0) {
							// 実質上、ASCII文字のみと想定している.
							// (マルチバイト文字列の分割位置を考慮していない)
							out = out + new String(outBuf, 0, siz, "UTF-8");
						} else {
							break;
						}
					}
				} while (rd > 0);
				authnReq = out;
				session.setAttribute("authnReq", authnReq);
				System.out.println("authnReq: " + authnReq);
			} catch (DataFormatException e) {
				e.printStackTrace();

			} finally {
				decompresser.end();
			}
		}
		// -------------------------------------------------
		// 入力パラメータチェック
		// -------------------------------------------------
		String relayState = (String) req.getParameter("RelayState");
		if (Utils.isEmpty(relayState)) {
			relayState = "";
		}
		// session に保存
		session.setAttribute("relayState", relayState);

		// -------------------------------------------------
		// パラメータセット
		// -------------------------------------------------
		req.setAttribute("relayState", relayState);
		req.setAttribute("authnReq", authnReq);

		req.getRequestDispatcher("/jsp/login.jsp").forward(req, res);

		System.out.println("<< end RcvAuthnReq");
	}
}
