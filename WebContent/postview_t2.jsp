<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
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
		<link rel="stylesheet" type="text/css" href="/quetana/css/function/comment.css">
		<link href="https://fonts.googleapis.com/css?family=Lato:400,900" rel="stylesheet">
	</head>
	<body>
		<div class="Foundation">
			<div class="PageTitle">イベント告知</div>
			<div class="ContentsHeadLine"></div>
			<div class="ContentsArea">
				<div class="ContentsShadow">
					<div class="ContentsTop">
<!--
						<form name="comment" action="/quetana/postedit_t1.jsp" method="Get">
							<input type="submit" name="commentBtn" class="Button30 Right edit" value="">
						</form>
 -->
						<a class="noshadow" id="TLLink" href="/quetana/Contents/UserProfile?idUser=UI000001">
							<img class="Icon30 Left" src="/quetana/img/UI000001.jpg">
							<div class="UserName30">まえぞのりょうた</div>
						</a>
						<div class="TblBody">
							<div class="TblHeadTitleL">
								OB/OGライブ 2018
							</div>
						</div>
						<div class="TblHead">
							<div class="TblHeadBlock"></div>
							<div class="TblHeadTitleS">概略</div>
						</div>
						<div class="TblBody">
							<div class="TblKey">開催日</div><div class="TblValue">2018/02/09</div>
						</div>
						<div class="TblBody">
							<div class="TblKey">開催場所</div><div class="TblValue">池袋ロサ</div>
						</div>
						<div class="TblHead">
							<div class="TblHeadBlock"></div>
							<div class="TblHeadTitleS">詳細</div>
						</div>
						<div class="TblBody">
							<div class="TblValue FullWide">
								皆様、いかがお過ごしでしょうか。今年もOB/OGライブの季節がやってまいりました。OB/OGの方も、現役のみんなも、ごちゃまぜのライブを楽しみましょう！<br>
								まずは参加バンドを募集するので、興味のある方はコメントやDMお願いします！！！<br>
								<br>
								■スケジュール<br>
								2018/02/09<br>
								open ：17:00<br>
								start：17:30<br>
								<br>
								■会場<br>
								池袋ロサ<br>
								住所：〒171-0021 東京都豊島区西池袋１丁目３７−１２ ロサ会館<br>
								電話：03-5956-3463
							</div>
						</div>
						<div class="Update20">2018/12/31 23:55</div>
					</div>
					<!-- コメントなし想定だからContentsMiddleなし -->
					<div class="ContentsBottom">
						<div class="CommentBase">
							<img class="Icon30 Left" src="/quetana/img/UI000001.jpg">
							<form name="comment" action="/quetana/Contents/Home" method="Get">
								<textarea id="stComment" class="CommentForm" name="stComment" maxlength="128" placeholder="コメントする" onfocus="dynamicHeightChanger('stComment')"></textarea>
								<input type="submit" name="commentBtn" class="Icon30 Right comment" value="">
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
		<jsp:include page="./WEB-INF/jsp/parts/mainheader.jsp" flush="true" />
		<script src="/quetana/js/common.js"></script>
		<script src="/quetana/js/postview.js"></script>
	</body>
</html>