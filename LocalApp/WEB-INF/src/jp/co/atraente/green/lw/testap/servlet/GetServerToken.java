package jp.co.atraente.green.lw.testap.servlet;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.IOUtils;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSSigner;
import com.nimbusds.jose.crypto.RSASSASigner;
import com.nimbusds.jose.util.Base64;
import com.nimbusds.jose.util.JSONObjectUtils;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;

import jp.co.atraente.green.lw.testap.common.Constants;
import net.minidev.json.JSONObject;

public class GetServerToken implements Servlet {

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

		System.out.println("start GetServerToken >>");

		HttpServletRequest tmpReq = (HttpServletRequest) req;
		String referer = tmpReq.getHeader("Referer");
		String[] tmpStr = referer.split("/app", 0);
		String returnTo = tmpStr[tmpStr.length - 1];

		// -------------------------------------------------
		// JWT Bodyの準備
		// -------------------------------------------------
		JWTClaimsSet claimsSet = new JWTClaimsSet.Builder().issuer(Constants.SERVER_ID).issueTime(new Date())
				.expirationTime(new Date(new Date().getTime() + 300 * 1000)).build();

		// -------------------------------------------------
		// 署名対象のJWT用意
		// -------------------------------------------------
		SignedJWT signedJWT = new SignedJWT(new JWSHeader(JWSAlgorithm.RS256), claimsSet);

		// -------------------------------------------------
		// 秘密鍵の読み込み＆署名
		// -------------------------------------------------
		Base64 prvKey = new Base64(Constants.PRIVATE_KEY);
		PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(prvKey.decode());
		KeyFactory kf;
		try {
			kf = KeyFactory.getInstance("RSA");
			JWSSigner signer;
			signer = new RSASSASigner(kf.generatePrivate(spec));
			signedJWT.sign(signer);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (InvalidKeySpecException e) {
			e.printStackTrace();
		} catch (JOSEException e) {
			e.printStackTrace();
		}

		// -------------------------------------------------
		// 送付するパラメータの準備
		// -------------------------------------------------
		List<NameValuePair> requestParams = new ArrayList<NameValuePair>();
		requestParams.add(new BasicNameValuePair("grant_type", Constants.GRANT_TYPE_JWT));
		requestParams.add(new BasicNameValuePair("assertion", signedJWT.serialize()));

		// -------------------------------------------------
		// HTTPクライアントの準備
		// -------------------------------------------------
		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpPost request = new HttpPost(Constants.SERVER_TOKEN_URL);
		request.setEntity(new UrlEncodedFormEntity(requestParams));

		// -------------------------------------------------
		// リクエストの送付＆レスポンスの取得
		// -------------------------------------------------
		CloseableHttpResponse response = httpclient.execute(request);
		InputStream is = response.getEntity().getContent();
		String body = IOUtils.toString(is, Charset.defaultCharset());

		try {
			JSONObject obj = JSONObjectUtils.parse(body);
			String accessToken = (String) obj.get("access_token");
			Constants.setServerToken(accessToken);
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		// Print Log
		System.out.println("ResponseBody:" + body);
		System.out.println("<< end GetServerToken");
		// 元の画面に戻す。
		req.getRequestDispatcher(returnTo).forward(req, res);
	}
}
