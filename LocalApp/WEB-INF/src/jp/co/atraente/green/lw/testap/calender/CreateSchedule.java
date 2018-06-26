package jp.co.atraente.green.lw.testap.calender;

import java.io.IOException;
import java.io.InputStream;

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
import jp.co.atraente.green.lw.testap.common.Utils;
import jp.co.atraente.green.lw.testap.exception.AppException;

public class CreateSchedule implements Servlet {

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

		System.out.println(">> start CreateSchedule");

		req.setCharacterEncoding(Constants.UTF8);

		String url = "";
		try {
			// -------------------------------------------------
			// パラメータの準備
			// -------------------------------------------------
			if (Utils.isEmpty(req.getParameter("obj"))) {
				throw new AppException("JSON is empty.");
			}
			if (Utils.isEmpty(req.getParameter("targetUrl"))) {
				throw new AppException("targetUrl is empty.");
			}
			url = (String) req.getParameter("targetUrl");

			String tmp = (String) req.getParameter("obj");
			// JSONObject obj = JSONObjectUtils.parse((String) req.getParameter("obj"));
			// String tmp = new
			// String(JSONObject.toJSONString(obj).getBytes(Constants.UTF8), Constants.UTF8);
			StringEntity entity = new StringEntity(tmp);

			System.out.println("RequestBody:" + tmp);

			// -------------------------------------------------
			// HTTPクライアントの準備
			// -------------------------------------------------
			CloseableHttpClient httpclient = HttpClients.createDefault();
			HttpPost request = new HttpPost(url);
			request.setEntity(entity);
			request.setHeader("Content-Type", Constants.CONTENT_TYPE_JSON);
			request.setHeader("consumerKey", Constants.SERVICE_API_CONSUMER_KEY);
			request.setHeader("Authorization", "Bearer " + Constants.getServiceToken());

			// -------------------------------------------------
			// リクエストの送付＆レスポンスの取得
			// -------------------------------------------------
			CloseableHttpResponse response = httpclient.execute(request);
			InputStream is = response.getEntity().getContent();
			String body = IOUtils.toString(is, Constants.UTF8);

			// Print Log
			String status = response.getStatusLine().toString();
			String contentType = response.getHeaders("Content-Type")[0].getValue();
			System.out.println("StatusLine:" + status);
			System.out.println("Content-Type:" + contentType);
			System.out.println("ResponseBody:" + body);

			// Set Attribute
			req.setAttribute("sntReq", "POST " + url + "\n\n"
					+ new String(req.getParameter("obj").getBytes(Constants.UTF8), Constants.UTF8));
			req.setAttribute("contentType", contentType);
			req.setAttribute("status", status);
			req.setAttribute("rcvRes", body);

			// 元の画面に戻す。
			req.getRequestDispatcher("/jsp/cal.jsp").forward(req, res);
		} catch (AppException e) {
			e.printStackTrace();
		} finally {
			System.out.println("<< end CreateSchedule");
		}
	}
}
