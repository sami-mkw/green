<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" errorPage="/jsp/error.jsp"%>
<%@page import="jp.co.atraente.green.saml.common.Utils"%>
<%
	String responseType = (String) request.getAttribute("responseType");
	if (Utils.isEmpty(responseType)) {
		responseType = "入力なし";
	}
	String clientId = (String) request.getAttribute("clientId");
	if (Utils.isEmpty(clientId)) {
		clientId = "入力なし";
	}
	String relayState = (String) request.getAttribute("relayState");
	if (Utils.isEmpty(relayState)) {
		relayState = "入力なし";
	}
	String authnReq = (String) request.getAttribute("authnReq");
	if (Utils.isEmpty(authnReq)) {
		authnReq = "入力なし";
	}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript">

</script>
<title>ログイン画面</title>
</head>
<body>
	<h1>ログイン画面(SAMLテスト用)</h1>
	<hr />
	<h2>SAML Responseに必要な情報を入力してください</h2>
	<form method="post" action="/idp/sndAuthnRes">
		<ul>
			<li style="list-style: none">[inResponseTo]<br /> <input
				type="text" name="inResponseTo" size="50"></input></li>
			<li style="list-style: none">[email]<br /> <input type="text"
				name="email" size="50"></input></li>
			<li style="list-style: none">[ACS]<br /> <input type="text"
				name="acs" size="50" value="https://worksmobile.com/acs"></input></li>
			<li style="list-style: none">[RelayState]<br /> <input
				type="text" name="relayState" size="50" value="<%=relayState%>"></input></li>
			<li style="list-style: none"><button type='submit' name='action'>SAML
					Response生成＆返却</button></li>
		</ul>
	</form>
	<hr />
	<ul>
		<li>response_type：<%=responseType%></li>
		<li>client_id：<%=clientId%></li>
		<li>RelayState：<%=relayState%></li>
		<li>SAMLRequest：<br /><%=authnReq%></li>
	</ul>
</body>
</html>