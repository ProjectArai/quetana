//投稿画面のtextareaの初期heightを設定
var arrHeight = "stTitle=30,stGenre=30,stPlace=30,stDetails=118";

//投稿種別によるフォームの表示・非表示を制御
function dispPostForm() {

	cfPost = document.getElementById("cfPost").value;

	 if (cfPost == "M") {
	    document.getElementById("PostMember").style.display="block";
	    document.getElementById("PostEvent").style.display="none";

	} else if (cfPost == "E") {
		document.getElementById("PostMember").style.display="none";
		document.getElementById("PostEvent").style.display="block";

	} else {
	    document.getElementById("PostMember").style.display="none";
	    document.getElementById("PostEvent").style.display="none";
	}

}