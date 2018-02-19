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
							<% for (TimeLineBean test : arrTimeLine){ %>
								<div id="TLTable">
									<div id="TLIcon"><img src="<%= test.getStIconURL() %>" width="56" height="56"></div>
									<div id="TLUserName"><%= test.getStUserName() %></div>
									<% if ((test.getCfPost()).equals("E")) { %>
										<div id="TLType">ライブ/イベント</div>
									<% } else if ((test.getCfPost()).equals("M")) { %>
										<div id="TLType">メンバー募集</div>
									<% } %>
									<div id="TLTitle"><%= test.getStTitle() %></div>
									<% if ((test.getCfPost()).equals("E")) { %>
										<div id="TLTitle">場所　：<%= test.getStPlace() %><br>開催日：<%= test.getDtEvent() %></div>
									<% } else if ((test.getCfPost()).equals("M")) { %>
										<div id="TLTitle">募集パート　：<%= test.getStRecPart() %><br>演奏ジャンル：<%= test.getStGenre() %></div>
									<% } %>
									<div id="TLComment"><%= test.getStDetails() %></div>
								</div>
							<% } %>
				</div>
			</div>
	</body>
</html>