<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.TimeLineBean,java.util.List" %>
<%
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
						out.println(test.getIdPost());
						out.println(test.getIdUser());
						out.println(test.getStTitle());
						out.println(test.getStPart());
						out.println(test.getStGenre());
						out.println(test.getStPlace());
						out.println(test.getDtEvent());
						out.println(test.getStDetails());
						out.println(test.getDtUpdate());
						out.println(test.getDtResist());
					} %>
				</div>
			</div>
	</body>
</html>