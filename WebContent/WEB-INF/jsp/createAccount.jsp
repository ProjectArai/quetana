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
		<div id="Header">
			<div id="HeaderLogo">Quetana</div>
		</div>
		<div id="ContentsHeader"></div>
		<div id="Contents">
			<font id="Quetana">Quetana</font><font id="ToDo">&nbsp;のアカウントを作成</font>
			<form name="f1" action="/quetana/CreateAccount" method="post" onsubmit="return checkPassword()">
				<input type="text" name="stUserName" maxlength="8" placeholder="ユーザ名" required><br>
				<input type="text" name="stMailAddress" maxlength="128" placeholder="メールアドレス" required><br>
				<input type="text" name="stPassword" maxlength="16" placeholder="パスワード" required><br>
				<input type="text" name="stConfirmPassword" maxlength="16" placeholder="パスワード（確認）" required><br>
				<input id="submit_btn" type="submit" id="button" name="createAccount" size="10" value="アカウント作成">
			</form>
			<script>
			// パスワードの一致確認
			function checkPassword() {
				if(f1.stPassword.value != f1.stConfirmPassword.value) {
					alert("パスワードと確認用パスワードが一致しません");
					return false;
				} else {
					return true;
				}
			}
			</script>
			<a href="<%=request.getContextPath()%>/Login">キャンセル</a>
		</div>
	</body>
</html>