<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.UserProfileBean" %>
<%
//リクエストスコープからプロフィールを取得
UserProfileBean userProfile = (UserProfileBean) request.getAttribute("userProfile");
String errMsg = (String)request.getAttribute("errMsg");
%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width">
		<title>Quetana</title>
		<link rel="stylesheet" type="text/css" href="/quetana/css/base.css">
		<link rel="stylesheet" type="text/css" href="/quetana/css/af_login.css">
		<link rel="stylesheet" type="text/css" href="/quetana/css/profile.css">
		<link rel="stylesheet" type="text/css" href="/quetana/css/profile_edit.css">
		<link href="https://fonts.googleapis.com/css?family=Lato:400,900" rel="stylesheet">
		<script src="/quetana/js/common.js"></script>
	</head>
	<body>
		<jsp:include page="../jsp/parts/mainheader.jsp" flush="true" />
		<div id="ContentsBase">
				<div id="TitleHeader">プロフィール編集</div>
				<div id="Contents">
					<form enctype="multipart/form-data" action="/quetana/Contents/EditProfile" method="post">
					<div id="ContentsTable">
						<div id="PFIcon">
							<label for="IconInput">
								<input id="IconInput" type="file" name="imgIcon" accept="image/*" class="unshown" onChange="setImage(event)">
								<div id="PFIconView" style="background-image: url(<%= userProfile.getStIconURL() %>)">
									<div id="PFIconCover">⊕</div>
								</div>
							</label>
						</div>
						<div id="PFShort">
							<div id="PFShortLabel">表示名：</div>
							<input id="PFShortVar" type="text" name="stDisplayName" maxlength="8" placeholder="表示名" value="<%= userProfile.getStDisplayName() %>">
						</div>
						<div id="PFShort">
							<div id="PFShortLabel">年齢　：</div>
							<input id="PFShortVar" type="text" name="nmAge" maxlength="3" placeholder="年齢" value="<%= userProfile.getNmAge() %>">
						</div>
						<div id="PFShort">
							<div id="PFShortLabel">入学年：</div>
							<input id="PFShortVar" type="text" name="nmAddYear" maxlength="4" placeholder="入学年度" value="<%= userProfile.getNmAddYear() %>">
						</div>
						<div id="PFMiddle">
							<div id="PFMiddleLabel">担当パート　　：</div>
							<input id="PFMiddleVar" type="text" name="stPart" maxlength="128" placeholder="担当パート" value="<%= userProfile.getStPart() %>">
						</div>
						<div id="PFMiddle">
							<div id="PFMiddleLabel">好きなジャンル：</div>
							<input id="PFMiddleVar" type="text" name="stFGenre" maxlength="512" placeholder="好きなジャンル" value="<%= userProfile.getStFGenre() %>">
						</div>
						<div id="PFLong">
							<div id="PFLongLabel">好きなバンド　：</div>
							<textarea id="PFBand" name="stFBand" placeholder="好きなバンド"><%= userProfile.getStFBand() %></textarea>
						</div>
						<div id="PFLong">
							<div id="PFLongLabel">自己紹介　　　：</div>
							<textarea id="PFComment" name="stComment" placeholder="コメント"><%= userProfile.getStComment() %></textarea>
						</div>
						<div id="PFLong">
							<div id="PFLongLabel">YoutubeURL　　：</div>
							<input id="PFLongVar" type="text" name="stVideoURL" maxlength="512" placeholder="YoutubeURL" value="<%= userProfile.getStVideoURL() %>">
						</div>
					</div>
					<input type="hidden" name="stIconURL" value="<%= userProfile.getStIconURL() %>">
					<input id="EditBtn" type="submit" name="edit" value="変更を保存">
					<a href="<%=request.getContextPath()%>/Contents/UserProfile">キャンセル</a>
					</form>
					<% if(errMsg != null) { %>
						<div id="MsgArea">
							<font id="ErrMsg"><%= errMsg %></font>
						</div>
					<% } %>
				</div>
		</div>
	</body>
</html>