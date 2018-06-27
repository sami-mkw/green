<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isErrorPage="true"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript">

</script>
<title>エラー画面</title>
</head>
<body>
	<h1>エラー画面</h1>
	<hr />
	<h2>エラー情報</h2>
	<p>
		例外が発生しました。<br>
		<%
			if (exception != null) {
		%><%=exception.getMessage()%>
		<%
			}
		%>
	</p>

</body>
</html>