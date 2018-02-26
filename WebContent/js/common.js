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
//投稿画面フォーム表示・非表示
function dispPostForm(cfPost) {
	if (cfPost == "M") {
	    document.getElementById("PostMember").style.display="block";
	    document.getElementById("PostEvent").style.display="none";
	} else if (cfPost == "E") {
		document.getElementById("PostMember").style.display="none";
		document.getElementById("PostEvent").style.display="block";
	}
}

//バリデーションチェック
//パスワードの一致確認
function checkPassword() {
	if(f1.stPassword.value != f1.stConfirmPassword.value) {
		alert("パスワードと確認用パスワードが一致しません");
		return false;
	} else {
		return true;
	}
}
