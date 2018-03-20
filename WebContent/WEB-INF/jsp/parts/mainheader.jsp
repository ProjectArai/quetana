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
<!--
		<link rel="stylesheet" type="text/css" href="/quetana/css/common/menu.css">
 -->
	</head>
	<body>
		<div class="HeaderBase">
			<div class="BtnBase">
			<% if (loginUserInfo != null) { %>
				<input class="BackBtn" type="button" value="&lt;" onClick="history.back()">
			<% } %>
			</div>
			<div class="HeaderLogo">Quetana</div>
			<% if (loginUserInfo != null) { %>
				<div class="BtnBase">
					<input id="MenuCheck" type="checkbox" class="unshown">
					<label for="MenuCheck">
						<div class="MenuBtn">
							<hr class="HamburgerMark">
							<hr class="HamburgerMark">
							<hr class="HamburgerMark">
						</div>
					</label>
					<label for="MenuCheck" class="ContentsCover"></label>
					<div class="MenuBase">
						<div class=MenuHeader>
							<img src="<%= loginUserInfo.getStIconURL() %>" class="MenuIcon">
							<div class="MenuUserName"><%= loginUserInfo.getStAccountName() %></div>
						</div>
						<input class="MenuLinkBtn" type="button" value="ホーム" onClick="location.href='/quetana/Contents/Home';closeMenu()">
						<input class="MenuLinkBtn" type="button" value="マイプロフィール" onClick="location.href='/quetana/Contents/UserProfile';closeMenu()">
						<input class="MenuLinkBtn" type="button" value="新規投稿" onClick="location.href='/quetana/Contents/Post';closeMenu()">
<!--
						<input class="MenuLinkBtn" type="button" value="メッセージ" onClick="location.href='/quetana/dmList.jsp';closeMenu()">
 -->
						<a class="ContentsLink" href="/quetana/dmList.jsp" onClick="closeMenu()">
							<div class="MenuLinkBtn">
								<div class="LinkLabel Left">メッセージ</div>
								<div class="LinkBadgeArea Left"><div class="Badge">99</div>
							</div>
						</a>
						<div class=MenuBlank></div>
						<input class="MenuLinkBtn" type="button" value="設定" onClick="location.href='/quetana/Error';closeMenu()">
						<div class=MenuBlank></div>
						<input class="MenuLinkBtn" type="button" value="ログアウト" onClick="location.href='/quetana/Logout'">
						<div class=MenuMSG>Copyright 2018 Quetana by ProjectArai</div>
					</div>
				</div>
			<% } %>
		</div>
		<script src="/quetana/js/common.js"></script>
	</body>
</html>
