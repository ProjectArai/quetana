// 表示時のheight取得が必要な要素IDを定義
var arrTextareaId = ['stGenre', 'stBand', 'stIntroduction'];

// プロフィール画像変更時、submit前にプレビューさせる処理
function setImage(event){
	var file = event.target.files[0];
	var reader = new FileReader();
	var divIconView = document.getElementById("IconArea");

	reader.onload = function(event) {
		var style = 'background-image: url(' + reader.result + ')'
		divIconView.setAttribute('style', style);
	};
	reader.readAsDataURL(file);
}

// URL編集エリアの初期height
function setVideoURLHeight() {
	var isu = document.getElementById("stVideoURL");
	isu.style.height = "52px"; //とりあえず固定で2行 + padding上下4pxずつ = 52px
}
