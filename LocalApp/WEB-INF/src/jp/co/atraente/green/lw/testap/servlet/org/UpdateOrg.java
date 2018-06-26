package jp.co.atraente.green.lw.testap.servlet.org;

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
import org.apache.http.client.methods.HttpPatch;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import com.nimbusds.jose.util.JSONObjectUtils;

import jp.co.atraente.green.lw.testap.common.Constants;
import jp.co.atraente.green.lw.testap.common.Utils;
import jp.co.atraente.green.lw.testap.exception.AppException;
import net.minidev.json.JSONObject;

public class UpdateOrg implements Servlet {

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

		System.out.println("start UpdateOrg >>");
		try {
			// -------------------------------------------------
			// パラメータの準備
			// -------------------------------------------------
			if (Utils.isEmpty(req.getParameter("exKey"))) {
				throw new AppException("exKey is empty.");
			}
			if (Utils.isEmpty(req.getParameter("obj"))) {
				throw new AppException("JSON is empty.");
			}
			String targetUrl = Constants.ORG_URL_PREFIX + (String) req.getParameter("exKey");
			JSONObject obj = JSONObjectUtils.parse(req.getParameter("obj"));

			StringEntity entity = new StringEntity(JSONObject.toJSONString(obj));
			// Print Log
			System.out.println("RequestBody:" + JSONObject.toJSONString(obj));

			// -------------------------------------------------
			// HTTPクライアントの準備
			// -------------------------------------------------
			CloseableHttpClient httpclient = HttpClients.createDefault();
			HttpPatch request = new HttpPatch(targetUrl);
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
			// 元の画面に戻す。
			req.getRequestDispatcher("/jsp/org.jsp").forward(req, res);
		} catch (AppException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		} finally {
			System.out.println("<< end UpdateOrg");
		}

	}
}
