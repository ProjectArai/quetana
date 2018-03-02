<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
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
	</head>
	<body>
		<jsp:include page="../jsp/parts/mainheader.jsp" flush="true" />
		<div id="ContentsBase">
			<div id="ContentsMargin"></div>
			<div id="Contents">
				<font id="Quetana">Quetana</font><font id="ToDo">&nbsp;にログイン</font>
				<form action="/quetana/Login" method="post">
					<input type="text" name="stLoginUser" maxlength="128" placeholder="ユーザ名 または メールアドレス" value="r-zoon"><br>
					<input type="text" name="stPassword" maxlength="16" placeholder="パスワード" value="ryota1229"><br>
					<div id="MsgArea">
						<% if(errMsg != null) { %>
							<font id="ErrMsg"><%= errMsg %></font>
						<% } %>
					</div>
					<input id="submit_btn" type="submit" name="login" value="ログイン">
				</form>
				<div id="MsgArea">
					<a href="<%=request.getContextPath()%>/CreateAccount">アカウント作成はこちら</a>
				</div>
			</div>
		</div>
	</body>
</html>