<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.UserProfileBean" %>
<%
//リクエストスコープからプロフィールを取得
UserProfileBean userProfile = (UserProfileBean) request.getAttribute("userProfile");
//リクエストスコープからプロフィール編集権限を取得
String perEdit = (String) request.getAttribute("perEdit");
%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width">
		<meta name="format-detection" content="telephone=no">
		<title>Quetana</title>
		<link rel="stylesheet" type="text/css" href="/quetana/css/common/com.css">
		<link rel="stylesheet" type="text/css" href="/quetana/css/common/com_basic.css">
		<link rel="stylesheet" type="text/css" href="/quetana/css/function/profile.css">
		<link rel="stylesheet" type="text/css" href="/quetana/css/switchdisp/pf_basic.css" id="DispStyle">
		<link href="https://fonts.googleapis.com/css?family=Lato:400,900" rel="stylesheet">
	</head>
	<body onLoad="changeDispContents()">
		<div class="Foundation">
			<div class="PageTitle"><%= userProfile.getStAccountName() %>&nbsp;のプロフィール</div>
			<div class="ContentsHead">
				<label for="RadioBasic" class="noshadow">
					<input id="RadioBasic" type="radio" name="TabRadio" class="TabCheck unshown" onclick="changeDispContents()" checked>
					<div class="Tab">基本情報</div>
				</label>
				<label for="RadioFriend" class="noshadow">
					<input id="RadioFriend" type="radio" name="TabRadio" class="TabCheck unshown" onclick="changeDispContents()">
					<div class="Tab">友達リスト</div>
				</label>
			</div>
			<div class="ContentsArea">
				<div class="PfBasic">
					<div class="ContentsShadow">
						<div class="ContentsFull">
							<% if (perEdit.equals("Y")) { %>
								<!-- ログインユーザ自身の場合、プロフィール編集ボタンを表示 -->
								<form name="formEdit" action="/quetana/Contents/UserProfile" method="post">
									<input type="submit" class="Button30 Right edit" value="" onclick="getHeightTextare(arrTextareaId)">
								</form>
							<% } else if (perEdit.equals("N")) { %>
								<!-- ログインユーザ以外の場合、友達申請ボタン？とDM送信ボタンを表示 -->
								<form name="formReqFriend" action="/quetana/Contents/UserProfile" method="post" onsubmit="return dispConfirmDialog('MSG00010')">
									<input type="submit" name="btnRecFriend" class="Button30 Right friend" value="">
								</form>
								<form name="formSendDM" action="/quetana/Contents/UserProfile" method="post" onsubmit="return dispConfirmDialog('MSG00012')">
									<input type="submit" name="btnSendDM" class="Button30 Right dm" value="">
								</form>
							<% } %>
							<img class="IconView" src="<%= userProfile.getStIconURL() %>">
							<div class="TblHead">
								<div class="TblHeadBlock"></div>
								<div class="TblHeadTitleS">基本データ</div>
							</div>
							<div class="TblBody">
								<div class="TblKey">表示名</div>
								<div class="TblValue">
									<%= userProfile.getStDisplayName() %>
								</div>
							</div>
							<div class="TblBody">
								<div class="TblKey">年齢</div>
								<div class="TblValue">
									<% if ((userProfile.getNmAge()).equals("")) { %>
										<font class="Unset">未登録</font>
									<% } else { %>
										<%= userProfile.getNmAge() %>&nbsp;才
									<% } %>
								</div>
							</div>
							<div class="TblBody">
								<div class="TblKey">入学年</div>
								<div class="TblValue">
									<% if ((userProfile.getNmAddYear()).equals("")) { %>
										<font class="Unset">未登録</font>
									<% } else { %>
										<%= userProfile.getNmAddYear() %>&nbsp;年
									<% } %>
								</div>
							</div>
							<div class="TblBody">
								<div class="TblKey">担当パート</div>
								<div class="TblValue">
									<% if ((userProfile.getStPart()).equals("")) { %>
										<font class="Unset">未登録</font>
									<% } else { %>
										<%= userProfile.getStPart() %>
									<% } %>
								</div>
							</div>
							<div class="TblHead">
								<div class="TblHeadBlock"></div>
								<div class="TblHeadTitleS">お気に入り</div>
							</div>
							<div class="TblBody">
								<div class="TblKey">ジャンル</div>
								<div class="TblValue" id="stGenre">
									<% if ((userProfile.getStFGenre()).equals("")) { %>
										<font class="Unset">未登録</font>
									<% } else { %>
										<%= userProfile.getStFGenre() %>
									<% } %>
								</div>
							</div>
							<div class="TblBody">
								<div class="TblKey">バンド</div>
								<div class="TblValue" id="stBand">
									<% if ((userProfile.getStFBand()).equals("")) { %>
										<font class="Unset">未登録</font>
									<% } else { %>
										<%= userProfile.getStFBand() %>
									<% } %>
								</div>
							</div>
							<div class="TblHead">
								<div class="TblHeadBlock"></div>
								<div class="TblHeadTitleS">自己紹介</div>
							</div>
							<div class="TblBody">
								<div class="TblValue FullWide" id="stIntroduction">
									<% if ((userProfile.getStComment()).equals("")) { %>
										<font class="Unset">未登録</font>
									<% } else { %>
										<%= userProfile.getStComment() %>
									<% } %>
								</div>
							</div>
							<div class="TblHead">
								<div class="TblHeadBlock"></div>
								<div class="TblHeadTitleS">演奏動画</div>
							</div>
							<div class="TblBody">
								<div class="TblValue FullWide" id="stVideoURL">
									<% if ((userProfile.getStVideoURL()).equals("")) { %>
										<font class="Unset">未登録</font>
									<% } else { %>
										<a href="<%= userProfile.getStVideoURL() %>">動画を見る</a>
									<% } %>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="PfFriend">
					<div class="ContentsShadow">
						<a class="ContentsLink" href="/quetana/Contents/UserProfile?idUser=UI000001">
							<div class="ContentsFull">
								<div class="ListIconArea">
									<img class="Icon30" src="/quetana/img/UI000001.jpg">
								</div>
								<div class="ListUserName">まえぞのりょうた</div>
								<div class="ListBadgeArea">
									<div class="Badge">N</div>
								</div>
								<div class="ListOutline">なに表示しようか</div>
							</div>
						</a>
					</div>
					<div class="ContentsShadow">
						<!-- <a class="ContentsLink" href="/quetana/Contents/UserProfile?idUser=UI000001"> -->
							<div class="ContentsFull">
								<div class="ListIconArea">
									<img class="Icon30" src="/quetana/img/kazzool.png">
								</div>
								<div class="ListUserName">kazzool</div>
								<div class="ListBadgeArea">
								</div>
								<div class="ListOutline">なに表示しようか</div>
							</div>
						<!-- </a> -->
					</div>
					<div class="ContentsShadow">
						<!-- <a class="ContentsLink" href="/quetana/Contents/UserProfile?idUser=UI000001"> -->
							<div class="ContentsFull">
								<div class="ListIconArea">
									<img class="Icon30" src="/quetana/img/gekiyasu.png">
								</div>
								<div class="ListUserName">あべけん</div>
								<div class="ListBadgeArea">
								</div>
								<div class="ListOutline">なに表示しようか</div>
							</div>
						<!-- </a> -->
					</div>				</div>
			</div>
		</div>
		<jsp:include page="../jsp/parts/mainheader.jsp" flush="true" />
		<script src="/quetana/js/common.js"></script>
		<script src="/quetana/js/msgClientCheck.js"></script>
		<script src="/quetana/js/profileView.js"></script>
	</body>
</html>