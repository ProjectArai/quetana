<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.UserProfileBean" %>
<%
//リクエストスコープからプロフィールを取得
UserProfileBean myProfile = (UserProfileBean) request.getAttribute("myProfile");
String infoMsg = (String)request.getAttribute("infoMsg");
%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width">
		<title>Quetana</title>
		<link rel="stylesheet" type="text/css" href="/quetana/css/base.css">
		<link rel="stylesheet" type="text/css" href="/quetana/css/af_login.css">
		<link rel="stylesheet" type="text/css" href="/quetana/css/menu.css">
		<link rel="stylesheet" type="text/css" href="/quetana/css/profile.css">
		<link href="https://fonts.googleapis.com/css?family=Lato:400,900" rel="stylesheet">
		<script src="/quetana/js/common.js"></script>
	</head>
	<body>
		<jsp:include page="../jsp/parts/mainheader.jsp" flush="true" />
		<div id="ContentsBase">
				<div id="TitleHeader"><%= myProfile.getStAccountName() %>&nbsp;のプロフィール</div>
				<div id="Contents">
					<div id="PFTable">
						<div id="PFIcon"><img src="<%= myProfile.getStIconURL() %>" width="90" height="90"></div>
						<div id="PFShort">
							<div id="PFShortLabel">表示名：</div>
							<div id="PFShortVar"><%= myProfile.getStDisplayName() %></div>
						</div>
						<div id="PFShort">
							<div id="PFShortLabel">年齢　：</div>
							<div id="PFShortVar"><%= myProfile.getNmAge() %>&nbsp;才</div>
						</div>
						<div id="PFShort">
							<div id="PFShortLabel">入学年：</div>
							<div id="PFShortVar"><%= myProfile.getNmAddYear() %>&nbsp;年</div>
						</div>
						<div id="PFMiddle">
							<div id="PFMiddleLabel">担当パート　　：</div>
							<div id="PFMiddleVar"><%= myProfile.getStPart() %></div>
						</div>
						<div id="PFMiddle">
							<div id="PFMiddleLabel">好きなジャンル：</div>
							<div id="PFMiddleVar"><%= myProfile.getStFGenre() %></div>
						</div>
						<div id="PFLong">
							<div id="PFLongLabel">好きなバンド　：</div>
							<div id="PFLongVar"><%= myProfile.getStFBand() %></div>
						</div>
						<div id="PFLong">
							<div id="PFCommentVar"><%= myProfile.getStComment() %></div>
						</div>
						<% if (!(myProfile.getStVideoURL()).equals("")) { %>
								<div id="PFVideoURL"><a href="<%= myProfile.getStVideoURL() %>">▶演奏動画を視聴</a></div>
						<% } %>
					</div>
					<div id="PFEditBtn" class="EditLink">プロフィールを編集<a href="/quetana/Contents/EditProfile"></a></div>
				</div>
		</div>
	</body>
</html>