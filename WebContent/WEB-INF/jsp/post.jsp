<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.UserInfoBean,model.TimeLineBean,java.util.List" %>
<!DOCTYPE html>
<html>
<script>
function displayMember() {
    document.getElementById("stPart").style.display="block";
    document.getElementById("stGenre").style.display="block";
    document.getElementById("stPlace").style.display="none";
    document.getElementById("dtEvent").style.display="none";
}

function displayEvent() {
    document.getElementById("stPart").style.display="none";
    document.getElementById("stGenre").style.display="none";
    document.getElementById("stPlace").style.display="block";
    document.getElementById("dtEvent").style.display="block";
}
</script>
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
				<div id="TLMenu">
					<div id="main">こんにちは</div>
				</div>
				<div id="TLContents">
					<div id="TLTable">
						<form action="/quetana/Contents/Post" method="post">
							<p>
								<input type="radio" name="cfPost" value="M" onclick="displayMember()" checked>
								<input type="radio" name="cfPost" value="E" onclick="displayEvent()">
							</p>
							<input type="text" name="stTitle" maxlength="32" placeholder="タイトル" required><br>
							<input id="stPart" type="text" name="stPart" maxlength="32" placeholder="募集パート"><br>
							<input id="stGenre" type="text" name="stGenre" maxlength="32" placeholder="演奏ジャンル"><br>
							<input id="stPlace" type="text" name="stPlace" maxlength="32" placeholder="場所"><br>
							<input id="dtEvent" type="text" name="dtEvent" maxlength="32" placeholder="開催日"><br>
							<textarea name="stDetails" rows="4" cols="40" placeholder="詳細"></textarea><br>
							<input id="post_btn" type="submit" name="post" value="投稿">
						</form>
					</div>
				</div>
			</div>
	</body>
</html>