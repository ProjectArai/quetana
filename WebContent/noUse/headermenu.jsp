<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
	<head>
		<link rel="stylesheet" type="text/css" href="/quetana/css/menu2.css">
		<link rel="stylesheet" type="text/css" href="/quetana/css/base2.css">
	</head>
<div id ="MenuBase">
	<div id=HBMenuUser>
		<div id="HBMIcon"><img src="/quetana/img/default-icon.jpg" width="40" height="40" class="IconRound"></div>
		<div id="HBMAccountName">test0001</div>
	</div>
	<input id="HBMenuLink" type="button" value="ホーム" onClick="location.href='/quetana/Contents/Home'">
	<input id="HBMenuLink" type="button" value="マイプロフィール" onClick="location.href='/quetana/Contents/UserProfile'">
	<input id="HBMenuLink" type="button" value="投稿ページ" onClick="location.href='/quetana/Contents/Post'">
	<div id=HBMenuBlank></div>
	<input id="HBMenuLink" type="button" value="設定">
	<div id=HBMenuBlank></div>
	<input id="HBMenuLink" type="button" value="ログアウト" onClick="location.href='/quetana/Logout'">
	<div id=HBMenuMSG>Copyright 2018 Quetana by ProjectArai</div>
</div>
<div id="test">
	<input id="MenuCheck2" type="checkbox">
	<div id="HBMenuContents">
		<div id="HeaderBase">
			<div id="HBMenuBase">
				<div id="HBMenuBtn">
					<div id="HBMenuMark">
						<hr id="HBMenuMarkLine">
						<hr id="HBMenuMarkLine">
						<hr id="HBMenuMarkLine">
					</div>
				</div>
			</div>
			<div id="HeaderLogo">Quetana</div>
		</div>
	</div>
	<label for="MenuCheck2" id="HBMenuBG"></label>
</div>
