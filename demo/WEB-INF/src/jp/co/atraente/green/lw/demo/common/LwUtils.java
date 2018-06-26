package jp.co.atraente.green.lw.demo.common;

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

import org.apache.commons.io.IOUtils;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
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

import net.minidev.json.JSONObject;

public class LwUtils {

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

	public static void getServerToken() throws ClientProtocolException, IOException, NoSuchAlgorithmException,
			InvalidKeySpecException, JOSEException, ParseException {
		// -------------------------------------------------
		// JWT Bodyの準備
		// -------------------------------------------------
		JWTClaimsSet claimsSet = new JWTClaimsSet.Builder().issuer(LwDomainInfo.getServerId()).issueTime(new Date())
				.expirationTime(new Date(new Date().getTime() + 300 * 1000)).build();

		// -------------------------------------------------
		// 署名対象のJWT用意
		// -------------------------------------------------
		SignedJWT signedJWT = new SignedJWT(new JWSHeader(JWSAlgorithm.RS256), claimsSet);

		// -------------------------------------------------
		// 秘密鍵の読み込み＆署名
		// -------------------------------------------------
		Base64 prvKey = new Base64(LwDomainInfo.getPrivateKey());
		PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(prvKey.decode());
		KeyFactory kf = KeyFactory.getInstance("RSA");
		JWSSigner signer;
		signer = new RSASSASigner(kf.generatePrivate(spec));
		signedJWT.sign(signer);
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

		JSONObject obj = JSONObjectUtils.parse(body);
		String token = (String) obj.get("access_token");
		LwDomainInfo.setServerToken(token);
	}
}
