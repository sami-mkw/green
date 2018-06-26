package jp.co.atraente.green.lw.testap.servlet.org;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import jp.co.atraente.green.lw.testap.common.Constants;
import jp.co.atraente.green.lw.testap.common.Utils;
import jp.co.atraente.green.lw.testap.exception.AppException;

public class GetOrg implements Servlet {

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
		System.out.println("start GetOrg >>");
		try {
			// -------------------------------------------------
			// パラメータの準備
			// -------------------------------------------------
			if (Utils.isEmpty(req.getParameter("exKey"))) {
				throw new AppException("exKey is empty.");
			}
			String targetUrl = Constants.ORG_URL_PREFIX + req.getParameter("exKey");

			// -------------------------------------------------
			// HTTPクライアントの準備
			// -------------------------------------------------
			CloseableHttpClient httpclient = HttpClients.createDefault();
			HttpGet request = new HttpGet(targetUrl);
			Utils.setApiReqHeader4GET(request);

			// -------------------------------------------------
			// リクエストの送付＆レスポンスの取得
			// -------------------------------------------------
			CloseableHttpResponse response = httpclient.execute(request);
			InputStream is = response.getEntity().getContent();
			String body = IOUtils.toString(is, Charset.defaultCharset());

			// Print Log
			String status = response.getStatusLine().toString();
			String contentType = response.getHeaders("Content-Type")[0].getValue();
			System.out.println("StatusLine:" + status);
			System.out.println("Content-Type:" + contentType);
			System.out.println("ResponseBody:" + body);
			// 元の画面に戻す。
			req.setAttribute("result", body);
			req.getRequestDispatcher("/jsp/org.jsp").forward(req, res);
		} catch (AppException e) {
			e.printStackTrace();
		} finally {
			System.out.println("<< end GetOrg");
		}

	}
}
