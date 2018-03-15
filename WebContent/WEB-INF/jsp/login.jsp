<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String errMsg = (String)request.getAttribute("errMsg");
String infoMsg = (String)session.getAttribute("infoMsg");
// sessionスコープに持ってるいらない情報を破棄
session.removeAttribute("infoMsg");
%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width, maximum-scale=1.0">
		<meta name="format-detection" content="telephone=no">
		<title>Quetana</title>
		<link rel="stylesheet" type="text/css" href="/quetana/css/common/com.css">
		<link rel="stylesheet" type="text/css" href="/quetana/css/common/com_other.css">
		<link href="https://fonts.googleapis.com/css?family=Lato:400,900" rel="stylesheet">
	</head>
	<body onLoad="dispInfoMsg('<%= infoMsg %>')">
		<div class="Foundation">
			<div class="ContentsMargin"></div>
			<div class="ContentsArea">
				<div class="TitleArea">
					<font class="XL">Quetana</font><font class="M">&nbsp;にログイン</font>
				</div>
				<form class="InputForm" action="/quetana/Login" method="post">
					<input type="text" name="stLoginUser" maxlength="128" placeholder="アカウント名 または メールアドレス" value="test0002"><br>
					<input type="password" name="stPassword" maxlength="16" placeholder="パスワード" value="test"><br>
					<div class="MsgArea">
						<% if(errMsg != null) { %>
							<font class="ErrMsg"><%= errMsg %></font>
						<% } %>
					</div>
					<input type="submit" name="login" value="ログイン">
				</form>
				<div class="MsgArea">
					<a href="<%=request.getContextPath()%>/CreateAccount">アカウント作成はこちら</a>
				</div>
			</div>
		</div>
		<jsp:include page="../jsp/parts/mainheader.jsp" flush="true" />
		<script src="/quetana/js/common.js"></script>
	</body>
</html>