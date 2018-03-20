<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.LoginUserInfoBean" %>
<%
//セッションスコープからユーザ情報を取得
LoginUserInfoBean loginUserInfo = (LoginUserInfoBean) session.getAttribute("loginUserInfo");
%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width, maximum-scale=1.0">
		<meta name="format-detection" content="telephone=no">
		<title>Quetana</title>
	</head>
	<body>
		<div class="HeaderBase">
			<div class="BtnBase">
				<input class="BackBtn" type="button" value="×" onClick="history.back()">
			</div>
			<div class="HeaderLogo">Quetana</div>
			<div class="BtnBase">
<!--
				<img class="HeaderBtn" src="/quetana/img/fix.jpg">
 -->
				<input type="submit" name="fixBtn" class="fix" value="">
			</div>
		</div>
		<script src="/quetana/js/common.js"></script>
	</body>
</html>
