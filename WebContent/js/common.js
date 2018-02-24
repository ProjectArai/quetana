//画面表示時にメッセージ出す
function loadtest() {
    alert("test");
}

//画面表示時にメッセージ出す
function dispMsg() {
	if (infoMsg != "") {
	    alert(infoMsg);
	}
}
// パスワードの一致確認
function checkPassword() {
	if(f1.stPassword.value != f1.stConfirmPassword.value) {
		alert("パスワードと確認用パスワードが一致しません");
		return false;
	} else {
		return true;
	}
}
// 投稿画面・メンバー募集フォーム表示
function displayMember() {
    document.getElementById("stPart").style.display="block";
    document.getElementById("stGenre").style.display="block";
    document.getElementById("stPlace").style.display="none";
    document.getElementById("dtEvent").style.display="none";
}
// 投稿画面・イベント告知フォーム表示
function displayEvent() {
    document.getElementById("stPart").style.display="none";
    document.getElementById("stGenre").style.display="none";
    document.getElementById("stPlace").style.display="block";
    document.getElementById("dtEvent").style.display="block";
}