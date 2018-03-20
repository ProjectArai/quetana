//投稿画面のtextareaの初期heightを設定
//var arrHeight = "stTitle=30,stGenre=30,stPlace=30,stDetails=118";

//投稿種別によるフォームの表示・非表示を制御
function dispPostOutline() {

	cfPostType = document.getElementById("cfPostType").value;

	 if (cfPostType == "M") {
	    document.getElementById("PostMember").style.display="block";
	    document.getElementById("PostEvent").style.display="none";

	} else if (cfPostType == "E") {
		document.getElementById("PostMember").style.display="none";
		document.getElementById("PostEvent").style.display="block";

	} else {
	    document.getElementById("PostMember").style.display="none";
	    document.getElementById("PostEvent").style.display="none";
	}

}

// 新規or修正による投稿種別プルダウンの表示・非表示を制御(onLoad時のみ)
function dispPostTypePulldown(cfPostMode) {

	 if (cfPostMode == "N") {
	    document.getElementById("PostType").style.display="block";

	} else if (cfPostMode == "F") {
		document.getElementById("PostType").style.display="none";
	}

}
