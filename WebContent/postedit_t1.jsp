<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.UserProfileBean, java.util.Date, java.text.SimpleDateFormat" %>
<%
//モック用
String stTitle = "";
String stDetails = "";
String cfPost = "E";
if (cfPost.equals("M")) {
	stTitle = "Beatles（コピー）のメンバーを探しています！";
	stDetails = "ライブの日取りはまだ決まっていないのですが、theBeatlesのコピーバンドを組んでスタジオで合わせたいと思っています！！興味のある方は是非コメントやDMを送って下さい！よろしくお願いします！！！\r\n【コピー中の曲】\r\n・Hey Jude\r\n・I Want To Hold Your Hand\r\n・She Loves You";
} else {
	stTitle = "OB/OGライブ 2018 EndllesEight＠Merancoly of Haruhi Suzumiya";
	stDetails = "皆様、いかがお過ごしでしょうか。今年もOB/OGライブの季節がやってまいりました。OB/OGの方も、現役のみんなも、ごちゃまぜのライブを楽しみましょう！\r\nまずは参加バンドを募集するので、興味のある方はコメントやDMお願いします！！！\r\n\r\n■スケジュール\r\n2018/02/09\r\nopen ：17:00\r\nstart：17:30\r\n\r\n■会場\r\n池袋ロサ\r\n住所：〒171-0021 東京都豊島区西池袋１丁目３７−１２ ロサ会館\r\n電話：03-5956-3463";
}
String stGenre = "Rock";
String dtEvent = "2018-02-09";
String stPlace = "池袋ロサ";
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
	<body onLoad="dispPostForm()">
		<div class="Foundation">
			<div class="PageTitle">投稿内容修正</div>
			<div class="ContentsHeadLine"></div>
			<div class="ContentsArea">
				<div class="ContentsShadow">
					<div class="ContentsFull">
						<img class="Icon30 Left" src="/quetana/img/UI000001.jpg">
						<div class="UserName30">まえぞのりょうた</div>
						<div class="TblBody">
							<textarea id="stTitle" class="TblHeadTitleL" name="stTitle" placeholder="タイトル" onFocus="dynamicHeightChanger('stTitle')"><%= stTitle %></textarea>
						</div>
						<div class="TblHead">
							<div class="TblHeadBlock"></div>
							<div class="TblHeadTitleS">概略</div>
						</div>
						<param id="cfPost" value="<%= cfPost %>">
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
										<input id="checkBa" type="checkbox" name="part" value="03" class="PartCheck unshown" checked>
										<div class="part">Ba.</div>
									</label>
									<label for="checkKey" class="noshadow">
										<input id="checkKey" type="checkbox" name="part" value="04" class="PartCheck unshown">
										<div class="part">Key.</div>
									</label>
									<label for="checkDr" class="noshadow">
										<input id="checkDr" type="checkbox" name="part" value="05" class="PartCheck unshown" checked>
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
					</div>
				</div>
			</div>
		</div>
		<jsp:include page="./WEB-INF/jsp/parts/editheader.jsp" flush="true" />
		<script src="/quetana/js/common.js"></script>
		<script src="/quetana/js/postEdit.js"></script>
		<script src="/quetana/js/postsend.js"></script>
	</body>
</html>