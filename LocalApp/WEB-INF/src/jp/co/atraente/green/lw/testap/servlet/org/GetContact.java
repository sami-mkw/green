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
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import jp.co.atraente.green.lw.testap.common.Constants;

public class GetContact implements Servlet {

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
		System.out.println("start GetContact >>");

		String targetUrl = Constants.GET_DOMAIN_CONTACT;
		// -------------------------------------------------
		// 送付するパラメータの準備
		// -------------------------------------------------
		// HashMap<String, Object> param = new HashMap<String, Object>();
		// param.put("account", (String) req.getParameter("account"));
		// StringEntity entity = new StringEntity(JSONObject.toJSONString(param));
		// // Print Log
		// System.out.println("RequestBody:" + JSONObject.toJSONString(param));
		//
		// StringEntity entity = new StringEntity(
		// "account=" + URLEncoder.encode((String) req.getParameter("account"),
		// "UTF-8"));
		// System.out.println(URLEncoder.encode("account:" + (String)
		// req.getParameter("account"), "UTF-8"));

		// String reqBody = "account=" + URLEncoder.encode((String)
		// req.getParameter("account"), "UTF-8");
		String reqBody = "account=" + (String) req.getParameter("account");
		StringEntity entity = new StringEntity(reqBody);

		// -------------------------------------------------
		// HTTPクライアントの準備
		// -------------------------------------------------
		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpPost request = new HttpPost(targetUrl);
		request.setEntity(entity);

		// Utils.setApiReqHeader4POST(request);
		request.setHeader("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
//		request.setHeader("Connection", "KeepAlive");
		request.setHeader("consumerKey", Constants.SERVER_API_CONSUMER_KEY);
		request.setHeader("Authorization", "Bearer " + Constants.getServerToken());

		// Print Logs
		System.out.println("RequestLine:" + request.getRequestLine());
		System.out.println("Content-Type:" + request.getHeaders("Content-Type")[0].getValue());
		System.out.println("RequestBody:" + reqBody);

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
		req.setAttribute("contact", body);
		req.getRequestDispatcher("/jsp/org.jsp").forward(req, res);

	}
}
