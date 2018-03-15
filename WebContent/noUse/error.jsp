<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String errMsg = (String)session.getAttribute("errMsg");
%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width">
		<title>Quetana</title>
		<link rel="stylesheet" type="text/css" href="/quetana/css/base.css">
		<link rel="stylesheet" type="text/css" href="/quetana/css/error.css">
		<link href="https://fonts.googleapis.com/css?family=Lato:400,900" rel="stylesheet">
	</head>
	<body>
		<div id="HeaderBase">
			<div id="HeaderLogo">Quetana</div>
		</div>
		<div id="HiddenHeader"></div>
		<div id="ContentsBase">
			<div id="ContentsMargin"></div>
			<div id="Contents">
				<font id="Quetana">Sorry</font><br>
				<font id="AA">技術的な問題が発生しました</font><br>
				<font id="ErrMsg"><br>繰り返し同様の操作をしても改善されない場合、<br>お手数ですがシステム管理者までご連絡下さい。</font>
				<div id="MsgArea">
					<font id="ErrMsg"><%= errMsg %></font>
				</div>
				<form action="/quetana/Error" method="post">
					<input id="submit_btn" type="submit" name="login" value="戻る">
				</form>			</div>
		</div>
	</body>
</html>