package jp.co.atraente.green.lw.testap.servlet.oauth;

import java.io.IOException;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import jp.co.atraente.green.lw.testap.common.Constants;

public class GetCode implements Servlet {

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

		System.out.println("start GetCode >>");

		// =====================================================
		// AuthorizationCode要求
		// =====================================================
		String url = Constants.AUTHORIZATION_ENDPOINT + "?client_id=" + Constants.SERVICE_API_CONSUMER_KEY
				+ "&redirect_uri=" + Constants.REDIRECT_URI + "&domain=red%2ewmjsales%2exyz";

		// -------------------------------------------------
		// 送付するパラメータの準備
		// -------------------------------------------------
		HttpServletResponse tmpRes = (HttpServletResponse) res;

		tmpRes.setHeader("consumerKey", Constants.SERVICE_API_CONSUMER_KEY);
		// Print Log
		System.out.println("RedirectTo:" + url);

		System.out.println("<< end GetCode");
		tmpRes.sendRedirect(url);

	}
}
