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
		<link rel="stylesheet" type="text/css" href="/quetana/css/base.css">
		<link rel="stylesheet" type="text/css" href="/quetana/css/af_login.css">
		<link rel="stylesheet" type="text/css" href="/quetana/css/menu.css">
		<link rel="stylesheet" type="text/css" href="/quetana/css/timeline.css">
		<link href="https://fonts.googleapis.com/css?family=Lato:400,900" rel="stylesheet">
		<script src="/quetana/js/common.js"></script>
	</head>
	<body>
		<jsp:include page="../jsp/parts/mainheader.jsp" flush="true" />
		<div id="ContentsBase">
				<div id="TitleHeader">TimeLine</div>
				<div id="SubMenuBase">
					<label for="RadioAll">
						<input id="RadioAll" type="radio" name="SubMenuRadio" class="unshown" checked>
						<div id="SubMenuTab">すべて</div>
					</label>
					<label for="RadioEvent">
						<input id="RadioEvent" type="radio" name="SubMenuRadio" class="unshown">
						<div id="SubMenuTab">イベント</div>
					</label>
					<label for="RadioMember">
						<input id="RadioMember" type="radio" name="SubMenuRadio" class="unshown">
						<div id="SubMenuTab">メンバー募集</div>
					</label>
				</div>
				<div id="Contents">
					<% for (TimeLineBean test : arrTimeLine){ %>
						<div id="ContentsTable">
							<% if ((test.getIdPost()).charAt(1) == 'E') { %>
								<div id="TLType">ライブ/イベント</div>
							<% } else if ((test.getIdPost()).charAt(1) == 'M') { %>
								<div id="TLType">メンバー募集</div>
							<% } %>
							<div id="TLTitle"><%= test.getStTitle() %></div>
							<% if ((test.getIdPost()).charAt(1) == 'E') { %>
								<div id="TLComment">場所　：<%= test.getStPlace() %><br>開催日：<%= test.getDtEvent() %></div>
							<% } else if ((test.getIdPost()).charAt(1) == 'M') { %>
								<div id="TLComment">募集パート　：<%= test.getStPart() %><br>演奏ジャンル：<%= test.getStGenre() %></div>
							<% } %>
							<div id="TLDetails"><%= test.getStDetails() %></div>
							<div id="TLIcon"><img src="<%= test.getStIconURL() %>" width="40" height="40" class="TLIconRound"></div>
							<div id="TLUserName"><%= test.getStDisplayName() %></div>
							<div id="TLUpdateDate"><%= test.getDtUpdate() %></div>
						</div>
					<% } %>
				</div>
		</div>
	</body>
</html>