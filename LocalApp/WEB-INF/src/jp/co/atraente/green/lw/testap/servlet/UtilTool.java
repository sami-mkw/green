package jp.co.atraente.green.lw.testap.servlet;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.text.ParseException;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPatch;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import com.nimbusds.jose.util.JSONObjectUtils;

import jp.co.atraente.green.lw.testap.common.Constants;
import jp.co.atraente.green.lw.testap.common.Utils;
import jp.co.atraente.green.lw.testap.exception.AppException;
import net.minidev.json.JSONObject;

public class UtilTool implements Servlet {

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

		System.out.println(">> start UtilTool");

		req.setCharacterEncoding(Constants.UTF8);

		String type = (String) req.getParameter("type");
		String url = "";
		if (Utils.isEmpty(type)) {
			// -------------------------------------------------
			// 送付するパラメータ/URLの準備
			// -------------------------------------------------
			String params = "?_startDate=" + (String) req.getParameter("startDate") + "&_endDate="
					+ (String) req.getParameter("endDate") + "&_tenantId=" + Constants.DOMAIN_ID + "&_domainId="
					+ Constants.DOMAIN_ID + "&rangeName=tenant&apiId=downCsvLog&serviceId=audit";
			url = (String) req.getParameter("targetUrl") + params;

			// -------------------------------------------------
			// HTTPクライアントの準備
			// -------------------------------------------------
			CloseableHttpClient httpclient = HttpClients.createDefault();
			HttpGet request = new HttpGet(url);
			Utils.setApiReqHeader4GET(request);
			CloseableHttpResponse response = httpclient.execute(request);

			// -------------------------------------------------
			// リクエストの送付＆レスポンスの取得
			// -------------------------------------------------
			InputStream is = response.getEntity().getContent();
			String status = response.getStatusLine().toString();
			String contentType = "";
			if (response.getHeaders("Content-Type").length > 0) {
				contentType = response.getHeaders("Content-Type")[0].getValue();
			}
			String body = IOUtils.toString(is, Charset.defaultCharset());

			// Print Log
			System.out.println("Request:" + url);
			System.out.println("StatusLine:" + status);
			System.out.println("Content-Type:" + contentType);
			System.out.println("ResponseBody:" + body);

			// Set Attribute
			req.setAttribute("sntReq", "GET " + url);
			req.setAttribute("contentType", contentType);
			req.setAttribute("status", status);
			req.setAttribute("rcvRes", body);

			System.out.println("<< end UtilTool");
			// 元の画面に戻す
			req.getRequestDispatcher("/jsp/tool.jsp").forward(req, res);
		} else if ("addGrp".equals(type)) {
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

				// JSONObject obj = JSONObjectUtils.parse((String) req.getParameter("obj"));
				// String tmp = new String(JSONObject.toJSONString(obj).getBytes("EUC-JP"),
				// "UTF-8");
				String tmp = (String) req.getParameter("obj");
				StringEntity entity = new StringEntity(tmp);

				System.out.println("RequestBody:" + tmp);

				System.out.println(System.getProperty("file.encoding"));
				// -------------------------------------------------
				// HTTPクライアントの準備
				// -------------------------------------------------
				CloseableHttpClient httpclient = HttpClients.createDefault();
				HttpPost request = new HttpPost(url);
				request.setEntity(entity);
				Utils.setApiReqHeader4POST(request);

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
				req.getRequestDispatcher("/jsp/tool.jsp").forward(req, res);
			} catch (AppException e) {
				e.printStackTrace();
			} finally {
				System.out.println("<< end UtilTool");
			}
		} else if ("ref".equals(type))

		{

			try {
				if (Utils.isEmpty(req.getParameter("targetUrl"))) {
					throw new AppException("targetUrl is empty.");
				}
				url = (String) req.getParameter("targetUrl");
				// -------------------------------------------------
				// HTTPクライアントの準備
				// -------------------------------------------------
				CloseableHttpClient httpclient = HttpClients.createDefault();
				HttpGet request = new HttpGet(url);
				// Print Log
				System.out.println("requestURL:" + (String) req.getParameter("targetUrl"));

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

				// Set Attribute
				req.setAttribute("sntReq", "GET " + url);
				req.setAttribute("contentType", contentType);
				req.setAttribute("status", status);
				req.setAttribute("rcvRes", body);

				// 元の画面に戻す。
				req.getRequestDispatcher("/jsp/tool.jsp").forward(req, res);
			} catch (AppException e) {
				e.printStackTrace();
			} finally {
				System.out.println("<< end UtilTool");
			}
		} else if ("patch".equals(type)) {
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
				JSONObject obj = JSONObjectUtils
						.parse(new String(req.getParameter("obj").getBytes(Constants.UTF8), Constants.UTF8));

				StringEntity entity = new StringEntity(JSONObject.toJSONString(obj));
				// Print Log
				System.out.println(
						"RequestBody:" + new String(req.getParameter("obj").getBytes(Constants.UTF8), Constants.UTF8));

				// -------------------------------------------------
				// HTTPクライアントの準備
				// -------------------------------------------------
				CloseableHttpClient httpclient = HttpClients.createDefault();
				HttpPatch request = new HttpPatch(url);
				request.setEntity(entity);
				Utils.setApiReqHeader4PATCH(request);

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

				// Set Attribute
				req.setAttribute("sntReq", "PATCH " + url);
				req.setAttribute("contentType", contentType);
				req.setAttribute("status", status);
				req.setAttribute("rcvRes", body);

				// 元の画面に戻す。
				req.getRequestDispatcher("/jsp/tool.jsp").forward(req, res);
			} catch (AppException e) {
				e.printStackTrace();
			} catch (ParseException e) {
				e.printStackTrace();
			} finally {
				System.out.println("<< end UtilTool");
			}
		}
	}
}
