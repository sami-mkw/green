<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" errorPage="/jsp/error.jsp"%>
<%@ page import="com.nimbusds.jose.util.Base64URL"%>
<%@ page import="com.nimbusds.jose.util.Base64"%>
<%@page import="jp.co.atraente.green.lw.testap.common.Constants"%>
<%@page import="jp.co.atraente.green.lw.testap.common.Utils"%>
<%@page import="net.minidev.json.JSONArray"%>
<%@page import="net.minidev.json.JSONObject"%>
<%
	String serverToken = Constants.getServerToken();
	if (Utils.isEmpty(serverToken)) {
		serverToken = "未取得です";
	}
	String sntReq = (String) request.getAttribute("sntReq");
	String rcvRes = (String) request.getAttribute("rcvRes");
	String status = (String) request.getAttribute("status");
	String contentType = (String) request.getAttribute("contentType");
%>
<?xml version="1.0" encoding="utf-8"?>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
<script
	src="http://ajax.googleapis.com/ajax/libs/jqueryui/1/jquery-ui.min.js"></script>
<script
	src="http://ajax.googleapis.com/ajax/libs/jqueryui/1/i18n/jquery.ui.datepicker-ja.min.js"></script>
<script>
	$(function() {
		$("#datepicker").datepicker();
	});
</script>
<title>LINE WORKS API Test Tool</title>
</head>
<body>
	<h1>LINE WORKS API Test Tool</h1>
	<hr />
	<h2>サーバ情報</h2>
	<ul>
		<li>サーバ名：<%=Constants.SERVER_NAME%></li>
		<li>サーバID：<%=Constants.SERVER_ID%></li>
		<li>サーバトークン：<%=serverToken%></li>
		<li style="list-style: none">
			<form method="get" action="/app/getServerToken">
				<button type='submit' name='action'>サーバトークン更新</button>
			</form>
		</li>
	</ul>
	<hr />
	<h2>Test Tool</h2>
	<!--
	<div style="text-decoration: none"
		onclick="var style = this.parentNode.getElementsByTagName('div')[1].style; style.display=(style.display ==='block' ?'none':'block')">
		 -->
	<h3>監査API</h3>
	<!--  <div style="display: none"> -->
	<div>
		<h4>利用するAPIの種類</h4>
		<form method="post" action="/app/utilTool">
			<ul>
				<li style="list-style: none"><input type="radio"
					name="targetUrl" value="<%=Constants.AUDIT_ADMIN_LOG_URL%>" checked>adminログ</li>
				<li style="list-style: none"><input type="radio"
					name="targetUrl" value="<%=Constants.AUDIT_AUTH_LOG_URL%>">authログ</li>
				<li style="list-style: none"><input type="radio"
					name="targetUrl" value="<%=Constants.AUDIT_HOME_LOG_URL%>">homeログ</li>
				<li style="list-style: none"><input type="radio"
					name="targetUrl" value="<%=Constants.AUDIT_DRIVE_LOG_URL%>">driveログ</li>
				<li style="list-style: none"><input type="radio"
					name="targetUrl" value="<%=Constants.AUDIT_CALENDER_LOG_URL%>">calendarログ</li>
				<li style="list-style: none"><input type="radio"
					name="targetUrl" value="<%=Constants.AUDIT_CONTACT_LOG_URL%>">contactログ</li>
				<li style="list-style: none"><input type="radio"
					name="targetUrl" value="<%=Constants.AUDIT_FORM_LOG_URL%>">formログ</li>
				<li style="list-style: none"><input type="radio"
					name="targetUrl" value="<%=Constants.AUDIT_SHARE_LOG_URL%>">shareログ</li>
				<li style="list-style: none"><input type="radio"
					name="targetUrl" value="<%=Constants.AUDIT_NOTE_LOG_URL%>">noteログ</li>
				<li style="list-style: none"><input type="radio"
					name="targetUrl" value="<%=Constants.AUDIT_RCVMAIL_LOG_URL%>">received-mailログ</li>
				<li style="list-style: none"><input type="radio"
					name="targetUrl" value="<%=Constants.AUDIT_MESSAGE_LOG_URL%>">messageログ</li>
				<li style="list-style: none"><input type="radio"
					name="targetUrl" value="<%=Constants.AUDIT_SNTMAIL_LOG_URL%>">sent-mailログ</li>
				<li style="list-style: none">[startDate]<input type="text"
					name="startDate" size="20" id="datepicker" value="20180101">
					[endDate]<input type="text" name="endDate" size="20"
					id="datepicker" value="20180131"></li>
				<li style="list-style: none"><button type='submit'
						name='action'>対象期間のログを取得</button></li>
			</ul>
		</form>
	</div>
	<!-- 	</div>
 -->
	<h3>グループ追加API</h3>
	<div>
		<h4>利用するAPIの種類</h4>
		<form method="post" action="/app/utilTool">
			<ul>
				<li style="list-style: none"><input type="radio"
					name="targetUrl" value="<%=Constants.GRP_ADD_URL%>test0515" checked>グループ追加</li>
				<li style="list-style: none">[JSON]<br /> <textarea rows="10"
						cols="100" name="obj"></textarea></li>
				<li style="list-style: none"><button type='submit'
						name='action'>グループ追加</button></li>
				<input type="hidden" name="type" value="addGrp">
			</ul>
		</form>
	</div>

	<h3>トークルーム生成API</h3>
	<div>
		<h4>利用するAPIの種類</h4>
		<form method="post" action="/app/utilTool">
			<ul>
				<li style="list-style: none"><input type="radio"
					name="targetUrl" value="<%=Constants.CREATE_ROOM_URL%>" checked>トークルーム生成</li>
				<li style="list-style: none">[JSON]<br /> <textarea rows="10"
						cols="100" name="obj"></textarea></li>
				<li style="list-style: none"><button type='submit'
						name='action'>トークルーム生成</button></li>
				<input type="hidden" name="type" value="addGrp">
			</ul>
		</form>
	</div>

	<h3>役職操作</h3>
	<div>
		<h4>役職追加API</h4>
		<form method="post" action="/app/utilTool" accept-charset="UTF-8">
			<ul>
				<li style="list-style: none"><input type="radio"
					name="targetUrl" value="<%=Constants.POSITION_ADD_URL%>" checked>役職追加</li>
				<li style="list-style: none">[JSON]<br /> <textarea rows="10"
						cols="100" name="obj" document.charset="UTF-8"></textarea></li>
				<li style="list-style: none"><button type='submit'
						name='action'>役職追加</button></li>
				<input type="hidden" name="type" value="addGrp">
			</ul>
		</form>
	</div>
	<div>
		<h4>役職照会API</h4>
		<form method="post" action="/app/utilTool">
			<ul>
				<li style="list-style: none"><input type="radio"
					name="targetUrl" value="<%=Constants.POSITION_REF_URL%>" checked>役職照会</li>
				<li style="list-style: none"><button type='submit'
						name='action'>役職照会</button></li>
				<input type="hidden" name="type" value="ref">
			</ul>
		</form>
	</div>

	<h3>メンバ操作</h3>
	<div>
		<h4>メンバ部分修正API</h4>
		<form method="post" action="/app/utilTool">
			<ul>
				<li style="list-style: none"><input type="radio"
					name="targetUrl" value="<%=Constants.MEMBER_CHANGE_URL%>" checked>メンバ部分修正</li>
				<li style="list-style: none">[JSON]<br /> <textarea rows="10"
						cols="100" name="obj"></textarea></li>
				<li style="list-style: none"><button type='submit'
						name='action'>メンバ部分修正</button></li>
				<input type="hidden" name="type" value="patch">
			</ul>
		</form>
	</div>
	<%
		if (!Utils.isEmpty(sntReq)) {
	%>
	<hr>
	<h4>Request</h4>
	<%=sntReq%>
	<%
		}
		if (!Utils.isEmpty(rcvRes)) {
	%>
	<h4>ResultHeader</h4>
	<%
		if (!Utils.isEmpty(status)) {
	%>
	<%=status%><br>
	<%
		}
			if (!Utils.isEmpty(contentType)) {
	%>
	Content-Type:<%=contentType%>

	<%
		}
	%>
	<h4>ResultBody</h4>
	<xmp> <%=rcvRes%> </xmp>
	<%
		}
	%>
</body>
</html>