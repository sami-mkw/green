<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" errorPage="/jsp/error.jsp"%>
<%@ page import="com.nimbusds.jose.util.Base64URL"%>
<%@ page import="com.nimbusds.jose.util.Base64"%>
<%@page import="jp.co.atraente.green.lw.testap.common.Constants"%>
<%@page import="jp.co.atraente.green.lw.testap.common.Utils"%>
<%@page import="net.minidev.json.JSONArray"%>
<%@page import="net.minidev.json.JSONObject"%>
<%
	String token = Constants.getServiceToken();
	String user = (String) request.getAttribute("user");
	if (Utils.isEmpty(token)) {
		token = "未取得です";
	}
	if (Utils.isEmpty(user)) {
		user = "未取得です";
	}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript">

</script>
<title>Developer Test Tool (Drive)</title>
</head>
<body>
	<a href="http://127.0.0.1:8080/app/jsp/org.jsp">組織管理</a> ｜
	<a href="http://127.0.0.1:8080/app/jsp/admin.jsp">BOT管理</a>
	<br>
	<h1>Developer Test Tool (Drive)</h1>
	<hr />
	<h2>Service API</h2>
	<ul>
		<li>トークン：<%=token%></li>
		<li style="list-style: none">
			<form method="get" action="/app/getCode">
				<button type='submit' name='action'>AccessToken更新</button>
			</form>
		</li>
	</ul>
	<hr />
	<h3>DRIVE</h3>
	<form method="post" action="/app/fileUpload">
		<ul>
			<li style="list-style: none"><button type='submit' name='action'>File Upload</button></li>
			<li style="list-style: none">[JSON]<br /> <textarea rows="10"
					cols="100"><%=user%></textarea></li>
		</ul>
	</form>

</body>
</html>