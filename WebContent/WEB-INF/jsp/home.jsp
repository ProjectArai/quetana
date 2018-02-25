<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.TimeLineBean,java.util.List" %>
<%
//リクエストスコープからタイムラインを取得
List<TimeLineBean> arrTimeLine = (List<TimeLineBean>) request.getAttribute("arrTimeLine");
%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width">
		<title>Quetana</title>
		<link rel="stylesheet" type="text/css" href="/quetana/css/main.css">
		<link rel="stylesheet" type="text/css" href="/quetana/css/menu.css">
		<link href="https://fonts.googleapis.com/css?family=Lato:400,900" rel="stylesheet">
		<script src="/quetana/js/common.js"></script>
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
						<div id="TLMenuTab">イベント</div>
					</label>
					<label for="RadioMember">
						<input id="RadioMember" name="TLMenuTabRadio" type="radio" class="nav-unshown">
						<div id="TLMenuTab">メンバー募集</div>
					</label>
				</div>
				<div id="TLContents">
					<% for (TimeLineBean test : arrTimeLine){ %>
						<div id="TLTable">
							<div id="TLTitle"><%= test.getStTitle() %></div>
							<div id="TLIcon"><img src="<%= test.getStIconURL() %>" width="50" height="50" class="BorderRadius"></div>
							<div id="TLUserName"><%= test.getStDisplayName() %></div>
							<div id="TLUpdateDate"><%= test.getDtUpdate() %></div>
							<% if ((test.getIdPost()).charAt(1) == 'E') { %>
								<div id="TLComment">■ライブ/イベント情報<br>場所　：<%= test.getStPlace() %><br>開催日：<%= test.getDtEvent() %></div>
							<% } else if ((test.getIdPost()).charAt(1) == 'M') { %>
								<div id="TLComment">■メンバー募集情報<br>募集パート　：<%= test.getStPart() %><br>演奏ジャンル：<%= test.getStGenre() %></div>
							<% } %>
							<div id="TLComment"><%= test.getStDetails() %></div>
						</div>
					<% } %>
				</div>
			</div>
	</body>
</html>