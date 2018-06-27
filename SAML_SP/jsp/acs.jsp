<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" errorPage="/jsp/error.jsp"%>
<%@page import="jp.co.atraente.green.saml.common.Utils"%>
<%
	String res = (String) request.getAttribute("res");
	if (Utils.isEmpty(res)) {
		res = "入力なし";
	}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript">

</script>
<title>AssertionConsumerService</title>
</head>
<body>
	<h1>AssertionConsumerService(SAMLテスト用)</h1>
	<hr />
	<h2>SAML Response</h2>
	<form method="post" action="/idp/sndAuthnRes">
		<ul>
			<li style="list-style: none">[Response]<br /> <textarea rows="10"
					cols="100" name="res"><%=res%></textarea></li>
		</ul>
	</form>
</body>
</html>