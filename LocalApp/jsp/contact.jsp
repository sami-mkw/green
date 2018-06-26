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
	String result = (String) request.getAttribute("result");
	if (Utils.isEmpty(serverToken)) {
		serverToken = "未取得です";
	}
	if (Utils.isEmpty(result)) {
		result = "未取得です";
	}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript">

</script>
<title>Developer管理画面(アドレス帳)</title>
</head>
<body>
	<a href="http://127.0.0.1:8080/app/jsp/org.jsp">組織管理</a> ｜
	<a href="http://127.0.0.1:8080/app/jsp/admin.jsp">BOT管理</a>
	<br>
	<h1>Developer管理画面(アドレス帳)</h1>
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
	<h2>アドレス帳管理</h2>
	<h3>移行の際の個人連絡先の追加</h3>
	<form method="post" action="/app/addPrivateContact">
		<ul>
			<li style="list-style: none">[JSON]<br /> <textarea rows="15"
					cols="100" name="obj"></textarea></li>
			<li style="list-style: none"><button type='submit' name='action'>追加</button></li>
		</ul>
	</form>
	<h3>固定メニュー照会</h3>
	<form method="post" action="/app/v3/getBotMenu">
		<ul>
			<li style="list-style: none">[botNo] <input type="text"
				size="50" name="botNo"></input></li>
			<li style="list-style: none"><button type='submit' name='action'>固定メニュー照会</button></li>
			<li style="list-style: none">[JSON]<br /> <textarea rows="10"
					cols="100" name="obj"><%=result%></textarea></li>
		</ul>
	</form>
	<h3>【text】メッセージ送信テスト</h3>
	<form method="post" action="/app/sndBotMsg">
		<ul>
			<li style="list-style: none">[botNo] <input type="text"
				size="10" name="botNo"></input>，[accountId] <input type="text"
				size="10" name="accountId"></input></li>
			<li style="list-style: none">[message]<input type="text"
				size="50" name="message"></input></li>
			<li style="list-style: none"><button type="submit" name="action">送信</button></li>
		</ul>
	</form>
	<h3>【その他】メッセージ送信テスト</h3>

	<form method="post" action="/app/v3/sndBotMsg">
		<ul>
			<li style="list-style: none">[JSON]<br /> <textarea rows="20"
					cols="100" name="obj"></textarea></li>
			<li style="list-style: none"><button type='submit' name='action'>送信</button></li>
		</ul>
	</form>
	<h3>csvファイルからメッセージ送信テスト</h3>
	<form method="POST" enctype="multipart/form-data"
		action="/app/sndBotMsgFromCsv">
		<ul>
			<li style="list-style: none">[csv file] <input type="file"
				name="upfile"><br /> <br /></li>
			<li style="list-style: none"><button type="submit" name="action">メッセージ送信(from
					csv)テスト</button></li>
		</ul>
	</form>
</body>
</html>