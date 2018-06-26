package jp.co.atraente.green.lw.testap.servlet;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.HashMap;

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
import net.minidev.json.JSONObject;

public class RegistBot implements Servlet {

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

		// -------------------------------------------------
		// 送付するパラメータの準備
		// -------------------------------------------------
		HashMap<String, Object> param = new HashMap<String, Object>();
		param.put("name", (String) req.getParameter("name"));
		param.put("photoUrl", (String) req.getParameter("photoUrl"));
		param.put("status", 1);
		StringEntity entity = new StringEntity(JSONObject.toJSONString(param));
		// Print Log
		System.out.println("RequestBody:" + JSONObject.toJSONString(param));

		// -------------------------------------------------
		// HTTPクライアントの準備
		// -------------------------------------------------
		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpPost request = new HttpPost(Constants.REGIST_BOT_URL);
		request.setEntity(entity);
		Utils.setApiReqHeader4POST(request);

		// -------------------------------------------------
		// リクエストの送付＆レスポンスの取得
		// -------------------------------------------------
		CloseableHttpResponse response = httpclient.execute(request);
		InputStream is = response.getEntity().getContent();
		String body = IOUtils.toString(is, Charset.defaultCharset());

		// Print Log
		System.out.println("ResponseBody:" + body);
		// 元の画面に戻す。
		req.getRequestDispatcher("/jsp/admin.jsp").forward(req, res);
	}
}
