package jp.co.atraente.green.lw.testap.common;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.text.ParseException;
import java.util.HashMap;

import javax.servlet.ServletRequest;

import org.apache.commons.io.IOUtils;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPatch;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import com.nimbusds.jose.util.JSONObjectUtils;

import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;

public class Utils {

	private static JSONArray botList;

	public static String hogehoge() {
		return "";
	}

	/**
	 * 「botNo,accountId,message」の形式の文字列引数を元にBotAPIを利用したメッセージを送付する。
	 *
	 * @param req
	 * @param str
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public static void sndMsg(ServletRequest req, String str) throws ClientProtocolException, IOException {

		System.out.println("start sndMsg >>");

		// -------------------------------------------------
		// 文字列をカンマで分割
		// -------------------------------------------------
		String[] items = str.split(",", 0);
		if (items.length != 3) {
			System.out.println("invalid line:" + str);
			return;
		}
		String botNo = items[0];
		String accountId = items[1];
		String message = items[2];

		// -------------------------------------------------
		// 送付するJSONオブジェクトの準備
		// -------------------------------------------------
		req.setCharacterEncoding(Constants.UTF8);
		HashMap<String, Object> param = new HashMap<String, Object>();
		param.put("botNo", Integer.parseInt(botNo));
		param.put("accountId", accountId);
		HashMap<String, String> subParam = new HashMap<String, String>();
		subParam.put("type", "text");
		subParam.put("text", message);
		param.put("content", subParam);
		StringEntity entity = new StringEntity(JSONObject.toJSONString(param), Constants.UTF8);

		System.out.println("SendBody:" + JSONObject.toJSONString(param));

		// -------------------------------------------------
		// HTTPクライアントの準備
		// -------------------------------------------------
		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpPost request = new HttpPost(Constants.SND_BOT_MSG_URL);
		request.setEntity(entity);
		Utils.setApiReqHeader4POST(request);

		// -------------------------------------------------
		// リクエストの送付＆レスポンスの取得
		// -------------------------------------------------
		CloseableHttpResponse response = httpclient.execute(request);
		InputStream is = response.getEntity().getContent();
		String body = IOUtils.toString(is, Charset.defaultCharset());
		System.out.println("Result:" + body);
		System.out.println("<< end setBotList");
		return;
	}

	/**
	 * 登録されているBotの一覧を取得してメモリに保存する。
	 *
	 * @throws ClientProtocolException
	 * @throws IOException
	 * @throws ParseException
	 */
	public static void setBotList() throws ClientProtocolException, IOException, ParseException {

		System.out.println("start setBotList >>");
		// -------------------------------------------------
		// HTTPクライアントの準備
		// -------------------------------------------------
		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpPost request = new HttpPost(Constants.GET_BOT_LIST_URL);
		Utils.setApiReqHeader4POST(request);

		// -------------------------------------------------
		// リクエストの送付＆レスポンスの取得
		// -------------------------------------------------
		CloseableHttpResponse response = httpclient.execute(request);
		InputStream is = response.getEntity().getContent();
		String body = IOUtils.toString(is, Constants.UTF8);
		JSONObject obj = JSONObjectUtils.parse(body);
		if (obj.containsKey("botList") && (obj.get("botList") != null)) {
			botList = JSONObjectUtils.getJSONArray(obj, "botList");
		}

		// -------------------------------------------------
		// ログの出力
		// -------------------------------------------------
		int status = response.getStatusLine().getStatusCode();
		System.out.println("HTTPステータス:" + status);
		System.out.println("ResponseBody:" + body);
		System.out.println("<< end setBotList");
		return;
	}

	/**
	 * 登録されているBotの一覧を返却する。
	 *
	 * @return
	 */
	public static JSONArray getBotList() {
		return botList;
	}

	/**
	 * API利用時のGETのヘッダに、サーバ認証用のパラメータを付す。
	 *
	 * @param request
	 */
	public static void setApiReqHeader4GET(HttpGet request) {
		request.setHeader("consumerKey", Constants.SERVER_API_CONSUMER_KEY);
		request.setHeader("Authorization", "Bearer " + Constants.getServerToken());
	}

	/**
	 * API利用時のPOSTのヘッダに、サーバ認証用のパラメータを付す。
	 *
	 * @param request
	 */
	public static void setApiReqHeader4POST(HttpPost request) {
		request.setHeader("Content-Type", Constants.CONTENT_TYPE_JSON);
		request.setHeader("consumerKey", Constants.SERVER_API_CONSUMER_KEY);
		request.setHeader("Authorization", "Bearer " + Constants.getServerToken());
	}

	/**
	 * API利用時のPOSTのヘッダに、サーバ認証用のパラメータを付す。
	 *
	 * @param request
	 */
	public static void setApiReqHeader4POSTForm(HttpPost request) {
		request.setHeader("Content-Type", Constants.CONTENT_TYPE_FORM);
		request.setHeader("consumerKey", Constants.SERVER_API_CONSUMER_KEY);
		request.setHeader("Authorization", "Bearer " + Constants.getServerToken());
	}

	/**
	 * API利用時のPATCHのヘッダに、サーバ認証用のパラメータを付す。
	 *
	 * @param request
	 */
	public static void setApiReqHeader4PATCH(HttpPatch request) {
		request.setHeader("Content-Type", Constants.CONTENT_TYPE_JSON);
		request.setHeader("consumerKey", Constants.SERVER_API_CONSUMER_KEY);
		request.setHeader("Authorization", "Bearer " + Constants.getServerToken());
	}

	/**
	 * API利用時のDELETEのヘッダに、サーバ認証用のパラメータを付す。
	 *
	 * @param request
	 */
	public static void setApiReqHeader4DELETE(HttpDelete request) {
		request.setHeader("Content-Type", Constants.CONTENT_TYPE_JSON);
		request.setHeader("consumerKey", Constants.SERVER_API_CONSUMER_KEY);
		request.setHeader("Authorization", "Bearer " + Constants.getServerToken());
	}

	/**
	 * 文字列が空文字やNullであるかを確認する。
	 *
	 * @param 対象文字列t
	 * @return
	 */
	public static boolean isEmpty(String target) {
		if ((null == target) || (target.length() == 0)) {
			return true;
		} else {
			return false;
		}
	}
}
