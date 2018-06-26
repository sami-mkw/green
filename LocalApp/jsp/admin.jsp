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
	JSONArray botList = null;
	if (Utils.isEmpty(serverToken)) {
		serverToken = "未取得です";
	} else {
		Utils.setBotList();
		botList = Utils.getBotList();
	}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript">

</script>
<title>Developer管理画面(BOT管理)</title>
</head>
<body>
	<a href="http://127.0.0.1:8080/app/jsp/org.jsp">組織管理</a> ｜
	<a href="http://127.0.0.1:8080/app/jsp/admin.jsp">BOT管理</a>
	<br>
	<h1>Developer 管理画面(BOT管理)</h1>
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
	<h2>BOT管理</h2>
	<h3>BOT一覧</h3>
	<form method="post" action="/app/registBot">
		<ul>
			<%
				if (null != botList) {
					for (int i = 0; i < botList.size(); i++) {
						JSONObject jsonObject = (JSONObject) botList.get(i);
			%>
			<li style="list-style: none"><img border="0"
				src="<%=jsonObject.get("photoUrl")%>" width="32" height="32">
				[botNo]<%=jsonObject.get("botNo")%>，[name]<%=jsonObject.get("name")%>，[status]<%=jsonObject.get("status")%></li>
			<%
				}
				} else {
			%>
			<li>botはまだ登録されていません。</li>
			<%
				}
			%>
			<li>新規登録する</li>
			<li style="list-style: none">[name] <input type="text" size="50"
				name="name"></input></li>
			<li style="list-style: none">[photoUrl] <input type="text"
				size="50" name="photoUrl"></input></li>
			<li style="list-style: none"><button type='submit' name='action'>新規Bot登録</button></li>
		</ul>
	</form>
	<h3>ドメイン登録</h3>
	<form method="post" action="/app/registBot2Domain">
		<ul>
			<li style="list-style: none">[botNo] <input type="text"
				size="10" name="botNo"></input></li>
			<li style="list-style: none"><button type='submit' name='action'>ドメイン登録</button></li>
		</ul>
	</form>
	<h3>メッセージ受信サーバ登録</h3>
	<form method="post" action="/app/setCallback">
		<ul>
			<li style="list-style: none">[botNo] <input type="text"
				size="10" name="botNo"></input></li>
			<li style="list-style: none">[callbackUrl] <input type="text"
				size="50" name="callbackUrl"></input></li>
			<li style="list-style: none"><button type="submit" name="action">メッセージ受信サーバ登録</button></li>
		</ul>
	</form>
	<h3>メッセージ送信テスト</h3>
	<form method="post" action="/app/sndBotMsg">
		<ul>
			<li style="list-style: none">[botNo] <input type="text"
				size="10" name="botNo"></input>，[accountId] <input type="text"
				size="10" name="accountId"></input></li>
			<li style="list-style: none">[message]<input type="text"
				size="50" name="message"></input></li>
			<li style="list-style: none"><button type="submit" name="action">メッセージ送信テスト</button></li>
		</ul>
	</form>
	<h3>csvファイルからメッセージ送信テスト</h3>
	<form method="POST" enctype="multipart/form-data"
		action="/app/sndBotMsgFromCsv">
		<ul>
			<li style="list-style: none">[csv file] <input type="file"
				name="upfile"><br />
			<br /></li>
			<li style="list-style: none"><button type="submit" name="action">メッセージ送信(from
					csv)テスト</button></li>
		</ul>
	</form>
	<hr>

	<h3>LINE 連携 ON</h3>
	<form method="post" action="/app/enableLine">
		<ul>
			<li style="list-style: none">[JSON]<br /> <textarea rows="10"
					cols="100" name="obj">{}</textarea></li>
			<li style="list-style: none"><button type="submit" name="action">連携ON</button></li>
		</ul>
	</form>

		<hr>

	<h3>ドメインチェック</h3>
	<form method="post" action="/app/checkDomain">
		<ul>
			<li style="list-style: none">[JSON]<br /> <textarea rows="10"
					cols="100" name="obj">{}</textarea></li>
			<li style="list-style: none"><button type="submit" name="action">Check</button></li>
		</ul>
	</form>


</body>
</html>