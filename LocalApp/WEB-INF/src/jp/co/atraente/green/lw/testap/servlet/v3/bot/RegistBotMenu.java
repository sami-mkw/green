package jp.co.atraente.green.lw.testap.servlet.v3.bot;

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
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import com.nimbusds.jose.util.JSONObjectUtils;

import jp.co.atraente.green.lw.testap.common.Constants;
import jp.co.atraente.green.lw.testap.common.Utils;
import jp.co.atraente.green.lw.testap.exception.AppException;
import net.minidev.json.JSONObject;

public class RegistBotMenu implements Servlet {

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

		System.out.println(">> start RegistBotMenu");
		try {
			// -------------------------------------------------
			// パラメータの準備
			// -------------------------------------------------
			if (Utils.isEmpty(req.getParameter("obj"))) {
				throw new AppException("JSON is empty.");
			}
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
			HttpPost request = new HttpPost(Constants.REGIST_BOT_MENU_URL);
			request.setEntity(entity);
			Utils.setApiReqHeader4POST(request);

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
			req.getRequestDispatcher("/jsp/v3/bot.jsp").forward(req, res);
		} catch (AppException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		} finally {
			System.out.println("<< end RegistBotMenu");
		}
	}
}
