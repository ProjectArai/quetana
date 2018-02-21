<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.UserInfoBean,model.UserProfileBean" %>
<%
//セッションスコープからユーザ情報を取得
UserInfoBean userInfo = (UserInfoBean) session.getAttribute("userInfo");
//リクエストスコープからプロフィールを取得
UserProfileBean myProfile = (UserProfileBean) request.getAttribute("myProfile");
%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width">
		<title>Quetana</title>
		<link rel="stylesheet" type="text/css" href="/quetana/css/main.css">
		<link rel="stylesheet" type="text/css" href="/quetana/css/menu.css">
	</head>
	<body>
		<jsp:include page="../jsp/parts/mainheader.jsp" flush="true" />
		<div id="ContentsHeader"></div>
		<div id="Contents">
			<div id="TimeLine">
				<div id="TLHeader">TimeLine</div>
				<div id="TLContents">
					<div id="TLTable">
						<div id="TLIcon"><img src="<%= myProfile.getStIconURL() %>" width="56" height="56"></div>
						<div id="TLUserName"><%= myProfile.getIdUser() %></div>
						<div id="TLType"><%= myProfile.getStUserName() %></div>
						<div id="TLTitle"><%= myProfile.getNmAge() %></div>
						<div id="TLTitle"><%= myProfile.getNmAddYear() %></div>
						<div id="TLTitle"><%= myProfile.getStPart() %></div>
						<div id="TLTitle"><%= myProfile.getStFBand() %></div>
						<div id="TLTitle"><%= myProfile.getStFGenre() %></div>
						<div id="TLTitle"><%= myProfile.getStVideoURL() %></div>
						<div id="TLComment"><%= myProfile.getStComment() %></div>
					</div>
				</div>
				<form action="/quetana/Contents/EditProfile" method="post">
					<input type="text" name="idUser" maxlength="8" placeholder="ユーザID" required value="<%= myProfile.getIdUser() %>"><br>
					<input type="text" name="stUserName" maxlength="8" placeholder="ユーザ名" required value="<%= myProfile.getStUserName() %>"><br>
					<input type="text" name="nmAge" maxlength="3" placeholder="年齢" required value="<%= myProfile.getNmAge() %>"><br>
					<input type="text" name="nmAddYear" maxlength="4" placeholder="入学年度" required value="<%= myProfile.getNmAddYear() %>"><br>
					<input type="text" name="stPart" maxlength="128" placeholder="担当パート" required value="<%= myProfile.getStPart() %>"><br>
					<input type="text" name="stFBand" maxlength="512" placeholder="好きなバンド" required value="<%= myProfile.getStFBand() %>"><br>
					<input type="text" name="stFGenre" maxlength="512" placeholder="好きなジャンル" required value="<%= myProfile.getStFGenre() %>"><br>
					<input type="text" name="stVideoURL" maxlength="512" placeholder="Youtube" required value="<%= myProfile.getStVideoURL() %>"><br>
					<textarea name="stComment" rows="4" cols="40" placeholder="コメント"><%= myProfile.getStComment() %></textarea><br>
					<input id="edit_btn" type="submit" name="save" value="保存">
				</form>
			</div>
	</body>
</html>