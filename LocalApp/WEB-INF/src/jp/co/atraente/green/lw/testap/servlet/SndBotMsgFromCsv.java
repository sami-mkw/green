package jp.co.atraente.green.lw.testap.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import jp.co.atraente.green.lw.testap.common.Constants;
import jp.co.atraente.green.lw.testap.common.Utils;
import jp.co.atraente.green.lw.testap.exception.AppException;

public class SndBotMsgFromCsv implements Servlet {

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

		// Check that we have a file upload request
		boolean isMultipart = ServletFileUpload.isMultipartContent((HttpServletRequest) req);
		if (isMultipart) {
			// Create a factory for disk-based file items
			DiskFileItemFactory factory = new DiskFileItemFactory();
			ServletFileUpload sfu = new ServletFileUpload(factory);
			try {
				List<FileItem> items = sfu.parseRequest((HttpServletRequest) req);
				for (int i = 0; i < items.size(); i++) {
					// ファイルの場合のみ処理する
					FileItem item = items.get(i);
					if (!item.isFormField()) {
						String filename = item.getName();
						long sizeInBytes = item.getSize();

						// ファイルサイズが1M超えていたらエラーで終了。
						if (sizeInBytes > 1000000) {
							throw new AppException("file size is too large.");
						}
						// ファイルサイズがemptyならエラーで終了。
						if (Utils.isEmpty(filename)) {
							throw new AppException("invalid file name (empty).");
						}

						// 文字列ストリームを作る。文字列エンコードはUTF-8
						InputStream uploadedStream = item.getInputStream();
						Reader reader = new InputStreamReader(uploadedStream, Constants.UTF8);
						BufferedReader bufferedReader = new BufferedReader(reader);
						// １行ずつ読み込む
						String line = bufferedReader.readLine();
						while (line != null) {
							if (!line.matches("^#.*")) {
								Utils.sndMsg(req, line);
							}
							line = bufferedReader.readLine();
						}
						reader.close();
						bufferedReader.close();
						uploadedStream.close();
					}
				}
			} catch (FileUploadException e) {
				e.printStackTrace();
			} catch (AppException e) {
				e.printStackTrace();
			}
		}
		// 元の画面に戻す
		req.getRequestDispatcher("/jsp/admin.jsp").forward(req, res);
	}
}
