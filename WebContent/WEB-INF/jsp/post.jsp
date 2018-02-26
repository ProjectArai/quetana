<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.TimeLineBean,java.util.Map, java.util.Date, java.text.SimpleDateFormat" %>
<%
// デフォルトで使用する値達
Date date = new Date();
SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
String stTitle = "";
String stPart = "";
String stPlace = "";
String stGenre = "";
String dtEvent = sdf.format(date); //デフォルトは当日日付を表示
String stDetails = "";
String cfPost = "M"; //デフォルトはメンバー募集フォームを表示
String chkM = "checked";
String chkE = "";
// エラーで画面再描画する際に使う値達
Map inParam = (Map)request.getAttribute("inParam");
if (inParam != null) {
	stTitle = (String)inParam.get("stTitle");
	stPart = (String)inParam.get("stPart");
	stPlace = (String)inParam.get("stPlace");
	stGenre = (String)inParam.get("stGenre");
	dtEvent = (String)inParam.get("dtEvent");
	stDetails = (String)inParam.get("stDetails");
	cfPost = (String)inParam.get("cfPost");
	if (cfPost.equals("E")) {
		chkM = "";
		chkE = "checked";
	}
}
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
		<link rel="stylesheet" type="text/css" href="/quetana/css/menu.css">
		<link rel="stylesheet" type="text/css" href="/quetana/css/post.css">
		<link href="https://fonts.googleapis.com/css?family=Lato:400,900" rel="stylesheet">
		<script src="/quetana/js/common.js"></script>
	</head>
	<body onLoad="dispPostForm('<%=  cfPost %>')">
		<jsp:include page="../jsp/parts/mainheader.jsp" flush="true" />
		<div id="ContentsBase">
				<div id="TitleHeader">投稿ページ</div>
				<form action="/quetana/Contents/Post" method="post">
				<div id="SubMenuBase">
					<label for="RadioMember">
						<input id="RadioMember" name="cfPost" type="radio" class="unshown" value="M" onclick="dispPostForm('M')" <%= chkM %>>
						<div id="SubMenuTab">メンバー募集</div>
					</label>
					<label for="RadioEvent">
						<input id="RadioEvent" name="cfPost" type="radio" class="unshown" value="E" onclick="dispPostForm('E')" <%= chkE %>>
						<div id="SubMenuTab">イベント告知</div>
					</label>
				</div>
				<div id="Contents">
					<div id="ContentsTable">
						<div id="PostTitle"><input id="PostElement" type="text" name="stTitle" maxlength="32" placeholder="タイトル" value="<%=  stTitle %>"></div>
						<div id="PostMember">
							<input id="PostElement" type="text" name="stPart" maxlength="32" placeholder="募集パート" value="<%=  stPart %>">
							<input id="PostElement" type="text" name="stGenre" maxlength="32" placeholder="演奏ジャンル" value="<%=  stGenre %>">
						</div>
						<div id="PostEvent">
							<input id="PostElement" type="text" name="stPlace" maxlength="32" placeholder="場所" value="<%=  stPlace %>">
							<input id="PostElement" type="date" name="dtEvent" maxlength="32" placeholder="開催日" value="<%=  dtEvent %>">
						</div>
						<div id="PostDetails"><textarea id="PostDetails" name="stDetails" placeholder="詳細"><%=  stDetails %></textarea></div>
					</div>
					<input id="PostBtn" type="submit" name="post" value="投稿する">
					<a href="<%=request.getContextPath()%>/Contents/Home">キャンセル</a>
					<% if(errMsg != null) { %>
						<div id="MsgArea">
							<font id="ErrMsg"><%= errMsg %></font>
						</div>
					<% } %>
				</div>
				</form>
		</div>
	</body>
</html>