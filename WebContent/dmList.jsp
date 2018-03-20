<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.UserProfileBean" %>
<%
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
		<link rel="stylesheet" type="text/css" href="/quetana/css/function/dmList.css">
		<link href="https://fonts.googleapis.com/css?family=Lato:400,900" rel="stylesheet">
	</head>
	<body>
		<div class="Foundation">
			<div class="PageTitle">メッセージ履歴一覧</div>
			<div class="ContentsHeadLine"></div>
			<div class="ContentsArea">
					<div class="ContentsShadow">
						<a class="ContentsLink" href="/quetana/dmTalk.jsp">
							<div class="ContentsFull">
								<div class="IconArea">
									<img class="Icon30" src="/quetana/img/UI000001.jpg">
								</div>
								<div class="Recipient">まえぞのりょうた</div>
								<div class="BadgeArea">
								</div>
								<div class="Update20">12/31 23:59</div>
								<div class="Outline">まえぞのりょうたです！！よろしくおねがい …</div>
							</div>
						</a>
					</div>
					<div class="ContentsShadow">
						<a class="ContentsLink" href="/quetana/dmTalk.jsp">
							<div class="ContentsFull">
								<div class="IconArea">
									<img class="Icon30" src="/quetana/img/kazzool.png">
								</div>
								<div class="Recipient">kazzool</div>
								<div class="BadgeArea">
									<div class="Badge">3</div>
								</div>
								<div class="Update20">12/31 23:59</div>
								<div class="Outline">Beatlesのコピバンの件ですが、メン …</div>
							</div>
						</a>
					</div>
					<div class="ContentsShadow">
						<a class="ContentsLink" href="/quetana/dmTalk.jsp">
							<div class="ContentsFull">
								<div class="IconArea">
									<img class="Icon30" src="/quetana/img/default-icon.jpg">
								</div>
								<div class="Recipient">テスト　テスト三郎</div>
								<div class="BadgeArea">
									<div class="Badge">99</div>
								</div>
								<div class="Update20">12/31 23:59</div>
								<div class="Outline">１２３４５６７８９０１２３４５６７８９０ …</div>
							</div>
						</a>
					</div>
					<div class="ContentsShadow">
						<a class="ContentsLink" href="/quetana/dmTalk.jsp">
							<div class="ContentsFull">
								<div class="IconArea">
									<img class="Icon30" src="/quetana/img/admin-icon.jpg">
								</div>
								<div class="Recipient">QuetanaAdmin</div>
								<div class="BadgeArea">
									<div class="Badge">1</div>
								</div>
								<div class="Update20">12/31 23:59</div>
								<div class="Outline">Quetanaへようこそ</div>
							</div>
						</a>
					</div>
			</div>
		</div>
		<jsp:include page="./WEB-INF/jsp/parts/mainheader.jsp" flush="true" />
		<script src="/quetana/js/common.js"></script>
	</body>
</html>