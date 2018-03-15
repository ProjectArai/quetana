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
		<meta name="viewport" content="width=device-width, maximum-scale=1.0">
		<meta name="format-detection" content="telephone=no">
		<title>Quetana</title>
		<link rel="stylesheet" type="text/css" href="/quetana/css/common/com.css">
		<link rel="stylesheet" type="text/css" href="/quetana/css/common/com_other.css">
		<link href="https://fonts.googleapis.com/css?family=Lato:400,900" rel="stylesheet">
		<script src="/quetana/js/common.js"></script>
	</head>
	<body onLoad="dispErrMsg('<%= errMsg %>')">
		<div class="Foundation">
			<div class="ContentsMargin"></div>
			<div class="ContentsArea">
				<div class="TitleArea">
					<font class="XL">Quetana</font><font class="M">&nbsp;のアカウントを作成</font>
				</div>
				<form name="inputForm" action="/quetana/CreateAccount" method="post" onsubmit="return checkPassword()">
					<input type="text" name="stAccountName" maxlength="8" placeholder="アカウント名" required value="<%=  stAccountName %>"><br>
					<input type="text" name="stMailAddress" maxlength="128" placeholder="メールアドレス" required value="<%=  stMailAddress %>"><br>
					<input type="text" name="stPassword" maxlength="16" placeholder="パスワード" required value="<%=  stPassword %>"><br>
					<input type="text" name="stConfirmPassword" maxlength="16" placeholder="パスワード(確認用)" required value="<%=  stConfirmPassword %>"><br>
					<div class="MsgArea">
						<% if(errMsg != null) { %>
							<font class="ErrMsg"><%= errMsg %></font>
						<% } %>
					</div>
					<input type="submit" name="createAccount" value="アカウント作成">
				</form>
				<div class="MsgArea">
					<a href="<%=request.getContextPath()%>/Login">キャンセル</a>
				</div>
			</div>
		</div>
		<jsp:include page="../jsp/parts/mainheader.jsp" flush="true" />
	</body>
</html>