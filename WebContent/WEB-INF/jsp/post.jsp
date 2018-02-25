<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.TimeLineBean,java.util.Map" %>
<%
// デフォルトで使用する値達
String stTitle = "";
String stPart = "";
String stPlace = "";
String stGenre = "";
String dtEvent = "";
String stDetails = "";
String cfPost = "M"; //デフォルトはとりあえずM
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
		<link rel="stylesheet" type="text/css" href="/quetana/css/main.css">
		<link rel="stylesheet" type="text/css" href="/quetana/css/menu.css">
		<script src="/quetana/js/common.js"></script>
	</head>
	<body onLoad="dispPostForm('<%=  cfPost %>')">
		<jsp:include page="../jsp/parts/mainheader.jsp" flush="true" />
		<div id="ContentsHeader"></div>
		<div id="Contents">
			<div id="TimeLine">
			<form action="/quetana/Contents/Post" method="post">
				<div id="TLHeader">投稿ページ</div>
				<div id="TLMenu">
					<label for="RadioAll">
						<input id="RadioAll" name="cfPost" type="radio" class="nav-unshown" value="M" onclick="dispPostForm('M')" <%= chkM %>>
						<div id="TLMenuTab">メンバー募集</div>
					</label>
					<label for="RadioEvent">
						<input id="RadioEvent" name="cfPost" type="radio" class="nav-unshown" value="E" onclick="dispPostForm('E')" <%= chkE %>>
						<div id="TLMenuTab">イベント告知</div>
					</label>
				</div>
				<div id="TLContents">
					<div id="TLTable">
						<div id="TLTitle"><input type="text" name="stTitle" maxlength="32" placeholder="タイトル" required value="<%=  stTitle %>"></div>
						<div id="TLTitle"><input id="stPart" type="text" name="stPart" maxlength="32" placeholder="募集パート" value="<%=  stPart %>">
											<input id="stPlace" type="text" name="stPlace" maxlength="32" placeholder="場所" value="<%=  stPlace %>"></div>
						<div id="TLTitle"><input id="stGenre" type="text" name="stGenre" maxlength="32" placeholder="演奏ジャンル" value="<%=  stGenre %>">
											<input id="dtEvent" type="date" name="dtEvent" maxlength="32" placeholder="開催日" value="<%=  dtEvent %>"></div>
						<div id="TLTitle"><textarea name="stDetails" rows="4" cols="32" placeholder="詳細"><%=  stDetails %></textarea></div>
					</div>
				</div>
				<input id="post_btn" type="submit" name="post" value="投稿">
				<a href="<%=request.getContextPath()%>/Contents/Home">キャンセル</a>
			</form>
				<% if(errMsg != null) { %>
						<font id="ErrMsg"><%= errMsg %></font>
				<% } %>
			</div>
		</div>
	</body>
</html>