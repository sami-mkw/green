<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" errorPage="/jsp/error.jsp"%>
<%@page import="jp.co.atraente.green.saml.common.Constants"%>
<%
	String samlRes = (String) request.getAttribute("SAMLResponse");
	String relayState = (String) request.getAttribute("RelayState");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript">

</script>
<title>POST</title>
</head>
<body>
	<form method="post" action="https://auth.worksmobile.com/acs/red.wmjsales.xyz">
		<input type="hidden" name="SAMLResponse" value="<%=samlRes%>">
		<input type="hidden" name="RelayState" value="<%=relayState%>">
		<button type='submit' name='action'>SAML Response返却</button>
	</form>

</body>
</html>