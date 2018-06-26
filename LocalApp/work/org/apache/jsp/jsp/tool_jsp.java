/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.78
 * Generated at: 2018-05-18 04:26:23 UTC
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

public final class tool_jsp extends org.apache.jasper.runtime.HttpJspBase
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
	if (Utils.isEmpty(serverToken)) {
		serverToken = "未取得です";
	}
	String sntReq = (String) request.getAttribute("sntReq");
	String rcvRes = (String) request.getAttribute("rcvRes");
	String status = (String) request.getAttribute("status");
	String contentType = (String) request.getAttribute("contentType");

      out.write("\r\n");
      out.write("<?xml version=\"1.0\" encoding=\"utf-8\"?>\r\n");
      out.write("<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\r\n");
      out.write("<script\r\n");
      out.write("\tsrc=\"https://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js\"></script>\r\n");
      out.write("<script\r\n");
      out.write("\tsrc=\"http://ajax.googleapis.com/ajax/libs/jqueryui/1/jquery-ui.min.js\"></script>\r\n");
      out.write("<script\r\n");
      out.write("\tsrc=\"http://ajax.googleapis.com/ajax/libs/jqueryui/1/i18n/jquery.ui.datepicker-ja.min.js\"></script>\r\n");
      out.write("<script>\r\n");
      out.write("\t$(function() {\r\n");
      out.write("\t\t$(\"#datepicker\").datepicker();\r\n");
      out.write("\t});\r\n");
      out.write("</script>\r\n");
      out.write("<title>LINE WORKS API Test Tool</title>\r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");
      out.write("\t<h1>LINE WORKS API Test Tool</h1>\r\n");
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
      out.write("\t<h2>Test Tool</h2>\r\n");
      out.write("\t<!--\r\n");
      out.write("\t<div style=\"text-decoration: none\"\r\n");
      out.write("\t\tonclick=\"var style = this.parentNode.getElementsByTagName('div')[1].style; style.display=(style.display ==='block' ?'none':'block')\">\r\n");
      out.write("\t\t -->\r\n");
      out.write("\t<h3>監査API</h3>\r\n");
      out.write("\t<!--  <div style=\"display: none\"> -->\r\n");
      out.write("\t<div>\r\n");
      out.write("\t\t<h4>利用するAPIの種類</h4>\r\n");
      out.write("\t\t<form method=\"post\" action=\"/app/utilTool\">\r\n");
      out.write("\t\t\t<ul>\r\n");
      out.write("\t\t\t\t<li style=\"list-style: none\"><input type=\"radio\"\r\n");
      out.write("\t\t\t\t\tname=\"targetUrl\" value=\"");
      out.print(Constants.AUDIT_ADMIN_LOG_URL);
      out.write("\" checked>adminログ</li>\r\n");
      out.write("\t\t\t\t<li style=\"list-style: none\"><input type=\"radio\"\r\n");
      out.write("\t\t\t\t\tname=\"targetUrl\" value=\"");
      out.print(Constants.AUDIT_AUTH_LOG_URL);
      out.write("\">authログ</li>\r\n");
      out.write("\t\t\t\t<li style=\"list-style: none\"><input type=\"radio\"\r\n");
      out.write("\t\t\t\t\tname=\"targetUrl\" value=\"");
      out.print(Constants.AUDIT_HOME_LOG_URL);
      out.write("\">homeログ</li>\r\n");
      out.write("\t\t\t\t<li style=\"list-style: none\"><input type=\"radio\"\r\n");
      out.write("\t\t\t\t\tname=\"targetUrl\" value=\"");
      out.print(Constants.AUDIT_DRIVE_LOG_URL);
      out.write("\">driveログ</li>\r\n");
      out.write("\t\t\t\t<li style=\"list-style: none\"><input type=\"radio\"\r\n");
      out.write("\t\t\t\t\tname=\"targetUrl\" value=\"");
      out.print(Constants.AUDIT_CALENDER_LOG_URL);
      out.write("\">calendarログ</li>\r\n");
      out.write("\t\t\t\t<li style=\"list-style: none\"><input type=\"radio\"\r\n");
      out.write("\t\t\t\t\tname=\"targetUrl\" value=\"");
      out.print(Constants.AUDIT_CONTACT_LOG_URL);
      out.write("\">contactログ</li>\r\n");
      out.write("\t\t\t\t<li style=\"list-style: none\"><input type=\"radio\"\r\n");
      out.write("\t\t\t\t\tname=\"targetUrl\" value=\"");
      out.print(Constants.AUDIT_FORM_LOG_URL);
      out.write("\">formログ</li>\r\n");
      out.write("\t\t\t\t<li style=\"list-style: none\"><input type=\"radio\"\r\n");
      out.write("\t\t\t\t\tname=\"targetUrl\" value=\"");
      out.print(Constants.AUDIT_SHARE_LOG_URL);
      out.write("\">shareログ</li>\r\n");
      out.write("\t\t\t\t<li style=\"list-style: none\"><input type=\"radio\"\r\n");
      out.write("\t\t\t\t\tname=\"targetUrl\" value=\"");
      out.print(Constants.AUDIT_NOTE_LOG_URL);
      out.write("\">noteログ</li>\r\n");
      out.write("\t\t\t\t<li style=\"list-style: none\"><input type=\"radio\"\r\n");
      out.write("\t\t\t\t\tname=\"targetUrl\" value=\"");
      out.print(Constants.AUDIT_RCVMAIL_LOG_URL);
      out.write("\">received-mailログ</li>\r\n");
      out.write("\t\t\t\t<li style=\"list-style: none\"><input type=\"radio\"\r\n");
      out.write("\t\t\t\t\tname=\"targetUrl\" value=\"");
      out.print(Constants.AUDIT_MESSAGE_LOG_URL);
      out.write("\">messageログ</li>\r\n");
      out.write("\t\t\t\t<li style=\"list-style: none\"><input type=\"radio\"\r\n");
      out.write("\t\t\t\t\tname=\"targetUrl\" value=\"");
      out.print(Constants.AUDIT_SNTMAIL_LOG_URL);
      out.write("\">sent-mailログ</li>\r\n");
      out.write("\t\t\t\t<li style=\"list-style: none\">[startDate]<input type=\"text\"\r\n");
      out.write("\t\t\t\t\tname=\"startDate\" size=\"20\" id=\"datepicker\" value=\"20180101\">\r\n");
      out.write("\t\t\t\t\t[endDate]<input type=\"text\" name=\"endDate\" size=\"20\"\r\n");
      out.write("\t\t\t\t\tid=\"datepicker\" value=\"20180131\"></li>\r\n");
      out.write("\t\t\t\t<li style=\"list-style: none\"><button type='submit'\r\n");
      out.write("\t\t\t\t\t\tname='action'>対象期間のログを取得</button></li>\r\n");
      out.write("\t\t\t</ul>\r\n");
      out.write("\t\t</form>\r\n");
      out.write("\t</div>\r\n");
      out.write("\t<!-- \t</div>\r\n");
      out.write(" -->\r\n");
      out.write("\t<h3>グループ追加API</h3>\r\n");
      out.write("\t<div>\r\n");
      out.write("\t\t<h4>利用するAPIの種類</h4>\r\n");
      out.write("\t\t<form method=\"post\" action=\"/app/utilTool\">\r\n");
      out.write("\t\t\t<ul>\r\n");
      out.write("\t\t\t\t<li style=\"list-style: none\"><input type=\"radio\"\r\n");
      out.write("\t\t\t\t\tname=\"targetUrl\" value=\"");
      out.print(Constants.GRP_ADD_URL);
      out.write("test0515\" checked>グループ追加</li>\r\n");
      out.write("\t\t\t\t<li style=\"list-style: none\">[JSON]<br /> <textarea rows=\"10\"\r\n");
      out.write("\t\t\t\t\t\tcols=\"100\" name=\"obj\"></textarea></li>\r\n");
      out.write("\t\t\t\t<li style=\"list-style: none\"><button type='submit'\r\n");
      out.write("\t\t\t\t\t\tname='action'>グループ追加</button></li>\r\n");
      out.write("\t\t\t\t<input type=\"hidden\" name=\"type\" value=\"addGrp\">\r\n");
      out.write("\t\t\t</ul>\r\n");
      out.write("\t\t</form>\r\n");
      out.write("\t</div>\r\n");
      out.write("\r\n");
      out.write("\t<h3>トークルーム生成API</h3>\r\n");
      out.write("\t<div>\r\n");
      out.write("\t\t<h4>利用するAPIの種類</h4>\r\n");
      out.write("\t\t<form method=\"post\" action=\"/app/utilTool\">\r\n");
      out.write("\t\t\t<ul>\r\n");
      out.write("\t\t\t\t<li style=\"list-style: none\"><input type=\"radio\"\r\n");
      out.write("\t\t\t\t\tname=\"targetUrl\" value=\"");
      out.print(Constants.CREATE_ROOM_URL);
      out.write("\" checked>トークルーム生成</li>\r\n");
      out.write("\t\t\t\t<li style=\"list-style: none\">[JSON]<br /> <textarea rows=\"10\"\r\n");
      out.write("\t\t\t\t\t\tcols=\"100\" name=\"obj\"></textarea></li>\r\n");
      out.write("\t\t\t\t<li style=\"list-style: none\"><button type='submit'\r\n");
      out.write("\t\t\t\t\t\tname='action'>トークルーム生成</button></li>\r\n");
      out.write("\t\t\t\t<input type=\"hidden\" name=\"type\" value=\"addGrp\">\r\n");
      out.write("\t\t\t</ul>\r\n");
      out.write("\t\t</form>\r\n");
      out.write("\t</div>\r\n");
      out.write("\r\n");
      out.write("\t<h3>役職操作</h3>\r\n");
      out.write("\t<div>\r\n");
      out.write("\t\t<h4>役職追加API</h4>\r\n");
      out.write("\t\t<form method=\"post\" action=\"/app/utilTool\" accept-charset=\"UTF-8\">\r\n");
      out.write("\t\t\t<ul>\r\n");
      out.write("\t\t\t\t<li style=\"list-style: none\"><input type=\"radio\"\r\n");
      out.write("\t\t\t\t\tname=\"targetUrl\" value=\"");
      out.print(Constants.POSITION_ADD_URL);
      out.write("\" checked>役職追加</li>\r\n");
      out.write("\t\t\t\t<li style=\"list-style: none\">[JSON]<br /> <textarea rows=\"10\"\r\n");
      out.write("\t\t\t\t\t\tcols=\"100\" name=\"obj\" document.charset=\"UTF-8\"></textarea></li>\r\n");
      out.write("\t\t\t\t<li style=\"list-style: none\"><button type='submit'\r\n");
      out.write("\t\t\t\t\t\tname='action'>役職追加</button></li>\r\n");
      out.write("\t\t\t\t<input type=\"hidden\" name=\"type\" value=\"addGrp\">\r\n");
      out.write("\t\t\t</ul>\r\n");
      out.write("\t\t</form>\r\n");
      out.write("\t</div>\r\n");
      out.write("\t<div>\r\n");
      out.write("\t\t<h4>役職照会API</h4>\r\n");
      out.write("\t\t<form method=\"post\" action=\"/app/utilTool\">\r\n");
      out.write("\t\t\t<ul>\r\n");
      out.write("\t\t\t\t<li style=\"list-style: none\"><input type=\"radio\"\r\n");
      out.write("\t\t\t\t\tname=\"targetUrl\" value=\"");
      out.print(Constants.POSITION_REF_URL);
      out.write("\" checked>役職照会</li>\r\n");
      out.write("\t\t\t\t<li style=\"list-style: none\"><button type='submit'\r\n");
      out.write("\t\t\t\t\t\tname='action'>役職照会</button></li>\r\n");
      out.write("\t\t\t\t<input type=\"hidden\" name=\"type\" value=\"ref\">\r\n");
      out.write("\t\t\t</ul>\r\n");
      out.write("\t\t</form>\r\n");
      out.write("\t</div>\r\n");
      out.write("\r\n");
      out.write("\t<h3>メンバ操作</h3>\r\n");
      out.write("\t<div>\r\n");
      out.write("\t\t<h4>メンバ部分修正API</h4>\r\n");
      out.write("\t\t<form method=\"post\" action=\"/app/utilTool\">\r\n");
      out.write("\t\t\t<ul>\r\n");
      out.write("\t\t\t\t<li style=\"list-style: none\"><input type=\"radio\"\r\n");
      out.write("\t\t\t\t\tname=\"targetUrl\" value=\"");
      out.print(Constants.MEMBER_CHANGE_URL);
      out.write("\" checked>メンバ部分修正</li>\r\n");
      out.write("\t\t\t\t<li style=\"list-style: none\">[JSON]<br /> <textarea rows=\"10\"\r\n");
      out.write("\t\t\t\t\t\tcols=\"100\" name=\"obj\"></textarea></li>\r\n");
      out.write("\t\t\t\t<li style=\"list-style: none\"><button type='submit'\r\n");
      out.write("\t\t\t\t\t\tname='action'>メンバ部分修正</button></li>\r\n");
      out.write("\t\t\t\t<input type=\"hidden\" name=\"type\" value=\"patch\">\r\n");
      out.write("\t\t\t</ul>\r\n");
      out.write("\t\t</form>\r\n");
      out.write("\t</div>\r\n");
      out.write("\t");

		if (!Utils.isEmpty(sntReq)) {
	
      out.write("\r\n");
      out.write("\t<hr>\r\n");
      out.write("\t<h4>Request</h4>\r\n");
      out.write("\t");
      out.print(sntReq);
      out.write('\r');
      out.write('\n');
      out.write('	');

		}
		if (!Utils.isEmpty(rcvRes)) {
	
      out.write("\r\n");
      out.write("\t<h4>ResultHeader</h4>\r\n");
      out.write("\t");

		if (!Utils.isEmpty(status)) {
	
      out.write('\r');
      out.write('\n');
      out.write('	');
      out.print(status);
      out.write("<br>\r\n");
      out.write("\t");

		}
			if (!Utils.isEmpty(contentType)) {
	
      out.write("\r\n");
      out.write("\tContent-Type:");
      out.print(contentType);
      out.write("\r\n");
      out.write("\r\n");
      out.write("\t");

		}
	
      out.write("\r\n");
      out.write("\t<h4>ResultBody</h4>\r\n");
      out.write("\t<xmp> ");
      out.print(rcvRes);
      out.write(" </xmp>\r\n");
      out.write("\t");

		}
	
      out.write("\r\n");
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