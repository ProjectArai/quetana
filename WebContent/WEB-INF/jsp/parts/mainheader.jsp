<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.LoginUserInfoBean" %>
<%
//セッションスコープからユーザ情報を取得
LoginUserInfoBean loginUserInfo = (LoginUserInfoBean) session.getAttribute("loginUserInfo");
%>
<div id="HeaderBase">
	<div id="HeaderLogo">Quetana</div>
	<div id="HBMenuBase">
		<input id="HBMenuCheck" type="checkbox" class="unshown">
		<label for="HBMenuCheck">
			<div id="HBMenuBtn">
				<div id="HBMenuMark">
					<hr id="HBMenuMarkLine">
					<hr id="HBMenuMarkLine">
					<hr id="HBMenuMarkLine">
					<!-- ≡ -->
				</div>
			</div>
		</label>
		<label for="HBMenuCheck" id="HBMenuBG"></label>
		<div id="HBMenuContents">
			<div id=HBMenuUser>
				<div id="HBMIcon"><img src="<%= loginUserInfo.getStIconURL() %>" width="40" height="40" class="IconRound"></div>
				<div id="HBMAccountName"><%= loginUserInfo.getStAccountName() %></div>
			</div>
			<input id="HBMenuElement" type="button" value="ホーム" onClick="location.href='/quetana/Contents/Home'">
			<input id="HBMenuElement" type="button" value="マイプロフィール" onClick="location.href='/quetana/Contents/UserProfile'">
			<input id="HBMenuElement" type="button" value="投稿ページ" onClick="location.href='/quetana/Contents/Post'">
			<div id=HBMenuBreak></div>
			<input id="HBMenuElement" type="button" value="設定">
			<div id=HBMenuBreak></div>
			<input id="HBMenuElement" type="button" value="ログアウト" onClick="location.href='/quetana/Logout'">
			<div id=HBMenuMSG>Copyright 2018 Quetana by ProjectArai</div>
		</div>
	</div>
<!--
	<div id="LoginUser">
		<div id="AccountName"><%= loginUserInfo.getStAccountName() %></div>
		<div id="Icon"><img src="<%= loginUserInfo.getStIconURL() %>" width="30" height="30" class="HeaderIconRound"></div>
	</div>
-->
</div>
<div id="HiddenHeader"></div>

