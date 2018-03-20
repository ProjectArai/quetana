<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.TimeLineBean,java.util.Map, java.util.Date, java.text.SimpleDateFormat, model.LoginUserInfoBean" %>
<%
//セッションスコープからユーザ情報を取得
LoginUserInfoBean loginUserInfo = (LoginUserInfoBean) session.getAttribute("loginUserInfo");

// デフォルトで使用する値達
String stStatus = "new"; // 新規：new or 修正：fix
String chkN = "";
String chkM = "";
String chkE = "";
Date date = new Date();
SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
String stTitle = "";
String stPart = "";
String stPlace = "";
String stGenre = "";
String dtEvent = sdf.format(date); //デフォルトは当日日付を表示
String stDetails = "";
String cfPost = "N"; // 新規のデフォルトはプルダウン未選択状態
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
}
// 投稿種別プルダウンの初期選択状態設定
if (cfPost.equals("M")) {
	chkN = "";
	chkM = "selected";
	chkE = "";
} else if (cfPost.equals("E")) {
	chkN = "";
	chkM = "";
	chkE = "selected";
} else {
	chkN = "selected";
	chkM = "";
	chkE = "";
}
// エラーメッセージがあれば保持
String errMsg = (String)request.getAttribute("errMsg");
%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width, maximum-scale=1.0">
		<meta name="format-detection" content="telephone=no">
		<title>Quetana</title>
		<link rel="stylesheet" type="text/css" href="/quetana/css/common/com.css">
		<link rel="stylesheet" type="text/css" href="/quetana/css/common/com_basic.css">
		<link rel="stylesheet" type="text/css" href="/quetana/css/function/postsend.css">
		<link rel="stylesheet" type="text/css" href="/quetana/css/parts/select_part.css">
		<link href="https://fonts.googleapis.com/css?family=Lato:400,900" rel="stylesheet">
	</head>
	<body onLoad="dispPostType('<%= stStatus %>');dispPostForm();dispErrMsg('<%= errMsg %>');setTextareaHeight(arrHeight)">
		<form action="/quetana/Contents/Post" method="post">
		<div class="Foundation">
			<div class="PageTitle">
				<% if (stStatus.equals("new")) { %>
					投稿ページ
				<% } else if (stStatus.equals("fix")) { %>
					投稿内容修正
				<% } %>
			</div>
			<div class="ContentsHeadLine"></div>
			<div class="ContentsArea">
				<div class="ContentsShadow">
					<div class="ContentsFull">
<!--
						<input type="submit" name="edit" class="Button30 Right post" value="">
 -->
 						<img class="Icon30 Left" src="/quetana/img/default-icon.jpg">
						<div class="UserName30">テスト山　テスト一郎</div>
<!--
						<div class="TblBody">
							<div class="TblKey">タイトル</div>
							<textarea id="stTitle" class="TblValue" name="stTitle" placeholder="タイトル" onFocus="dynamicHeightChanger('stTitle')"><%= stTitle %></textarea>
						</div>
 -->
						<div class="TblBody">
							<textarea id="stTitle" class="TblHeadTitleL" name="stTitle" placeholder="タイトル" onFocus="dynamicHeightChanger('stTitle')"><%= stTitle %></textarea>
						</div>
						<div class="TblHead">
							<div class="TblHeadBlock"></div>
							<div class="TblHeadTitleS">概略</div>
						</div>
						<div id="PostType">
							<div class="TblBody">
								<div class="TblKey">投稿種別</div>
								<select id="cfPost" class="TblValue" name="cfPost" onChange="dispPostForm();">
									<option value="N" <%= chkN %>>---選択して下さい---</option>
									<option value="M" <%= chkM %>>バンドメンバー募集</option>
									<option value="E" <%= chkE %>>イベント告知</option>
								</select>
							</div>
						</div>
						<div id="PostMember">
							<div class="TblBody">
								<div class="TblKey">募集パート</div>
								<div class="PartArea">
									<label for="checkVo" class="noshadow">
										<input id="checkVo" type="checkbox" name="part" value="01" class="PartCheck unshown">
										<div class="part">Vo.</div>
									</label>
									<label for="checkGt" class="noshadow">
										<input id="checkGt" type="checkbox" name="part" value="02" class="PartCheck unshown">
										<div class="part">Gt.</div>
									</label>
									<label for="checkBa" class="noshadow">
										<input id="checkBa" type="checkbox" name="part" value="03" class="PartCheck unshown">
										<div class="part">Ba.</div>
									</label>
									<label for="checkKey" class="noshadow">
										<input id="checkKey" type="checkbox" name="part" value="04" class="PartCheck unshown">
										<div class="part">Key.</div>
									</label>
									<label for="checkDr" class="noshadow">
										<input id="checkDr" type="checkbox" name="part" value="05" class="PartCheck unshown">
										<div class="part">Dr.</div>
									</label>
									<label for="checkPer" class="noshadow">
										<input id="checkPer" type="checkbox" name="part" value="06" class="PartCheck unshown">
										<div class="part">Per.</div>
									</label>
									<label for="checkCho" class="noshadow">
										<input id="checkCho" type="checkbox" name="part" value="07" class="PartCheck unshown">
										<div class="part">Cho.</div>
									</label>
									<label for="checkOther" class="noshadow">
										<input id="checkOther" type="checkbox" name="part" value="08" class="PartCheck unshown">
										<div class="part">Other</div>
									</label>
								</div>
							</div>
							<div class="TblBody">
								<div class="TblKey">ジャンル</div>
								<textarea id="stGenre" class="TblValue" name="stGenre" placeholder="ジャンル" onFocus="dynamicHeightChanger('stGenre')"><%= stGenre %></textarea>
							</div>
						</div>
						<div id="PostEvent">
							<div class="TblBody">
								<div class="TblKey">開催日</div>
								<input class="TblValue" type="date" name="dtEvent" placeholder="開催日" value="<%= dtEvent %>">
							</div>
							<div class="TblBody">
								<div class="TblKey">開催場所</div>
								<textarea id="stPlace" class="TblValue" name="stPlace" placeholder="開催場所" onFocus="dynamicHeightChanger('stPlace')"><%= stPlace %></textarea>
							</div>
						</div>
						<div class="TblHead">
							<div class="TblHeadBlock"></div>
							<div class="TblHeadTitleS">詳細</div>
						</div>
						<div class="TblBody">
							<textarea id="stDetails" class="FullWide" name="stDetails" placeholder="詳細" onFocus="dynamicHeightChanger('stDetails')"><%= stDetails %></textarea>
						</div>
						<!-- ★この下のやつは一時的なやつなのでロジック直したら消す -->
						<input type="hidden" name="stPart" value="<%= stPart %>">
					</div>
<!--
					<div class="ContentsBottom">
						<% if (stStatus.equals("new")) { %>
							<input type="submit" name="newPostBtn" class="Right write" value="投稿する">
						<% } else if (stStatus.equals("fix")) { %>
							<input type="submit" name="fixPostBtn" class="Right write" value="修正する">
						<% } %>
						<input class="PostSend" type="submit" name="PostSend" value="投稿する">
					</div>
 -->
				</div>
			</div>
		</div>
		<jsp:include page="../jsp/parts/editheader.jsp" flush="true" />
		</form>
		<script src="/quetana/js/common.js"></script>
		<script src="/quetana/js/postsend.js"></script>
	</body>
</html>