package jp.co.atraente.green.lw.testap.servlet.oauth;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.text.ParseException;
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

import com.nimbusds.jose.util.JSONObjectUtils;

import jp.co.atraente.green.lw.testap.common.Constants;
import net.minidev.json.JSONObject;

public class GetToken implements Servlet {

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

		System.out.println("start GetToken >>");

		String code = req.getParameter("code");
		System.out.println("Authorization code:" + code);

		// -------------------------------------------------
		// 送付するパラメータの準備
		// -------------------------------------------------
		List<NameValuePair> requestParams = new ArrayList<NameValuePair>();
		requestParams.add(new BasicNameValuePair("grant_type", Constants.GRANT_TYPE_CODE));
		requestParams.add(new BasicNameValuePair("code", code));
		requestParams.add(new BasicNameValuePair("client_id", Constants.SERVICE_API_CONSUMER_KEY));
		requestParams.add(new BasicNameValuePair("domain", "red.wmjsales.xyz"));
		requestParams.add(new BasicNameValuePair("redirect_uri", "http://127.0.0.1:8080/app/token"));

		// -------------------------------------------------
		// HTTPクライアントの準備
		// -------------------------------------------------
		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpPost request = new HttpPost(Constants.TOKEN_ENDPOINT);
		request.setEntity(new UrlEncodedFormEntity(requestParams));
		request.setHeader("consumerKey", Constants.SERVICE_API_CONSUMER_KEY);

		// -------------------------------------------------
		// リクエストの送付＆レスポンスの取得
		// -------------------------------------------------
		CloseableHttpResponse response = httpclient.execute(request);
		InputStream is = response.getEntity().getContent();
		String body = IOUtils.toString(is, Charset.defaultCharset());
		System.out.println("Token Response:" + body);

		String returnTo = "/jsp/getToken.jsp";
		String accessToken = "";

		try {
			JSONObject obj = JSONObjectUtils.parse(body);
			accessToken = (String) obj.get("access_token");
			Constants.setServiceToken(accessToken);
			System.out.println("Accss Token:" + accessToken);

		} catch (ParseException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		// Print Log
		System.out.println("<< end GetToken");
		// 元の画面に戻す。
		req.getRequestDispatcher(returnTo).forward(req, res);
	}

}
