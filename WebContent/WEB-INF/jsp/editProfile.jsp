<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.UserProfileBean" %>
<%
//リクエストスコープからプロフィールを取得
UserProfileBean myProfile = (UserProfileBean) request.getAttribute("myProfile");
String errMsg = (String)request.getAttribute("errMsg");
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
				<form action="/quetana/Contents/EditProfile" method="post">
				<div id="TLContents">
					<div id="TLTable">
						<div id="TLIcon"><img src="<%= myProfile.getStIconURL() %>" width="56" height="56"></div>
						<div id="TLUserName"><%= myProfile.getStAccountName() %></div>
						<div id="TLType"><input type="text" name="stDisplayName" maxlength="8" placeholder="表示名" required value="<%= myProfile.getStDisplayName() %>"></div>
						<div id="TLTitle"><input type="text" name="nmAge" maxlength="3" placeholder="年齢" required value="<%= myProfile.getNmAge() %>"></div>
						<div id="TLTitle"><input type="text" name="nmAddYear" maxlength="4" placeholder="入学年度" required value="<%= myProfile.getNmAddYear() %>"></div>
						<div id="TLTitle"><input type="text" name="stPart" maxlength="128" placeholder="担当パート" required value="<%= myProfile.getStPart() %>"></div>
						<div id="TLTitle"><input type="text" name="stFBand" maxlength="512" placeholder="好きなバンド" required value="<%= myProfile.getStFBand() %>"></div>
						<div id="TLTitle"><input type="text" name="stFGenre" maxlength="512" placeholder="好きなジャンル" required value="<%= myProfile.getStFGenre() %>"></div>
						<div id="TLTitle"><input type="text" name="stVideoURL" maxlength="512" placeholder="YoutubeURL" required value="<%= myProfile.getStVideoURL() %>"></div>
						<div id="TLComment"><textarea name="stComment" rows="4" cols="32" placeholder="コメント"><%= myProfile.getStComment() %></textarea></div>
					</div>
				</div>
				<input id="edit_btn" type="submit" name="save" value="保存">
				<a href="<%=request.getContextPath()%>/Contents/UserProfile">キャンセル</a>
				</form>
				<% if(errMsg != null) { %>
						<font id="ErrMsg"><%= errMsg %></font>
				<% } %>
			</div>
		</div>
	</body>
</html>