// stCommentの初期heightを設定
var isc = document.getElementById("stComment");
isc.style.height = "30px";

// 表示時のheight取得が必要な要素IDを定義
cfPostType = document.getElementById("cfPostType").value;
if (cfPostType == "E") {
	var arrTextareaId = ['stTitle', 'stPlace', 'stDetails'];
} else if (cfPostType == "M") {
	var arrTextareaId = ['stTitle', 'stGenre', 'stDetails'];
}

