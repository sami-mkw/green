package jp.co.atraente.green.lw.testap.contact;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;

import jp.co.atraente.green.lw.testap.common.Constants;
import jp.co.atraente.green.lw.testap.common.Utils;
import jp.co.atraente.green.lw.testap.exception.AppException;

public class AddPrivateContact implements Servlet {

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
		System.out.println(">> start EnableLINE");
		try {
			// // -------------------------------------------------
			// // パラメータの準備
			// // -------------------------------------------------
			// if (Utils.isEmpty(req.getParameter("obj"))) {
			// throw new AppException("JSON is empty.");
			// }
			// JSONObject obj = JSONObjectUtils
			// .parse(new String(req.getParameter("obj").getBytes(Constants.UTF8),
			// Constants.UTF8));
			// StringEntity entity = new StringEntity(JSONObject.toJSONString(obj));
			// // Print Log
			// System.out.println("RequestBody:" + JSONObject.toJSONString(obj));

			// -------------------------------------------------
			// 送付するパラメータの準備
			// -------------------------------------------------
			List<NameValuePair> requestParams = new ArrayList<NameValuePair>();

			requestParams.add(new BasicNameValuePair("targetMemberEmail", "sami.maekawa@red.wmjsales.xyz"));
			//
			if (Utils.isEmpty(req.getParameter("obj"))) {
				throw new AppException("JSON is empty.");
			}
			// JSONObject obj = JSONObjectUtils
			// .parse(new String(req.getParameter("obj").getBytes(Constants.UTF8),
			// Constants.UTF8));
			// requestParams.add(new BasicNameValuePair("contact",
			// JSONObject.toJSONString(obj)));
			requestParams.add(new BasicNameValuePair("contact", req.getParameter("obj")));
			// requestParams.add(new BasicNameValuePair("photoBase64", ""));
			//
			// // Print Log
			System.out.println("targetMemberEmail:sami.maekawa@red.wmjsales.xyz");
			// System.out.println("contact:" + JSONObject.toJSONString(obj));
			System.out.println("contact:" + req.getParameter("obj"));

			// -------------------------------------------------
			// HTTPクライアントの準備
			// -------------------------------------------------
			CloseableHttpClient httpclient = HttpClients.createDefault();
			HttpPost request = new HttpPost(Constants.ADD_PRIVATE_CONTACT_URL);
			// request.setEntity(entity);
			// Utils.setApiReqHeader4POST(request);
			request.setEntity(new UrlEncodedFormEntity(requestParams));
			Utils.setApiReqHeader4POSTForm(request);

			// Print Log
			BufferedReader br = new BufferedReader(new InputStreamReader(request.getEntity().getContent()));
			StringBuilder sb = new StringBuilder();
			String line;
			while ((line = br.readLine()) != null) {
				sb.append(line);
			}
			System.out.println("Request Body:\n" + sb.toString());
			br.close();

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
			req.getRequestDispatcher("/jsp/contact.jsp").forward(req, res);
		} catch (AppException e) {
			e.printStackTrace();
		} finally {
			System.out.println("<< end EnableLINE");
		}
	}
}
