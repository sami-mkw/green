<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" errorPage="/jsp/error.jsp"%>
<%@ page import="com.nimbusds.jose.util.Base64URL"%>
<%@ page import="com.nimbusds.jose.util.Base64"%>
<%@page import="jp.co.atraente.green.lw.testap.common.Constants"%>
<%@page import="jp.co.atraente.green.lw.testap.common.Utils"%>
<%@page import="net.minidev.json.JSONArray"%>
<%@page import="net.minidev.json.JSONObject"%>
<%
	String signature = (String) request.getAttribute("signature");
	String result = (String) request.getAttribute("result");
	String subMail = (String) request.getAttribute("subMail");
	if (Utils.isEmpty(signature)) {
		signature = "未取得です";
	}
	if (Utils.isEmpty(result)) {
		result = "未取得です";
	}
	if (Utils.isEmpty(subMail)) {
		subMail = "未取得です";
	}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript">

</script>
<title>LW API関連 ユーティリティ</title>
</head>
<body>
	<a href="http://127.0.0.1:8080/app/jsp/org.jsp">組織管理</a> ｜
	<a href="http://127.0.0.1:8080/app/jsp/admin.jsp">BOT管理</a>
	<br>
	<h1>LW API関連 ユーティリティ</h1>
	<hr />
	<h2>BOT関連</h2>
	<h3>メッセージ受信 Callback 署名</h3>
	<form method="post" action="/app/botSign">
		<ul>
			<li style="list-style: none">[API ID] <input type="text"
				size="50" name="apiId" value="<%=Constants.API_ID%>"></input></li>
			<li style="list-style: none">[Base Strings]<br /> <textarea
					rows="10" cols="100" name="base"></textarea></li>
			<li style="list-style: none"><button type='submit' name='action'>署名作成</button></li>
			<li style="list-style: none">[Signature] <%=signature%></li>
		</ul>
	</form>
</body>
</html>