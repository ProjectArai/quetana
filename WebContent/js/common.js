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
function imgPreView(event){
	  var file = event.target.files[0];
	  var reader = new FileReader();
	  var preview = document.getElementById("preview");
	  var previewImage = document.getElementById("previewImage");

	  if(previewImage != null)
	    preview.removeChild(previewImage);

	  reader.onload = function(event) {
	     var img = document.createElement("img");
	     img.setAttribute("src", reader.result);
	     img.setAttribute("id", "previewImage");
	     preview.appendChild(img);

	  };

	  reader.readAsDataURL(file);
	}
