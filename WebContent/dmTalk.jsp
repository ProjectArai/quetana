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
		<link rel="stylesheet" type="text/css" href="/quetana/css/function/dmList.css">
		<link rel="stylesheet" type="text/css" href="/quetana/css/function/comment.css">
		<link href="https://fonts.googleapis.com/css?family=Lato:400,900" rel="stylesheet">
	</head>
	<body>
		<div class="Foundation">
			<div class="PageTitle">kazzoolとのメッセージ</div>
			<div class="ContentsHeadLine"></div>
			<div class="ContentsArea">
				<div class="ContentsShadow">
					<div class="ContentsTop">
						<div class="DmHeader">
							kazzool ⇔ test0002
						</div>
<!--
						<div class="TblHeadTitleL">
							kazzool ⇔ test0002
						</div>
 -->
					</div>
					<div class="ContentsMiddle">
						<div class="BalloonBase">
							<img class="Icon25 Left" src="/quetana/img/kazzool.png">
							<div class="TirLeft"></div>
							<div class="Balloon Left">
								はじめまして！
							</div>
						</div>
						<div class="BalloonBase">
							<img class="Icon25 Left" src="/quetana/img/kazzool.png">
							<div class="TirLeft"></div>
							<div class="Balloon Left">
								2014年卒のkazzoolです！
							</div>
						</div>
						<div class="BalloonBase">
							<img class="Icon25 Left" src="/quetana/img/kazzool.png">
							<div class="TirLeft"></div>
							<div class="Balloon Left">
								Beatlesのコピバンの件ですが、メンバー決まってなければベースで加入できますか？
							</div>
						</div>
						<div class="BalloonBase">
							<img class="Icon25 Right" src="/quetana/img/default-icon.jpg">
							<div class="TirRight"></div>
							<div class="Balloon Right">
								はじめまして！！
							</div>
						</div>
						<div class="BalloonBase">
							<img class="Icon25 Right" src="/quetana/img/default-icon.jpg">
							<div class="TirRight"></div>
							<div class="Balloon Right">
								まだ決まってないのでお願いします！
							</div>
						</div>
					</div>
					<div class="ContentsBottom">
						<div class="CommentBase">
							<img class="Icon30 Left" src="/quetana/img/default-icon.jpg">
							<form name="formSendMsg" action="/quetana/Contents/Home" method="Get">
								<textarea id="stMessage" class="CommentForm" name="stMessage" maxlength="128" placeholder="メッセージを送信する" onfocus="dynamicHeightChanger('stMessage')"></textarea>
								<input type="submit" name="sendMsgBtn" class="Icon30 Right comment" value="">
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
		<jsp:include page="./WEB-INF/jsp/parts/mainheader.jsp" flush="true" />
		<script src="/quetana/js/common.js"></script>
		<script src="/quetana/js/dm.js"></script>
	</body>
</html>