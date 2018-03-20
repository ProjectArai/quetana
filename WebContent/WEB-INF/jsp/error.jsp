<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
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
		<link rel="stylesheet" type="text/css" href="/quetana/css/common/com_other.css">
		<link href="https://fonts.googleapis.com/css?family=Lato:400,900" rel="stylesheet">
	</head>
	<body>
		<div class="Foundation">
			<div class="ContentsMargin"></div>
			<div class="ContentsArea">
				<div class="TitleArea">
					<font class="XL">Sorry.</font>
				</div>
				<div class="TitleArea">
					<font class="L">技術的な問題が発生しました</font>
				</div>
				<div class="MsgArea">
					<font class="ErrMsg">繰り返し同様の操作をしても改善されない場合、<br>お手数ですがシステム管理者までご連絡下さい。</font>
				</div>
				<div class="MsgArea">
					<font class="ErrMsg"><%= errMsg %></font>
				</div>
			</div>
		</div>
		<jsp:include page="../jsp/parts/mainheader.jsp" flush="true" />
	</body>
</html>