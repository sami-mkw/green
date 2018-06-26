/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.78
 * Generated at: 2018-05-28 01:51:16 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import com.nimbusds.jose.util.Base64URL;
import com.nimbusds.jose.util.Base64;
import sami.lineworks.testap.common.Constants;
import sami.lineworks.testap.common.Utils;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;

public final class contact_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  private volatile javax.el.ExpressionFactory _el_expressionfactory;
  private volatile org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public javax.el.ExpressionFactory _jsp_getExpressionFactory() {
    if (_el_expressionfactory == null) {
      synchronized (this) {
        if (_el_expressionfactory == null) {
          _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
        }
      }
    }
    return _el_expressionfactory;
  }

  public org.apache.tomcat.InstanceManager _jsp_getInstanceManager() {
    if (_jsp_instancemanager == null) {
      synchronized (this) {
        if (_jsp_instancemanager == null) {
          _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
        }
      }
    }
    return _jsp_instancemanager;
  }

  public void _jspInit() {
  }

  public void _jspDestroy() {
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
        throws java.io.IOException, javax.servlet.ServletException {

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html; charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			"/jsp/error.jsp", true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");

	String serverToken = Constants.getServerToken();
	String result = (String) request.getAttribute("result");
	if (Utils.isEmpty(serverToken)) {
		serverToken = "未取得です";
	}
	if (Utils.isEmpty(result)) {
		result = "未取得です";
	}

      out.write("\r\n");
      out.write("<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\r\n");
      out.write("<script type=\"text/javascript\">\r\n");
      out.write("\r\n");
      out.write("</script>\r\n");
      out.write("<title>Developer管理画面(アドレス帳)</title>\r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");
      out.write("\t<a href=\"http://127.0.0.1:8080/app/jsp/org.jsp\">組織管理</a> ｜\r\n");
      out.write("\t<a href=\"http://127.0.0.1:8080/app/jsp/admin.jsp\">BOT管理</a>\r\n");
      out.write("\t<br>\r\n");
      out.write("\t<h1>Developer管理画面(アドレス帳)</h1>\r\n");
      out.write("\t<hr />\r\n");
      out.write("\t<h2>サーバ情報</h2>\r\n");
      out.write("\t<ul>\r\n");
      out.write("\t\t<li>サーバ名：");
      out.print(Constants.SERVER_NAME);
      out.write("</li>\r\n");
      out.write("\t\t<li>サーバID：");
      out.print(Constants.SERVER_ID);
      out.write("</li>\r\n");
      out.write("\t\t<li>サーバトークン：");
      out.print(serverToken);
      out.write("</li>\r\n");
      out.write("\t\t<li style=\"list-style: none\">\r\n");
      out.write("\t\t\t<form method=\"get\" action=\"/app/getServerToken\">\r\n");
      out.write("\t\t\t\t<button type='submit' name='action'>サーバトークン更新</button>\r\n");
      out.write("\t\t\t</form>\r\n");
      out.write("\t\t</li>\r\n");
      out.write("\t</ul>\r\n");
      out.write("\t<hr />\r\n");
      out.write("\t<h2>アドレス帳管理</h2>\r\n");
      out.write("\t<h3>移行の際の個人連絡先の追加</h3>\r\n");
      out.write("\t<form method=\"post\" action=\"/app/addPrivateContact\">\r\n");
      out.write("\t\t<ul>\r\n");
      out.write("\t\t\t<li style=\"list-style: none\">[JSON]<br /> <textarea rows=\"15\"\r\n");
      out.write("\t\t\t\t\tcols=\"100\" name=\"obj\"></textarea></li>\r\n");
      out.write("\t\t\t<li style=\"list-style: none\"><button type='submit' name='action'>追加</button></li>\r\n");
      out.write("\t\t</ul>\r\n");
      out.write("\t</form>\r\n");
      out.write("\t<h3>固定メニュー照会</h3>\r\n");
      out.write("\t<form method=\"post\" action=\"/app/v3/getBotMenu\">\r\n");
      out.write("\t\t<ul>\r\n");
      out.write("\t\t\t<li style=\"list-style: none\">[botNo] <input type=\"text\"\r\n");
      out.write("\t\t\t\tsize=\"50\" name=\"botNo\"></input></li>\r\n");
      out.write("\t\t\t<li style=\"list-style: none\"><button type='submit' name='action'>固定メニュー照会</button></li>\r\n");
      out.write("\t\t\t<li style=\"list-style: none\">[JSON]<br /> <textarea rows=\"10\"\r\n");
      out.write("\t\t\t\t\tcols=\"100\" name=\"obj\">");
      out.print(result);
      out.write("</textarea></li>\r\n");
      out.write("\t\t</ul>\r\n");
      out.write("\t</form>\r\n");
      out.write("\t<h3>【text】メッセージ送信テスト</h3>\r\n");
      out.write("\t<form method=\"post\" action=\"/app/sndBotMsg\">\r\n");
      out.write("\t\t<ul>\r\n");
      out.write("\t\t\t<li style=\"list-style: none\">[botNo] <input type=\"text\"\r\n");
      out.write("\t\t\t\tsize=\"10\" name=\"botNo\"></input>，[accountId] <input type=\"text\"\r\n");
      out.write("\t\t\t\tsize=\"10\" name=\"accountId\"></input></li>\r\n");
      out.write("\t\t\t<li style=\"list-style: none\">[message]<input type=\"text\"\r\n");
      out.write("\t\t\t\tsize=\"50\" name=\"message\"></input></li>\r\n");
      out.write("\t\t\t<li style=\"list-style: none\"><button type=\"submit\" name=\"action\">送信</button></li>\r\n");
      out.write("\t\t</ul>\r\n");
      out.write("\t</form>\r\n");
      out.write("\t<h3>【その他】メッセージ送信テスト</h3>\r\n");
      out.write("\r\n");
      out.write("\t<form method=\"post\" action=\"/app/v3/sndBotMsg\">\r\n");
      out.write("\t\t<ul>\r\n");
      out.write("\t\t\t<li style=\"list-style: none\">[JSON]<br /> <textarea rows=\"20\"\r\n");
      out.write("\t\t\t\t\tcols=\"100\" name=\"obj\"></textarea></li>\r\n");
      out.write("\t\t\t<li style=\"list-style: none\"><button type='submit' name='action'>送信</button></li>\r\n");
      out.write("\t\t</ul>\r\n");
      out.write("\t</form>\r\n");
      out.write("\t<h3>csvファイルからメッセージ送信テスト</h3>\r\n");
      out.write("\t<form method=\"POST\" enctype=\"multipart/form-data\"\r\n");
      out.write("\t\taction=\"/app/sndBotMsgFromCsv\">\r\n");
      out.write("\t\t<ul>\r\n");
      out.write("\t\t\t<li style=\"list-style: none\">[csv file] <input type=\"file\"\r\n");
      out.write("\t\t\t\tname=\"upfile\"><br /> <br /></li>\r\n");
      out.write("\t\t\t<li style=\"list-style: none\"><button type=\"submit\" name=\"action\">メッセージ送信(from\r\n");
      out.write("\t\t\t\t\tcsv)テスト</button></li>\r\n");
      out.write("\t\t</ul>\r\n");
      out.write("\t</form>\r\n");
      out.write("</body>\r\n");
      out.write("</html>");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try {
            if (response.isCommitted()) {
              out.flush();
            } else {
              out.clearBuffer();
            }
          } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}