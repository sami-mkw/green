package jp.co.atraente.green.lw.testap.servlet.bot;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import com.nimbusds.jose.util.Base64;

import jp.co.atraente.green.lw.testap.common.Utils;
import jp.co.atraente.green.lw.testap.exception.AppException;

public class Sign implements Servlet {

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
		System.out.println(">> start Sign");
		try {
			// -------------------------------------------------
			// パラメータの準備
			// -------------------------------------------------
			String base = req.getParameter("base");
			String apiId = req.getParameter("apiId");
			if (Utils.isEmpty(base)) {
				throw new AppException("Base String is empty.");
			}
			if (Utils.isEmpty(apiId)) {
				throw new AppException("apiId is empty.");
			}

			// HMAC-SHA256
			SecretKeySpec key = new SecretKeySpec(apiId.getBytes(), "HmacSHA256");
			Mac mac = Mac.getInstance("HmacSHA256");
			mac.init(key);
			// Sign
			byte[] tmpSrc = mac.doFinal(base.getBytes("UTF-8"));
			String signature = Base64.encode(tmpSrc).toString();
			// Print Log
			System.out.println("Base String:" + base);
			System.out.println("API ID:" + apiId);
			System.out.println("Signature:" + signature);

			req.setAttribute("signature", signature);
			// 元の画面に戻す。
			req.getRequestDispatcher("/jsp/util.jsp").forward(req, res);
		} catch (AppException e) {
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} finally {
			System.out.println("<< end Sign");
		}
	}
}
