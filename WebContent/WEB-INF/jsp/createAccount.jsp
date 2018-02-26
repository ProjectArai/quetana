<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.Map" %>
<%
String stAccountName = "";
String stMailAddress = "";
String stPassword = "";
String stConfirmPassword = "";
Map inParam = (Map)request.getAttribute("inParam");
if (inParam != null) {
	stAccountName = (String)inParam.get("stAccountName");
	stMailAddress = (String)inParam.get("stMailAddress");
	stPassword = (String)inParam.get("stPassword");
	stConfirmPassword = (String)inParam.get("stConfirmPassword");
}
String errMsg = (String)request.getAttribute("errMsg");
%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width">
		<title>Quetana</title>
		<link rel="stylesheet" type="text/css" href="/quetana/css/base.css">
		<link rel="stylesheet" type="text/css" href="/quetana/css/bf_login.css">
		<link href="https://fonts.googleapis.com/css?family=Lato:400,900" rel="stylesheet">
		<script src="/quetana/js/common.js"></script>
	</head>
	<body>
		<div id="Header">
			<div id="HeaderLogo">Quetana</div>
		</div>
		<div id="ContentsHeader"></div>
		<div id="Contents">
			<font id="Quetana">Quetana</font><font id="ToDo">&nbsp;のアカウントを作成</font>
			<form name="f1" action="/quetana/CreateAccount" method="post" onsubmit="return checkPassword()">
				<input type="text" name="stAccountName" maxlength="8" placeholder="アカウント名" required value="<%=  stAccountName %>"><br>
				<input type="text" name="stMailAddress" maxlength="128" placeholder="メールアドレス" required value="<%=  stMailAddress %>"><br>
				<input type="text" name="stPassword" maxlength="16" placeholder="パスワード" required value="<%=  stPassword %>"><br>
				<input type="text" name="stConfirmPassword" maxlength="16" placeholder="パスワード(確認用)" required value="<%=  stConfirmPassword %>"><br>
				<div id="MsgArea">
					<% if(errMsg != null) { %>
						<font id="ErrMsg"><%= errMsg %></font>
					<% } %>
				</div>
				<input id="submit_btn" type="submit" id="button" name="createAccount" size="10" value="アカウント作成">
			</form>
			<a href="<%=request.getContextPath()%>/Login">キャンセル</a>
		</div>
	</body>
</html>