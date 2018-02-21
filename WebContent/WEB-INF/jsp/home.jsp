<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.UserInfoBean,model.TimeLineBean,java.util.List" %>
<%
//セッションスコープからユーザ情報を取得
UserInfoBean userInfo = (UserInfoBean) session.getAttribute("userInfo");
//リクエストスコープからタイムラインを取得
List<TimeLineBean> arrTimeLine = (List<TimeLineBean>) request.getAttribute("arrTimeLine");
TimeLineBean timeLine = new TimeLineBean();
timeLine = (TimeLineBean) arrTimeLine.get(1);
%>
<!DOCTYPE html>
<html>
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
					<label for="RadioAll">
						<input id="RadioAll" name="TLMenuTabRadio" type="radio" class="nav-unshown" checked>
						<div id="TLMenuTab">すべて</div>
					</label>
					<label for="RadioEvent">
						<input id="RadioEvent" name="TLMenuTabRadio" type="radio" class="nav-unshown">
						<div id="TLMenuTab" class="aaa">イベント</div>
					</label>
					<label for="RadioMember">
						<input id="RadioMember" name="TLMenuTabRadio" type="radio" class="nav-unshown">
						<div id="TLMenuTab">メンバー募集</div>
					</label>
				</div>
				<div id="TLContents">
					<% for (TimeLineBean test : arrTimeLine){
						test.getIdPost();
						test.getIdUser();
						test.getStTitle();
						test.getStPart();
						test.getStGenre();
						test.getStPlace();
						test.getDtEvent();
						test.getStDetails();
						test.getDtUpdate();
						test.getDtResist();
					} %>
				</div>
			</div>
	</body>
</html>