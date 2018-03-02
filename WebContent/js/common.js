//画面表示時にエラーメッセージを出す
function dispErrMsg(errMsg) {
	if (errMsg != "null") {
	    alert(errMsg);
	}
}
//TLの表示変更(cssで制御)
function changesytle(cssfile) {
	document.getElementById("TLStyle").href = cssfile;
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

//プロフィール画像変更時、submit前にプレビューさせる処理
function setImage(event){
	var file = event.target.files[0];
	var reader = new FileReader();
	var divIconView = document.getElementById("PFIconView");

	reader.onload = function(event) {
		var style = 'background-image: url(' + reader.result + ')'
		divIconView.setAttribute('style', style);
	};

	reader.readAsDataURL(file);
}
