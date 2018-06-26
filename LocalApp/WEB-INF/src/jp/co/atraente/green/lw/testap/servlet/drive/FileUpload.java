package jp.co.atraente.green.lw.testap.servlet.drive;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import jp.co.atraente.green.lw.testap.common.Constants;

public class FileUpload implements Servlet {

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

		System.out.println("start FileUpload >>");

		// -------------------------------------------------
		// HTTPクライアントの準備
		// -------------------------------------------------

		String boundary = "NextContent";

		CloseableHttpClient httpclient = HttpClients.createDefault();

		HttpPut request = new HttpPut(Constants.DRIVE_FILE_UPLOAD_URL
				+ "?resourceName=test.txt&toParentKey=root&writeMode=none&isRetResourceKey=true");
		// HttpPost request = new HttpPost(Constants.DRIVE_FILE_UPLOAD_URL);
		request.setHeader("X-DRIVE-API-TYPE", "reseller-api");
		request.setHeader("consumerKey", Constants.SERVICE_API_CONSUMER_KEY);
		request.setHeader("Authorization", "Bearer " + Constants.getServiceToken());

		request.setHeader("Content-Type", "text/plain; charset=UTF-8");
		// request.setHeader("Content-Type", "multipart/mixed; boundary=" + boundary +
		// "; charset=UTF-8");

		HttpEntity entity = MultipartEntityBuilder.create().setBoundary(boundary).setCharset(Charset.forName("UTF-8"))
				.setMode(HttpMultipartMode.BROWSER_COMPATIBLE)
				// .addTextBody("resourceName", "icon.png").addTextBody("toParentKey", "root")
				// .addTextBody("isRetResourceKey", "true").addTextBody("writeMode", "none")
				.addBinaryBody("file", new File("C:\\tmp\\test.txt"), ContentType.create("text/plain"), "icon.png")
				// .addBinaryBody("file", new File("C:\\tmp\\icon.png"),
				// ContentType.create("application/octet-stream"),
				// "icon.png")
				.build();

		// -------------------------------------------------
		// .addPart("resourceName",
		// new
		// StringBody("resourceName=icon.png&isRetResourceKey=true&toParentKey=root&writeMode=none",
		// ContentType.create("application/x-www-form-urlencoded")))
		// .build();

		// -------------------------------------------------
		// .addPart("file", new FileBody(new File("C:\\tmp\\icon.png"),
		// ContentType.create("image/png")))
		// .addPart("resourceName", new StringBody("icon.png",
		// ContentType.create("multipart/form-data")))
		// .addPart("toParentKey", new StringBody("toParentKey=root",
		// ContentType.create("multipart/form-data")))
		// .addPart("isRetResourceKey", new StringBody("true",
		// ContentType.create("multipart/form-data")))
		// .addPart("writeMode", new StringBody("none",
		// ContentType.create("multipart/form-data")))
		// .addPart("useridx", new StringBody("1090919",
		// ContentType.create("multipart/form-data"))).build();

		// ----------------------------------------
		// .addPart(FormBodyPartBuilder.create().setName("isRetResourceKey")
		// .setBody(new StringBody("true", ContentType.create("text/plain"))).build())
		// .addPart(FormBodyPartBuilder.create().setName("writeMode")
		// .setBody(new StringBody("none", ContentType.create("text/plain"))).build())
		// .addPart(FormBodyPartBuilder.create().setName("toParentKey")
		// .setBody(new StringBody("root", ContentType.create("text/plain"))).build())
		// .addPart(FormBodyPartBuilder.create().setName("resourceName")
		// .setBody(new StringBody("icon-2.png",
		// ContentType.create("text/plain"))).build())
		// .build();

		request.setEntity(entity);

		// -------------------------------------------------
		// リクエストの送付＆レスポンスの取得
		// -------------------------------------------------
		CloseableHttpResponse response = httpclient.execute(request);
		InputStream is = response.getEntity().getContent();
		String body = IOUtils.toString(is, Charset.defaultCharset());
		System.out.println("Response:" + body);

		String returnTo = "/jsp/drive/drive.jsp";

		// Print Log
		System.out.println("<< end FileUpload");
		// 元の画面に戻す。
		req.getRequestDispatcher(returnTo).forward(req, res);

	}
}
