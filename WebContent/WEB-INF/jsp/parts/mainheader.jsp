<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.UserInfoBean" %>
<%
//セッションスコープからユーザ情報を取得
UserInfoBean userInfo = (UserInfoBean) session.getAttribute("userInfo");
%>
<div class="Header">
	<div class="Logo">Quetana</div>
	<div id="nav-drawer">
		<input id="nav-input" type="checkbox" class="nav-unshown">
		<label id="nav-open" for="nav-input"><div class="Menu">≡</div></label>
		<label class="nav-unshown" id="nav-close" for="nav-input"></label>
		<div id="nav-content">
			<div class="menubutton">マイページ</div>
			<div class="menubutton">投稿ページ</div>
			<div class="menubutton">ログアウト</div>
		</div>
	</div>
	<div class="LoginUser">
		<div class="Username"><%= userInfo.getStUserName() %></div>
		<div class="Icon"><img src="<%= userInfo.getStIconURL() %>" width="30" height="30"></div>
	</div>
</div>
<div class="HiddenHeader"></div>
