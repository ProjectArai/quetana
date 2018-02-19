<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.UserInfoBean" %>
<%
//セッションスコープからユーザ情報を取得
UserInfoBean userInfo = (UserInfoBean) session.getAttribute("userInfo");
%>
<div id="Header">
	<div id="Logo">Quetana</div>
	<div id="nav-drawer">
		<input id="nav-input" type="checkbox" class="nav-unshown">
		<label id="nav-open" for="nav-input"><div id="MenuButton">≡</div></label>
		<label class="nav-unshown" id="nav-close" for="nav-input"></label>
		<div id="nav-content">
			<form action="/quetana/Contents/Home" method="get">
				<input id="submit_btn" type="submit" name="Home"value="ホーム">
			</form>
			<form action="/quetana/Contents/UserProfile" method="get">
				<input id="submit_btn" type="submit" name="mypage"value="マイページ">
			</form>
			<div id="MenuContents">投稿ページ</div>
			<form action="/quetana/Logout" method="get">
				<input id="submit_btn" type="submit" name="logout"value="ログアウト">
			</form>
		</div>
	</div>
	<div id="LoginUser">
		<div id="UserID"><%= userInfo.getIdUser() %></div>
		<div id="Icon"><img src="<%= userInfo.getStIconURL() %>" width="30" height="30"></div>
	</div>
</div>
