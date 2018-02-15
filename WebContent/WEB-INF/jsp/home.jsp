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
					<div id="TLTable">
						<div id="TLIcon"><img src="<%= userInfo.getStIconURL() %>" width="56" height="56"></div>
						<div id="TLUserName"><%= timeLine.getStUserName() %></div>
						<div id="TLType">ライブ/イベント</div>
						<div id="TLTitle">タイトル</div>
						<div id="TLComment">皆様、いかがお過ごしでしょうか。<br>今年もOB/OGライブの季節がやってまいりました。<br>OB/OGの方も、現役のみんなも、ごちゃまぜのライ</div>
					</div>
					<div id="TLTable">
						<div id="TLIcon"><img src="<%= userInfo.getStIconURL() %>" width="54" height="54"></div>
						<div id="TLUserName"><%= timeLine.getStUserName() %></div>
						<div id="TLType">ライブ/イベント</div>
						<div id="TLTitle">１２３４５６７８９０１２３４５６７８９０１２３４５６７８９０１２</div>
						<div id="TLComment">皆様、いかがお過ごしでしょうか。<br>今年もOB/OGライブの季節がやってまいりました。<br>OB/OGの方も、現役のみんなも、ごちゃまぜのライ</div>
					</div>
				</div>
				</div>
			</div>
					<br>
						<%
							for (int cntTL = 0 ; cntTL < arrTimeLine.size() ; cntTL++) {
								TimeLineBean recTL = new TimeLineBean();
								recTL = (TimeLineBean) arrTimeLine.get(cntTL);
								String idPost     = recTL.getIdPost();
								String idUser     = recTL.getIdUser();
								String stUserName = recTL.getStUserName();
								String stTitle    = recTL.getStTitle();
								String cfPost     = recTL.getCfPost();
								out.println(idPost + ", " + idUser + ", " + stUserName + ", " + stTitle + ", " + cfPost + "<br>");
							}
						%>
					<br>
						<table>
							<% for (TimeLineBean test : arrTimeLine){ %>
								<p><%= test.getIdPost() %>, <%= test.getIdUser() %>, <%= test.getStUserName() %>, <%= test.getStTitle() %>, <%= test.getCfPost() %></p>
							<% } %>
						</table>
	</body>
</html>