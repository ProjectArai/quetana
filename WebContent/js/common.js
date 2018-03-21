var NOMAL_LINE_HEIGHT = "22px";
var TITLE_LINE_HEIGHT = "22px";
var DETAILS_HEIGHT = 184;

// 画面表示時にinfoメッセージを出す
function dispInfoMsg(infoMsg) {
	if (infoMsg != "null") {
	    alert(infoMsg);
	}
}
// 画面表示時にエラーメッセージを出す
function dispErrMsg(errMsg) {
	if (errMsg != "null") {
	    alert(errMsg);
	}
}
// アラートダイアログ表示処理
function dispAlertDialog(idMsg) {
	if (idMsg != "null") {
	    alert(msgList[idMsg]);
	}
}
// 確認ダイアログ表示処理
function dispConfirmDialog(idMsg) {
	if (window.confirm(msgList[idMsg])) {
		// 処理を進める★trueに直す
		return false;
	}else{
		return false;
	}
}

// バリデーションチェック
// パスワードの一致確認
function checkPassword() {
	if(inputForm.stPassword.value != inputForm.stConfirmPassword.value) {
		alert("パスワードと確認用パスワードが一致しません");
		return false;
	} else {
		return true;
	}
}

// Menuからリンク押下時にナビゲーションドロワを閉じるためのMenuCheckOFF
function closeMenu(){
	if (document.getElementById("MenuCheck") != null){
		document.getElementById("MenuCheck").checked = false;
	}
}

//タブ選択によるコンテンツの表示制御
function changeDispContents() {

	var arrTabEle = document.getElementsByName("TabRadio")

	for (var i = 0 ; i < arrTabEle.length; i++) {
		if (arrTabEle[i].checked){
			// ラジオボタンが選択されていたら要素IDを取得
			var stTabID = arrTabEle[i].id
			// 連想配列に定義されたIDに紐づく表示切替cssを反映
			document.getElementById("DispStyle").href = defDispStyle[stTabID];
			break;
		}
	}

}

// 入力内容の量に応じたtextareaの自動サイズ変更(★)
function dynamicHeightChanger(id) {
	var textarea = document.getElementById(id);

	// 対象のtextareaがタイトル欄であればタイトル用lineHeightを設定
	if (id == "stTitle"){
		textarea.style.lineHeight = TITLE_LINE_HEIGHT;
	} else {
		textarea.style.lineHeight = NOMAL_LINE_HEIGHT;
	}

	textarea.addEventListener("input",function(evt){

		// 詳細欄の最小Heightを指定
		var detailsHeightMin = DETAILS_HEIGHT;

		//scrollHeight(入力内容全体)とoffsetHeight(要素の大きさ)とを比較
		if(evt.target.scrollHeight > evt.target.offsetHeight){
			// 入力内容の方が大きい場合は、textareaのheightをscrollHeightに合わせる
			evt.target.style.height = evt.target.scrollHeight + "px";

		}else{
			// scrollHeightの方がoffsetHeightよりも小さい、あるいは同じ場合にはheightを減らす
			var height,lineHeight;
			while (true){
				// textareaのheightを1ラインずつ減らしていく
				height = Number(evt.target.style.height.split("px")[0]);
				lineHeight = Number(evt.target.style.lineHeight.split("px")[0]);
				evt.target.style.height = height - lineHeight + "px";

				// scrollHeightがoffsetHeightよりも大きくなったところでtextareaの高さを決定
				if(evt.target.scrollHeight > evt.target.offsetHeight){

					// 対象のtextareaが詳細欄、かつscrollHeightが最小Height以下の場合、最小Heightを採用
					if (id == "stDetails"){
						if (evt.target.scrollHeight <= detailsHeightMin){
							evt.target.style.height = detailsHeightMin + "px";
							break;
						}
					}
					// scrollHeightが最小Heightより大きい、または対象のtextareaが詳細欄以外の場合
					// textareaの高さをscrollHeightに合わせる
					evt.target.style.height = evt.target.scrollHeight + "px";
					break;
				}
			}
		}
	});
}

// 表示画面→編集画面への遷移時に渡す用の、textareaの高さを取得
function getHeightTextare(arrTextareaId) {

	var arrHeight = [];

	// 指定された要素IDの数だけループ
	for(var i = 0; i < arrTextareaId.length; i++) {
		// 各textareaの高さを取得し、「要素ID=高さ」で配列に追加
		var targetId = arrTextareaId[i];
		var targetHeight = document.getElementById(targetId).clientHeight;
		if (targetId == "stDetails") {
			if (targetHeight < DETAILS_HEIGHT) {
				targetHeight = DETAILS_HEIGHT;
			}
		}
		if (targetHeight == 0) {
			targetHeight = 30;
		}
		arrHeight.push(targetId + "=" + targetHeight);
	}

    // input要素を生成
    var ele = document.createElement('input');
    ele.setAttribute('type', 'hidden');
    ele.setAttribute('name', 'arrHeight');
    ele.setAttribute('value', arrHeight);

    // 編集画面遷移用のformに上記input要素を追加
    document.formEdit.appendChild(ele);
}

//各要素の高さ・行の高さを初期設定
function setTextareaHeight(stHeightArray) {

	// 「要素ID=高さ」がカンマ区切りになった文字列を分割して配列へ
	var arrHeight = stHeightArray.split(",");

	for(var i = 0; i < arrHeight.length; i++) {
		// 「要素ID=高さ」を分割
		var arrIdAHeight = arrHeight[i].split("=");

		// 分割後の0番目：要素ID、1番目：高さを使用してtextareaのheightを設定
		var textarea = document.getElementById(arrIdAHeight[0]);
		textarea.style.height = arrIdAHeight[1] + "px";

	}
}