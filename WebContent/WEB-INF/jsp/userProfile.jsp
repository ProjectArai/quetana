<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.UserProfileBean" %>
<%
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
						<div id="TLUserName"><%= myProfile.getStAccountName() %></div>
						<div id="TLType"><%= myProfile.getStDisplayName() %></div>
						<div id="TLTitle"><%= myProfile.getNmAge() %></div>
						<div id="TLTitle"><%= myProfile.getNmAddYear() %></div>
						<div id="TLTitle"><%= myProfile.getStPart() %></div>
						<div id="TLTitle"><%= myProfile.getStFBand() %></div>
						<div id="TLTitle"><%= myProfile.getStFGenre() %></div>
						<div id="TLTitle"><%= myProfile.getStVideoURL() %></div>
						<div id="TLComment"><%= myProfile.getStComment() %></div>
					</div>
				</div>
				<form action="/quetana/Contents/EditProfile" method="get">
					<input id="edit_btn" type="submit" name="edit"value="マイページを編集する">
				</form>
			</div>
	</body>
</html>