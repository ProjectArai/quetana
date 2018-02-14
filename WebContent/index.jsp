<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width">
		<title>Quetana</title>
		<link rel="stylesheet" type="text/css" href="/quetana/css/top.css">
	</head>
	<body>
		<div class="Header">
			<div class="HeaderLogo">Quetana</div>
		</div>
		<div class="ContentsA">
			<font size="6" >Quetana</font><font size="4" >&nbsp;にログイン</font>
			<form action="/quetana/Login" method="post">
				<input type="text" name="stLoginUser" maxlength="128" placeholder="ユーザ名 または メールアドレス"><br>
				<input type="text" name="stPassword" maxlength="16" placeholder="パスワード"><br>
				<input id="submit_btn" type="submit" id="button" name="login" size="10" value="ログイン">
			</form>
			<a href="createaccount.jsp">アカウント作成はこちら</a>
		</div>
	</body>
</html>