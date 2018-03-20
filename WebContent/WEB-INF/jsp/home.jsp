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
		<meta name="viewport" content="width=device-width, maximum-scale=1.0">
		<meta name="format-detection" content="telephone=no">
		<title>Quetana</title>
		<link rel="stylesheet" type="text/css" href="/quetana/css/common/com.css">
		<link rel="stylesheet" type="text/css" href="/quetana/css/common/com_basic.css">
		<link rel="stylesheet" type="text/css" href="/quetana/css/function/home.css">
		<link rel="stylesheet" type="text/css" href="/quetana/css/switchdisp/tl_all.css" id="DispStyle">
		<link href="https://fonts.googleapis.com/css?family=Lato:400,900" rel="stylesheet">
	</head>
	<body onLoad="changeDispContents()">
		<div class="Foundation">
			<div class="PageTitle">Home/TimeLine</div>
			<div class="ContentsHead">
				<label for="RadioAll" class="noshadow">
					<input id="RadioAll" type="radio" name="TabRadio" class="TabCheck unshown" onclick="changeDispContents()" checked>
					<div class="Tab">すべて</div>
				</label>
				<label for="RadioEvent" class="noshadow">
					<input id="RadioEvent" type="radio" name="TabRadio" class="TabCheck unshown" onclick="changeDispContents()">
					<div class="Tab">イベント</div>
				</label>
				<label for="RadioMember" class="noshadow">
					<input id="RadioMember" type="radio" name="TabRadio" class="TabCheck unshown" onclick="changeDispContents()" >
					<div class="Tab">メンバー募集</div>
				</label>
			</div>
			<div class="ContentsArea">
				<% for (TimeLineBean tlRecord : arrTimeLine){ %>
					<% if ((tlRecord.getCfPost()).equals("E")) { %>
						<div class="TLEvent">
							<div class="ContentsShadow">
								<a class="ContentsLink" href="/quetana/Contents/PostView?idPost=<%= tlRecord.getIdPost() %>">
									<div class="ContentsFull">
										<div class="TblHead">
											<div class="TblHeadOutline">イベント（<%= tlRecord.getStOutLine() %>）</div>
											<div class="TblHeadTitleL"><%= tlRecord.getStTitle() %></div>
										</div>
										<div class="TblBody">
											<div class="TblValue FullWide size-S"><%= tlRecord.getStDetailsOmit() %> … <font class="more">もっと見る</font></div>
										</div>
										<img class="Icon20 Left" src="<%= tlRecord.getStIconURL() %>">
										<div class="UserName20">by <%= tlRecord.getStDisplayName() %></div>
										<div class="Update20"><%= tlRecord.getDtUpdateDT() %></div>
									</div>
								</a>
							</div>
						</div>
					<% } else if ((tlRecord.getCfPost()).equals("M")) { %>
						<div class="TLMember">
							<div class="ContentsShadow">
								<a class="ContentsLink" href="/quetana/Contents/PostView?idPost=<%= tlRecord.getIdPost() %>">
									<div class="ContentsFull">
										<div class="TblHead">
											<div class="TblHeadOutline">メンバー募集（<%= tlRecord.getStOutLine() %>）</div>
											<div class="TblHeadTitleL"><%= tlRecord.getStTitle() %></div>
										</div>
										<div class="TblBody">
											<div class="TblValue FullWide size-S"><%= tlRecord.getStDetailsOmit() %> … <font class="more">もっと見る</font></div>
										</div>
										<img class="Icon20 Left" src="<%= tlRecord.getStIconURL() %>">
										<div class="UserName20">by <%= tlRecord.getStDisplayName() %></div>
										<div class="Update20"><%= tlRecord.getDtUpdateDT() %></div>
									</div>
								</a>
							</div>
						</div>
					<% } %>
				<% } %>
				<div class="TLMember">
					<div class="ContentsShadow">
						<a class="ContentsLink" href="/quetana/postview_t1.jsp">
							<div class="ContentsFull">
								<div class="TblHead">
									<div class="TblHeadOutline">メンバー募集（Ba, Dr）</div>
									<div class="TblHeadTitleL">Beatles（コピー）のメンバーを探しています！</div>
								</div>
								<div class="TblBody">
									<div class="TblValue FullWide size-S">
										ライブの日取りはまだ決まっていないのですが、theBeatlesのコピーバンドを組んでスタジオで合わせたいと思っています！！興味 … <font class="more">もっと見る</font>
									</div>
								</div>
								<img class="Icon20 Left" src="/quetana/img/UI000001.jpg">
								<div class="UserName20">by まえぞのりょうた</div>
								<div class="Update20">2018/12/31 23:55</div>
							</div>
						</a>
					</div>
				</div>
				<div class="TLEvent">
					<div class="ContentsShadow">
						<a class="ContentsLink" href="/quetana/postview_t2.jsp">
							<div class="ContentsFull">
								<div class="TblHead">
									<div class="TblHeadOutline">イベント（2018/02/09＠池袋ロサ）</div>
									<div class="TblHeadTitleL">OB/OGライブ 2018</div>
								</div>
								<div class="TblBody">
									<div class="TblValue FullWide size-S">
										皆様、いかがお過ごしでしょうか。今年もOB/OGライブの季節がやってまいりました。OB/OGの方も、現役のみんなも、ごちゃまぜの … <font class="more">もっと見る</font>
									</div>
								</div>
								<img class="Icon20 Left" src="/quetana/img/UI000001.jpg">
								<div class="UserName20">by まえぞのりょうた</div>
								<div class="Update20">2018/12/31 23:55</div>
							</div>
						</a>
					</div>
				</div>
				<div class="TLMember">
					<div class="ContentsShadow">
						<a class="ContentsLink" href="/quetana/postview_t1.jsp">
							<div class="ContentsFull">
								<div class="TblHead">
									<div class="TblHeadOutline">メンバー募集（Vo. Gt. Ba. Key. Dr. Per. Cho. Other）</div>
									<div class="TblHeadTitleL">１２３４５６７８９０１２３４５６７８９０１２３４５６７８９０１２</div>
								</div>
								<div class="TblBody">
									<div class="TblValue FullWide size-S">
										１２３４５６７８９０１２３４５６７８９０１２３４５６７８９０１２３４５６７８９０１２３４５６７８９０１２３４５６７８９０１２３４ … <font class="more">もっと見る</font>
									</div>
								</div>
								<img class="Icon20 Left" src="/quetana/img/default-icon.jpg">
								<div class="UserName20">by １２３４５６７８９０</div>
								<div class="Update20">9999/99/99 99:99</div>
							</div>
						</a>
					</div>
				</div>
				<div class="TLEvent">
					<div class="ContentsShadow">
						<a class="ContentsLink" href="/quetana/postview_t2.jsp">
							<div class="ContentsFull">
								<div class="TblHead">
									<div class="TblHeadOutline">イベント（9999/99/99＠１２３４５６７８９０１２３４５６）</div>
									<div class="TblHeadTitleL">１２３４５６７８９０１２３４５６７８９０１２３４５６７８９０１２</div>
								</div>
								<div class="TblBody">
									<div class="TblValue FullWide size-S">
										１２３４５６７８９０１２３４５６７８９０１２３４５６７８９０１２３４５６７８９０１２３４５６７８９０１２３４５６７８９０１２３４ … <font class="more">もっと見る</font>
									</div>
								</div>
								<img class="Icon20 Left" src="/quetana/img/default-icon.jpg">
								<div class="UserName20">by １２３４５６７８９０</div>
								<div class="Update20">9999/99/99 99:99</div>
							</div>
						</a>
					</div>
				</div>
			</div>
			<div class="FotterArea">
				<div class="TestArea">
					<div class="PageMove now">1</div>
					<div class="PageMove">2</div>
					<div class="PageMove">3</div>
					<div class="PageMove">4</div>
					<div class="PageMove">></div>
				</div>
			</div>
		</div>
		<input class="NewPostBtn" type="button" value="" onClick="location.href='/quetana/Contents/Post'">
		<jsp:include page="../jsp/parts/mainheader.jsp" flush="true" />
		<script src="/quetana/js/common.js"></script>
		<script src="/quetana/js/home.js"></script>
	</body>
</html>