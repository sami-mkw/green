package jp.co.atraente.green.lw.demo.servlet.bot;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.nimbusds.jose.util.JSONObjectUtils;

import jp.co.atraente.green.lw.demo.common.Constants;
import jp.co.atraente.green.lw.demo.common.LwDomainInfo;
import jp.co.atraente.green.lw.demo.common.LwUtils;
import jp.co.atraente.green.lw.demo.exception.AppException;
import net.minidev.json.JSONObject;

public class RcvCallback implements Servlet {

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
	public void init(ServletConfig config) throws ServletException {
	}

	private Logger log = LogManager.getLogger(RcvCallback.class);

	@Override
	public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
		log.info(">> start RcvCallback -----------------------------");

		try {
			// -------------------------------------------------
			// Bodyの確認
			// -------------------------------------------------
			BufferedReader br = new BufferedReader(req.getReader());
			String body = br.readLine();
			// Print Log
			log.info("Recieved Request Body:" + body);
			JSONObject obj = JSONObjectUtils
					.parse(new String(body.getBytes(StandardCharsets.UTF_8), StandardCharsets.UTF_8));
			String type = (String) obj.get("type");

			// ----------------------
			// メッセージ受信
			// ----------------------
			if ("message".equals(type)) {
				log.info("recieved callback type is \"message\":");
				String contentType = (String) ((JSONObject) obj.get("content")).get("type");

				String roomId = (String) ((JSONObject) obj.get("source")).get("roomId");
				String accountId = (String) ((JSONObject) obj.get("source")).get("accountId");
				if (LwUtils.isEmpty(roomId) && LwUtils.isEmpty(accountId)) {
					throw new AppException("invalid roomId/accountId: Both NULL or empty");
				}
				// -----------------
				// テキスト
				// -----------------
				if ("text".equals(contentType)) {
					log.info("Content Type of the message is \"text\":");
					String text = (String) ((JSONObject) obj.get("content")).get("text");
					if (LwUtils.isEmpty(text)) {
						text = "";
					}
					sndMsg(roomId, accountId, text);
				}
				// -----------------
				// 位置情報
				// -----------------
				else if ("location".equals(contentType)) {
					log.info("Content Type of the message is \"location\":");
					sndMsg(roomId, accountId, "Content Type of the message is \"location\"");
				}
				// -----------------
				// スタンプ
				// -----------------
				else if ("sticker".equals(contentType)) {
					log.info("Content Type of the message is \"sticker\":");
					sndMsg(roomId, accountId, "Content Type of the message is \"sticker\"");
				}
				// -----------------
				// 画像
				// -----------------
				else if ("image".equals(contentType)) {
					log.info("Content Type of the message is \"image\"");
					sndMsg(roomId, accountId, "Content Type of the message is \"image\"");
				}
				// -----------------
				// その他（発生し得ない）
				// -----------------
				else {
					log.error("Content Type of the message is \"null\":");
					throw new AppException("invalid content type: NULL or empty");
				}
			}
			// ----------------------
			// Botがトークルームに参加
			// ----------------------
			else if ("join".equals(type)) {
				// 特に何もしない
				log.info("recieved callback type is \"join\": noting to do.");
			}
			// ----------------------
			// Botがトークルーム退室
			// ----------------------
			else if ("leave".equals(type)) {
				// 特に何もしない
				log.info("recieved callback type is \"leave\": noting to do.");
			}
			// ----------------------
			// メンバが参加
			// ----------------------
			else if ("joined".equals(type)) {
				// 特に何もしない
				log.info("recieved callback type is \"leave\": noting to do.");
			}
			// ----------------------
			// メンバが退室
			// ----------------------
			else if ("left".equals(type)) {
				// 特に何もしない
				log.info("recieved callback type is \"leave\": noting to do.");
			}
			// ----------------------
			// NULL or 空（発生し得ない）
			// ----------------------
			else if (LwUtils.isEmpty(type)) {
				log.error("recieved callback type is \"null\":");
				throw new AppException("invalid type: NULL or empty");
			}
			// ----------------------
			// その他（発生し得ない）
			// ----------------------
			else {
				log.error("recieved callback type is \"" + type + "\":");
				throw new AppException("unknown type: type=" + type);
			}
			log.info("200 OK");
			HttpServletResponse response = (HttpServletResponse) res;
			response.setStatus(200);
			response.setContentType(Constants.CONTENT_TYPE_JSON);
			ServletOutputStream sos = response.getOutputStream();
			String s = "{\"code\": 200, \"message\": \"OK\"}";
			sos.print(s);
			log.info("Sent Response Body:" + s);
			log.info("<< end RcvCallback ------------------------------");
			sos.close();
		} catch (AppException e) {
			log.info("500");
			log.error(e.getMessage(), e);
			HttpServletResponse response = (HttpServletResponse) res;
			response.setStatus(500);
			response.setContentType(Constants.CONTENT_TYPE_JSON);
			ServletOutputStream sos = response.getOutputStream();
			String s = "{\"code\": 500, \"message\": \"Unknown error\"}";
			sos.print(s);
			log.info("Sent Response Body:" + s);
			log.info("<< end RcvCallback ------------------------------");
			sos.close();
		} catch (ParseException e) {
			log.info("400");
			log.error(e.getMessage(), e);
			HttpServletResponse response = (HttpServletResponse) res;
			response.setStatus(400);
			response.setContentType(Constants.CONTENT_TYPE_JSON);
			ServletOutputStream sos = response.getOutputStream();
			String s = "{\"code\": 400, \"message\": \"Bad request\"}";
			sos.print(s);
			log.info("Sent Response Body:" + s);
			log.info("<< end RcvCallback ------------------------------");
			sos.close();
		} catch (Throwable e) {
			log.info("Catch Exception!");
			log.error(e.getMessage(), e);
			log.info("500");
			log.error(e.getMessage(), e);
			HttpServletResponse response = (HttpServletResponse) res;
			response.setStatus(500);
			response.setContentType(Constants.CONTENT_TYPE_JSON);
			ServletOutputStream sos = response.getOutputStream();
			String s = "{\"code\": 500, \"message\": \"Unknown error\"}";
			sos.print(s);
			log.info("Sent Response Body:" + s);
			log.info("<< end RcvCallback ------------------------------");
			sos.close();
		}
	}

	private void sndMsg(String roomId, String accountId, String msg) throws IOException, ParseException, AppException {
		String str = "";
		if (!LwUtils.isEmpty(roomId)) {
			str = "{\"botNo\": 2865,\"roomId\": \"" + roomId + "\",\"content\": {\"type\": \"text\"," + "\"text\": \""
					+ msg + "\" }}";
		} else if (!LwUtils.isEmpty(accountId)) {
			str = "{\"botNo\": 2865,\"accountId\": \"" + accountId + "\",\"content\": {\"type\": \"text\",\"text\": \""
					+ msg + "\" }}";
		} else {
			throw new AppException("System error: no target. both roomId and accountId is null. ");
		}
		// -------------------------------------------------
		// パラメータの準備
		// -------------------------------------------------
		String json = new String(str.getBytes(StandardCharsets.UTF_8), StandardCharsets.UTF_8);
		StringEntity entity = new StringEntity(json);
		// Print Log
		log.info("Sent Request Body:" + json);

		// -------------------------------------------------
		// HTTPクライアントの準備
		// -------------------------------------------------
		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpPost request = new HttpPost(Constants.SND_BOT_MSG_URL);
		request.setEntity(entity);
		request.setHeader("Content-Type", Constants.CONTENT_TYPE_JSON);
		request.setHeader("consumerKey", LwDomainInfo.getSeverApiConsumerKey());
		request.setHeader("Authorization", "Bearer " + LwDomainInfo.getServerToken());

		// -------------------------------------------------
		// リクエストの送付＆レスポンスの取得
		// -------------------------------------------------
		CloseableHttpResponse response = httpclient.execute(request);
		InputStream is = response.getEntity().getContent();
		String resBody = IOUtils.toString(is, Charset.defaultCharset());

		// Print Log
		String status = response.getStatusLine().toString();
		// String contentType = response.getHeaders("Content-Type")[0].getValue();
		log.info("StatusLine:" + status);
		// System.out.println("Content-Type:" + contentType);
		log.info("Recieved Response Body:" + resBody);

	}

}
