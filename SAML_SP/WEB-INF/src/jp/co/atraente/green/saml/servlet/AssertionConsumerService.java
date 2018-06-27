package jp.co.atraente.green.saml.servlet;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.zip.Inflater;
import java.util.zip.InflaterOutputStream;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.commons.codec.binary.Base64;

import jp.co.atraente.green.saml.common.Utils;

public class AssertionConsumerService implements Servlet {

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

		System.out.println("start AssertionConsumerService >>");

		// -------------------------------------------------
		// 入力パラメータチェック
		// -------------------------------------------------
		String samlResponse = (String) req.getParameter("SAMLResponse");
		String relayState = (String) req.getParameter("RelayState");
		System.out.println("SAMLResponse=" + samlResponse);
		System.out.println("RelayState=" + relayState);
		if (Utils.isEmpty(samlResponse)) {

		} else {
			ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
			byteArrayOutputStream.write(Base64.decodeBase64(URLDecoder.decode(samlResponse, "UTF-8").getBytes()));
			byteArrayOutputStream.close();
			Inflater inflater = new Inflater();
			InflaterOutputStream inflaterOutputStream = new InflaterOutputStream(byteArrayOutputStream, inflater);

			byte[] b = new byte[byteArrayOutputStream.size()];
			inflaterOutputStream.write(b);
			inflaterOutputStream.close();
			// request に保存
			req.setAttribute("res", new String(b));

			System.out.println("<< end AssertionConsumerService");
			req.getRequestDispatcher("/jsp/acs.jsp").forward(req, res);

		}
	}
}
