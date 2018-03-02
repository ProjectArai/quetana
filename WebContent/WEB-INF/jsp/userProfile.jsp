<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.UserProfileBean" %>
<%
//リクエストスコープからプロフィールを取得
UserProfileBean userProfile = (UserProfileBean) request.getAttribute("userProfile");
String stAccountName = userProfile.getStAccountName();
String stIconURL = userProfile.getStIconURL();
String stDisplayName = userProfile.getStDisplayName();
String stAge;
if ((userProfile.getNmAge()).equals("")){
	stAge = "未登録";
} else {
	stAge = userProfile.getNmAge() + "才";
}
//リクエストスコープからプロフィール編集権限を取得
String perEdit = (String) request.getAttribute("perEdit");
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
		<link href="https://fonts.googleapis.com/css?family=Lato:400,900" rel="stylesheet">
		<script src="/quetana/js/common.js"></script>
	</head>
	<body>
		<jsp:include page="../jsp/parts/mainheader.jsp" flush="true" />
		<div id="ContentsBase">
				<div id="TitleHeader"><%= userProfile.getStAccountName() %>&nbsp;のプロフィール</div>
				<div id="Contents">
					<div id="ContentsTable">
						<div id="PFIcon"><img src="<%= userProfile.getStIconURL() %>" width="84" height="84"></div>
						<div id="PFShort">
							<div id="PFShortLabel">表示名：</div>
							<div id="PFShortVar"><%= userProfile.getStDisplayName() %></div>
						</div>
						<div id="PFShort">
							<div id="PFShortLabel">年齢　：</div>
							<div id="PFShortVar">
								<% if ((userProfile.getNmAge()).equals("")) { %>
									<font id="Unregist">未登録</font>
								<% } else { %>
									<%= userProfile.getNmAge() %>&nbsp;才
								<% } %>
							</div>
						</div>
						<div id="PFShort">
							<div id="PFShortLabel">入学年：</div>
							<div id="PFShortVar">
								<% if ((userProfile.getNmAddYear()).equals("")) { %>
									<font id="Unregist">未登録</font>
								<% } else { %>
									<%= userProfile.getNmAddYear() %>&nbsp;年
								<% } %>
							</div>
						</div>
						<div id="PFMiddle">
							<div id="PFMiddleLabel">担当パート　　：</div>
							<div id="PFMiddleVar">
								<% if ((userProfile.getStPart()).equals("")) { %>
									<font id="Unregist">未登録</font>
								<% } else { %>
									<%= userProfile.getStPart() %>
								<% } %>
							</div>
						</div>
						<div id="PFMiddle">
							<div id="PFMiddleLabel">好きなジャンル：</div>
							<div id="PFMiddleVar">
								<% if ((userProfile.getStFGenre()).equals("")) { %>
									<font id="Unregist">未登録</font>
								<% } else { %>
									<%= userProfile.getStFGenre() %>
								<% } %>
							</div>
						</div>
						<div id="PFLong">
							<div id="PFLongLabel">好きなバンド　：</div>
							<div id="PFLongVar">
								<% if ((userProfile.getStFBand()).equals("")) { %>
									<font id="Unregist">未登録</font>
								<% } else { %>
									<%= userProfile.getStFBand() %>
								<% } %>
							</div>
						</div>
						<div id="PFLong">
							<div id="PFLongLabel">自己紹介　　　：</div>
							<div id="PFLongVar">
								<% if ((userProfile.getStComment()).equals("")) { %>
									<font id="Unregist">未登録</font>
								<% } else { %>
									<%= userProfile.getStComment() %>
								<% } %>
							</div>
						</div>
						<% if (!(userProfile.getStVideoURL()).equals("")) { %>
							<div id="PFVideoURL"><a href="<%= userProfile.getStVideoURL() %>">▶演奏動画を見る</a></div>
						<% } %>
					</div>
					<% if (perEdit.equals("Y")) { %>
						<input id="PFEditBtn" type="button" value="プロフィールを編集" onClick="location.href='/quetana/Contents/EditProfile'">
					<% } %>
					<a href="<%=request.getContextPath()%>/Contents/Home">キャンセル</a>
				</div>
		</div>
	</body>
</html>