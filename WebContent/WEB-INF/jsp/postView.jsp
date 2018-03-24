<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.bean.PostViewBean,model.bean.LoginUserInfoBean" %>
<%
//セッションスコープからユーザ情報を取得
LoginUserInfoBean loginUserInfo = (LoginUserInfoBean) session.getAttribute("loginUserInfo");
//リクエストスコープから投稿内容を取得
PostViewBean beanPV = (PostViewBean) request.getAttribute("beanPV");
//リクエストスコープからプロフィール編集権限を取得
String perEdit = (String) request.getAttribute("perEdit");
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
			<div class="PageTitle">
			<% if ((beanPV.getCfPostType()).equals("E")) { %>
				イベント告知
			<% } else if ((beanPV.getCfPostType()).equals("M")) { %>
				バンドメンバー募集
			<% } %>
			</div>
			<div class="ContentsHeadLine"></div>
			<div class="ContentsArea">
				<div class="ContentsShadow">
					<div class="ContentsTop">
						<param id="cfPostType" value="<%= beanPV.getCfPostType() %>">
						<% if (perEdit.equals("Y")) { %>
							<form name="formEdit" action="/quetana/Contents/PostView" method="Post">
								<input type="submit" class="Button30 Right edit" value="" onclick="getHeightTextare(arrTextareaId)">
								<input type="hidden" name="idPost" value="<%= beanPV.getIdPost() %>">
							</form>
						<% } %>
						<a class="noshadow" id="TLLink" href="/quetana/Contents/ProfileView?idUser=<%= beanPV.getIdUser() %>">
							<img class="Icon30 Left" src="<%= beanPV.getStIconURL() %>">
							<div class="UserName30"><%= beanPV.getStDisplayName() %></div>
						</a>
						<div class="TblBody">
							<div class="TblHeadTitleL" id="stTitle"><%= beanPV.getStTitle() %></div>
						</div>
						<div class="TblHead">
							<div class="TblHeadBlock"></div>
							<div class="TblHeadTitleS">概略</div>
						</div>
						<% if ((beanPV.getCfPostType()).equals("E")) { %>
							<div class="TblBody">
								<div class="TblKey">開催日</div>
								<div class="TblValue"><%= beanPV.getDtEvent() %></div>
							</div>
							<div class="TblBody">
								<div class="TblKey">開催場所</div>
								<div class="TblValue" id="stPlace"><%= beanPV.getStPlace() %></div>
							</div>
						<% } else if ((beanPV.getCfPostType()).equals("M")) { %>
							<div class="TblBody">
								<div class="TblKey">募集パート</div>
								<div class="TblValue"><%= beanPV.getStPart() %></div>
							</div>
							<div class="TblBody">
								<div class="TblKey">ジャンル</div>
								<div class="TblValue" id="stGenre"><%= beanPV.getStGenre() %></div>
							</div>
						<% } %>
						<div class="TblHead">
							<div class="TblHeadBlock"></div>
							<div class="TblHeadTitleS">詳細</div>
						</div>
						<div class="TblBody">
							<div class="TblValue FullWide" id="stDetails"><pre><%= beanPV.getStDetails() %></pre></div>
						</div>
						<div class="Update20"><%= beanPV.getDtUpdate() %></div>
					</div>
					<div class="ContentsMiddle">
						<div class="BalloonBase">
							<img class="Icon25 Left" src="/quetana/img/gekiyasu.png">
							<div class="TirLeft"></div>
							<div class="Balloon Left">
								<a id="TLLink" href="/quetana/Contents/ProfileView?idUser=UI000009">あべけん</a>
								ドラム加入希望です！
							</div>
						</div>
						<div class="BalloonBase">
							<img class="Icon25 Left" src="/quetana/img/kazzool.png">
							<div class="TirLeft"></div>
							<div class="Balloon Left">
								<a id="TLLink" href="/quetana/Contents/ProfileView?idUser=UI000001">kazzool</a>
								はじめまして、2014年卒のkazzoolです！ベース加入希望ですがもう決まってしまいましたか？
							</div>
						</div>
						<div class="BalloonBase">
							<img class="Icon25 Right" src="/quetana/img/UI000001.jpg">
							<div class="TirRight"></div>
							<div class="Balloon Right">
								<a id="TLLink" href="/quetana/Contents/ProfileView?idUser=UI000001">まえぞのりょうた</a>
								あべけん、kazzool> ありがとうございます！！まだ決まってないのでお願いします！
							</div>
						</div>
					</div>
					<div class="ContentsBottom">
						<div class="CommentBase">
							<img class="Icon30 Left" src="<%= loginUserInfo.getStIconURL() %>">
							<form name="formComment" action="/quetana/Contents/Home" method="Get">
								<textarea id="stComment" class="CommentForm" name="stComment" maxlength="128" placeholder="コメントする" onfocus="dynamicHeightChanger('stComment')"></textarea>
								<input type="submit" name="btnComment" class="Icon30 Right comment" value="">
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
		<jsp:include page="../jsp/parts/mainheader.jsp" flush="true" />
		<script src="/quetana/js/common.js"></script>
		<script src="/quetana/js/postview.js"></script>
	</body>
</html>