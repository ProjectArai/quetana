package model;

import java.util.ArrayList;
import java.util.List;

public class ViewTimeLine {

	public static List<TimeLineBean> getTimeLineList() {

		List<TimeLineBean> arrTimeLine = new ArrayList();

		TimeLineBean timeLine1 = new TimeLineBean("T000001E", "A001", "r-zoon", "/quetana/img/r-zoon.png", "OB/OGライブ 2018", "E", "", "", "池袋ロサ", "2018/02/09" ,"皆様、いかがお過ごしでしょうか。今年もOB/OGライブの季節がやってまいりました。");
		TimeLineBean timeLine2 = new TimeLineBean("T000002M", "A002", "kazzool", "/quetana/img/kazzool.png", "Beatles（コピー）のメンバーを探しています！", "M", "ベース,ドラム", "Rock", "", "" ,"ライブの日取りはまだ決まっていないのですが、the Beatlesのコピーバンドを組んでスタジオで合わせたいと思っています！！興味のある方はぜひ、私のアカウ");
		TimeLineBean timeLine3 = new TimeLineBean("T000003M", "A003", "gekiyasu", "/quetana/img/gekiyasu.png", "9mmをガチでコピーしたい", "M", "ドラム以外", "Rock", "", "" ,"貴方の厳しさ、見せてください。");

		arrTimeLine.add(timeLine1);
		arrTimeLine.add(timeLine2);
		arrTimeLine.add(timeLine3);

		return arrTimeLine;
	}
}
