<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.UserProfileBean" %>
<%
String stBackURL = request.getContextPath() + "/Contents/Home";
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
		<link rel="stylesheet" type="text/css" href="/quetana/css/function/postview.css">
		<link href="https://fonts.googleapis.com/css?family=Lato:400,900" rel="stylesheet">
	</head>
	<body>
		<div class="Foundation">
			<div class="PageTitle">バンドメンバー募集</div>
			<div class="ContentsHeadLine"></div>
			<div class="ContentsArea">
				<div class="ContentsShadow">
					<div class="ContentsTop">
						<form name="comment" action="/quetana/postedit_t1.jsp" method="Get">
							<input type="submit" name="commentBtn" class="Button30 Right edit" value="">
						</form>
						<a class="noshadow" id="TLLink" href="/quetana/Contents/UserProfile?idUser=UI000001">
							<img class="Icon30 Left" src="/quetana/img/UI000001.jpg">
							<div class="UserName30">まえぞのりょうた</div>
						</a>
						<div class="TblHead">
							<div class="TblHeadTitleL">
								Beatles（コピー）のメンバーを探しています！
							</div>
						</div>
						<div class="TblBody">
							<div class="TblKey">募集パート</div><div class="TblValue">Ba, Dr</div>
						</div>
						<div class="TblBody">
							<div class="TblKey">ジャンル</div><div class="TblValue">Rock</div>
						</div>
						<div class="TblBody">
							<div class="TblValue FullWide">
								ライブの日取りはまだ決まっていないのですが、theBeatlesのコピーバンドを組んでスタジオで合わせたいと思っています！！興味のある方は是非コメントやDMを送って下さい！よろしくお願いします！！！
								<br>
								【コピー中の曲】<br>
								・Hey Jude<br>
								・I Want To Hold Your Hand<br>
								・She Loves You
							</div>
						</div>
						<div class="Update20">2018/12/31 23:55</div>
					</div>
					<div class="ContentsMiddle">
						<div class="BalloonBase">
							<img class="Icon25 Left" src="/quetana/img/gekiyasu.png">
							<div class="TirLeft"></div>
							<div class="Balloon Left">
								<a id="TLLink" href="/quetana/Contents/UserProfile?idUser=UI000009">あべけん</a>
								ドラム加入希望です！
							</div>
						</div>
						<div class="BalloonBase">
							<img class="Icon25 Left" src="/quetana/img/kazzool.png">
							<div class="TirLeft"></div>
							<div class="Balloon Left">
								<a id="TLLink" href="/quetana/Contents/UserProfile?idUser=UI000001">kazzool</a>
								はじめまして、2014年卒のkazzoolです！ベース加入希望ですがもう決まってしまいましたか？
							</div>
						</div>
						<div class="BalloonBase">
							<img class="Icon25 Right" src="/quetana/img/UI000001.jpg">
							<div class="TirRight"></div>
							<div class="Balloon Right">
								<a id="TLLink" href="/quetana/Contents/UserProfile?idUser=UI000001">まえぞのりょうた</a>
								あべけん> ありがとうございます！！
							</div>
						</div>
						<div class="BalloonBase">
							<img class="Icon25 Right" src="/quetana/img/UI000001.jpg">
							<div class="TirRight"></div>
							<div class="Balloon Right">
								<a id="TLLink" href="/quetana/Contents/UserProfile?idUser=UI000001">まえぞのりょうた</a>
								kazzool> ありがとうございます！！まだ決まってないのでお願いします！
							</div>
						</div>
					</div>
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