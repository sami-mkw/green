<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" errorPage="/jsp/error.jsp"%>
<%@ page import="com.nimbusds.jose.util.Base64URL"%>
<%@ page import="com.nimbusds.jose.util.Base64"%>
<%@page import="sami.lineworks.testap.common.Constants"%>
<%@page import="sami.lineworks.testap.common.Utils"%>
<%@page import="net.minidev.json.JSONArray"%>
<%@page import="net.minidev.json.JSONObject"%>
<%
	String serverToken = Constants.getServerToken();
	String result = (String) request.getAttribute("result");
	String subMail = (String) request.getAttribute("subMail");
	if (Utils.isEmpty(serverToken)) {
		serverToken = "未取得です";
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
<title>Developer管理画面(メンバー管理)</title>
</head>
<body>
	<a href="http://127.0.0.1:8080/app/jsp/org.jsp">組織管理</a> ｜
	<a href="http://127.0.0.1:8080/app/jsp/admin.jsp">BOT管理</a>
	<br>
	<h1>Developer 管理画面(メンバー管理)</h1>
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
	<h2>メンバー管理</h2>
	<h3>メンバー追加</h3>
	<form method="post" action="/app/registOrg">
		<ul>
			<li style="list-style: none">[exKey] <input type="text"
				size="50" name="exKey"></input></li>
			<li style="list-style: none">[JSON]<br /> <textarea rows="10"
					cols="100" name="obj"></textarea></li>
			<li style="list-style: none"><button type='submit' name='action'>新規組織登録</button></li>
		</ul>
	</form>
	<h3>組織修正</h3>
	<form method="post" action="/app/updateOrg">
		<ul>
			<li style="list-style: none">[exKey] <input type="text"
				size="50" name="exKey"></input></li>
			<li style="list-style: none">[JSON]<br /> <textarea rows="10"
					cols="100" name="obj"></textarea></li>
			<li style="list-style: none"><button type='submit' name='action'>組織情報修正</button></li>
		</ul>
	</form>
	<h3>組織削除</h3>
	<form method="post" action="/app/deleteOrg">
		<ul>
			<li style="list-style: none">[exKey] <input type="text"
				size="50" name="exKey"></input></li>
			<li style="list-style: none"><button type='submit' name='action'>組織削除</button></li>
		</ul>
	</form>
	<h3>組織照会</h3>
	<form method="post" action="/app/getOrg">
		<ul>
			<li style="list-style: none">[exKey] <input type="text"
				size="50" name="exKey"></input></li>
			<li style="list-style: none"><button type='submit' name='action'>組織照会</button></li>
			<li style="list-style: none">[JSON]<br /> <textarea rows="10"
					cols="100" name="obj"><%=result%></textarea></li>
		</ul>
	</form>
	<h3>組織移動</h3>
	<form method="post" action="/app/moveOrg">
		<ul>
			<li style="list-style: none">[exKey] <input type="text"
				size="50" name="exKey"></input></li>
			<li style="list-style: none">[JSON]<br /> <textarea rows="10"
					cols="100" name="obj"></textarea></li>
			<li style="list-style: none"><button type='submit' name='action'>組織移動</button></li>

		</ul>
	</form>
	<h3>組織のサブメールアドレス追加</h3>
	<form method="post" action="/app/registSubMail">
		<ul>
			<li style="list-style: none">[exKey] <input type="text"
				size="50" name="exKey"></input></li>
			<li style="list-style: none">[email] <input type="text"
				size="50" name="email"></input></li>
			<li style="list-style: none"><button type='submit' name='action'>サブメールアドレス登録</button></li>
		</ul>
	</form>
	<h3>組織のサブメールアドレス照会</h3>
	<form method="post" action="/app/getSubMail">
		<ul>
			<li style="list-style: none">[exKey] <input type="text"
				size="50" name="exKey"></input></li>
			<li style="list-style: none"><button type='submit' name='action'>サブメールアドレス照会</button></li>
			<li style="list-style: none">[JSON]<br /> <textarea rows="10"
					cols="100"><%=subMail%></textarea></li>
		</ul>
	</form>
	<h3>組織のサブメールアドレス削除</h3>
	<form method="post" action="/app/deleteSubMail">
		<ul>
			<li style="list-style: none">[exKey] <input type="text"
				size="50" name="exKey"></input></li>
			<li style="list-style: none">[email] <input type="text"
				size="50" name="email"></input></li>
			<li style="list-style: none"><button type='submit' name='action'>サブメールアドレス削除</button></li>
		</ul>
	</form>
</body>
</html>