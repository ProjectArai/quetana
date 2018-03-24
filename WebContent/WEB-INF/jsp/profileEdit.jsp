<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.bean.UserProfileBean, java.util.Map" %>
<%
//リクエストスコープからプロフィールを取得
UserProfileBean userProfile = (UserProfileBean) request.getAttribute("userProfile");
Map chkPart = (Map)userProfile.getChkPart();

//requestスコープから各textareaの高さを取得
String stHeightArray = (String)request.getAttribute("stHeightArray");

//エラーメッセージがあれば保持
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
		<link rel="stylesheet" type="text/css" href="/quetana/css/function/profile.css">
		<link rel="stylesheet" type="text/css" href="/quetana/css/parts/select_part.css">
		<link href="https://fonts.googleapis.com/css?family=Lato:400,900" rel="stylesheet">
	</head>
	<body onLoad="setVideoURLHeight();setTextareaHeight('<%= stHeightArray %>')">
		<form name="formEdit" enctype="multipart/form-data" action="/quetana/Contents/ProfileEdit" method="post">
		<div class="Foundation">
			<div class="PageTitle">プロフィール編集</div>
			<div class="ContentsHead">
				<label for="RadioBasic" class="noshadow">
					<input id="RadioBasic" type="radio" name="TabRadio" class="TabCheck unshown" checked>
					<div class="Tab">基本情報</div>
				</label>
				<label for="RadioFriend" class="noshadow">
					<input id="RadioFriend" type="radio" name="TabRadio" class="TabCheck unshown">
					<div class="Tab">友達リスト</div>
				</label>
			</div>
			<div class="ContentsArea">
				<div class="ContentsShadow">
					<div class="ContentsFull">
						<label for="IconInput" class="noshadow">
							<input id="IconInput" type="file" name="imgIcon" accept="image/*" class="unshown" onChange="setImage(event)">
							<div id="IconArea" class="IconView AfChangeIcon" style="background-image: url(<%= userProfile.getStIconURL() %>)">
								<div class="IconCover">⊕</div>
							</div>
						</label>
						<div class="TblHead">
							<div class="TblHeadBlock"></div>
							<div class="TblHeadTitleS">基本データ</div>
						</div>
						<div class="TblBody">
							<div class="TblKey">表示名</div>
							<input class="TblValue" type="text" name="stDisplayName" maxlength="10" placeholder="表示名" value="<%= userProfile.getStDisplayName() %>">
						</div>
						<div class="TblBody">
							<div class="TblKey">年齢</div>
							<input class="TblValue" type="text" name="nmAge" maxlength="3" placeholder="年齢" value="<%= userProfile.getNmAge() %>">
						</div>
						<div class="TblBody">
							<div class="TblKey">入学年</div>
							<input class="TblValue" type="text" name="nmAddYear" maxlength="4" placeholder="入学年度" value="<%= userProfile.getNmAddYear() %>">
						</div>
						<div class="TblBody">
							<div class="TblKey">担当パート</div>
							<div class="PartArea">
								<label for="checkVo" class="noshadow">
									<input id="checkVo" type="checkbox" name="chkPart" value="01" class="PartCheck unshown" <%= chkPart.get("01") %>>
									<div class="part">Vo.</div>
								</label>
								<label for="checkGt" class="noshadow">
									<input id="checkGt" type="checkbox" name="chkPart" value="02" class="PartCheck unshown" <%= chkPart.get("02") %>>
									<div class="part">Gt.</div>
								</label>
								<label for="checkBa" class="noshadow">
									<input id="checkBa" type="checkbox" name="chkPart" value="03" class="PartCheck unshown" <%= chkPart.get("03") %>>
									<div class="part">Ba.</div>
								</label>
								<label for="checkKey" class="noshadow">
									<input id="checkKey" type="checkbox" name="chkPart" value="04" class="PartCheck unshown" <%= chkPart.get("04") %>>
									<div class="part">Key.</div>
								</label>
								<label for="checkDr" class="noshadow">
									<input id="checkDr" type="checkbox" name="chkPart" value="05" class="PartCheck unshown" <%= chkPart.get("05") %>>
									<div class="part">Dr.</div>
								</label>
								<label for="checkPer" class="noshadow">
									<input id="checkPer" type="checkbox" name="chkPart" value="06" class="PartCheck unshown" <%= chkPart.get("06") %>>
									<div class="part">Per.</div>
								</label>
								<label for="checkCho" class="noshadow">
									<input id="checkCho" type="checkbox" name="chkPart" value="07" class="PartCheck unshown" <%= chkPart.get("07") %>>
									<div class="part">Cho.</div>
								</label>
								<label for="checkOther" class="noshadow">
									<input id="checkOther" type="checkbox" name="chkPart" value="08" class="PartCheck unshown" <%= chkPart.get("08") %>>
									<div class="part">Other</div>
								</label>
							</div>
						</div>
						<div class="TblHead">
							<div class="TblHeadBlock"></div>
							<div class="TblHeadTitleS">お気に入り</div>
						</div>
						<div class="TblBody">
							<div class="TblKey">ジャンル</div>
							<textarea id="stGenre" class="TblValue" name="stGenre" placeholder="ジャンル" onFocus="dynamicHeightChanger('stGenre')"><%= userProfile.getStFGenre() %></textarea>
						</div>
						<div class="TblBody">
							<div class="TblKey">バンド</div>
							<textarea id="stBand" class="TblValue" name="stBand" placeholder="バンド" onFocus="dynamicHeightChanger('stBand')"><%= userProfile.getStFBand() %></textarea>
						</div>
						<div class="TblHead">
							<div class="TblHeadBlock"></div>
							<div class="TblHeadTitleS">自己紹介</div>
						</div>
						<div class="TblBody">
							<textarea id="stIntroduction" class="FullWide" name="stIntroduction" placeholder="自己紹介" onFocus="dynamicHeightChanger('stIntroduction')"><%= userProfile.getStComment() %></textarea>
						</div>
						<div class="TblHead">
							<div class="TblHeadBlock"></div>
							<div class="TblHeadTitleS">演奏動画</div>
						</div>
						<div class="TblBody">
							<textarea id="stVideoURL" class=FullWide name="stVideoURL" placeholder="YoutubeURL" onfocus="dynamicHeightChanger('stVideoURL')"><%= userProfile.getStVideoURL() %></textarea>
						</div>
						<input type="hidden" name="stIconURL_org" value="<%= userProfile.getStIconURL() %>">
						<!-- ★この下のやつは一時的なやつなのでロジック直したら消す -->
						<input type="hidden" name="stPart" value="<%= userProfile.getStPart() %>">
					</div>
				</div>
			</div>
		</div>
		<jsp:include page="../jsp/parts/editheader.jsp" flush="true" />
		</form>
		<script src="/quetana/js/common.js"></script>
		<script src="/quetana/js/profileEdit.js"></script>
	</body>
</html>