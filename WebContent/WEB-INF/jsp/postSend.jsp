<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.bean.PostViewBean,model.TimeLineBean,java.util.Map, java.util.Date, java.text.SimpleDateFormat, model.LoginUserInfoBean" %>
<%
// セッションスコープからユーザ情報を取得
LoginUserInfoBean loginUserInfo = (LoginUserInfoBean) session.getAttribute("loginUserInfo");

// requestスコープから投稿モードを取得(新規：N、修正：F)
String cfPostMode = (String)request.getAttribute("cfPostMode");

// requestスコープから投稿内容(新規の場合は基本空)を取得
PostViewBean beanPV = (PostViewBean)request.getAttribute("beanPV");
String stTitle = (String)beanPV.getStTitle();
Map chkPart = (Map)beanPV.getChkPart();
String stGenre = (String)beanPV.getStGenre();
String stPlace = (String)beanPV.getStPlace();
String dtEvent = (String)beanPV.getDtEvent();
String stDetails = (String)beanPV.getStDetails();
String cfPostType = (String)beanPV.getCfPostType();

// 投稿種別プルダウンの初期選択状態設定
String chkN = "";
String chkM = "";
String chkE = "";
if (cfPostType.equals("M")) {
	chkM = "selected";
} else if (cfPostType.equals("E")) {
	chkE = "selected";
} else {
	chkN = "selected";
}

// sessionスコープからプロフィール表示画面での各要素の高さを取得、取得後は不要なので破棄
String stHeightArray = (String)request.getAttribute("stHeightArray");

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
	<body onLoad="dispPostTypePulldown('<%= cfPostMode %>');dispPostOutline();dispErrMsg('<%= errMsg %>');setTextareaHeight('<%= stHeightArray %>')">
		<form name="formEdit" action="/quetana/Contents/PostSend" method="post">
		<div class="Foundation">
			<div class="PageTitle">
				<% if (cfPostMode.equals("N")) { %>
					新規投稿
				<% } else if (cfPostMode.equals("F")) { %>
					投稿内容修正
				<% } %>
			</div>
			<div class="ContentsHeadLine"></div>
			<div class="ContentsArea">
				<div class="ContentsShadow">
					<div class="ContentsFull">
 						<img class="Icon30 Left" src="/quetana/img/default-icon.jpg">
						<div class="UserName30">テスト山　テスト一郎</div>
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
								<select id="cfPostType" class="TblValue" name="cfPostType" onChange="dispPostOutline();">
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
						<input type="hidden" name="cfPostMode" value="<%= cfPostMode %>">
						<% if (cfPostMode.equals("F")) { %>
							<input type="hidden" name="idPost" value="<%= beanPV.getIdPost() %>">
						<% } %>
					</div>
				</div>
			</div>
		</div>
		<jsp:include page="../jsp/parts/editheader.jsp" flush="true" />
		</form>
		<script src="/quetana/js/common.js"></script>
		<script src="/quetana/js/postsend.js"></script>
	</body>
</html>