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
	<h3>スケジュールAPI</h3>
	<div>
		<h4>利用するAPIの種類</h4>
		<form method="post" action="/app/createSchedule">
			<ul>
				<li style="list-style: none"><input type="radio"
					name="targetUrl"
					value="https://apis.worksmobile.com/<%=Constants.API_ID%>/calendar/createSchedule"
					checked>スケジュール追加</li>
				<li style="list-style: none">[JSON]<br /> <textarea rows="10"
						cols="100" name="obj"></textarea></li>
				<li style="list-style: none"><button type='submit'
						name='action'>スケジュール追加</button></li>
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