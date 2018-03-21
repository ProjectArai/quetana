<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
// 指定された戻り先を取得
String stReturnURL = (String)request.getAttribute("stReturnURL");
%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width, maximum-scale=1.0">
		<meta name="format-detection" content="telephone=no">
		<title>Quetana</title>
	</head>
	<body>
		<div class="HeaderBase">
			<div class="BtnBase">
				<input class="BackBtn" type="button" value="×" onClick="location.href='<%= stReturnURL %>'">
			</div>
			<div class="HeaderLogo">Quetana</div>
			<div class="BtnBase">
<!--
				<img class="HeaderBtn" src="/quetana/img/fix.jpg">
 -->
				<input type="submit" name="fixBtn" class="fix" value="" onclick="getHeightTextare(arrTextareaId)">
			</div>
		</div>
		<script src="/quetana/js/common.js"></script>
	</body>
</html>
