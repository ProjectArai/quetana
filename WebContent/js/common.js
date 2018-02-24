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
