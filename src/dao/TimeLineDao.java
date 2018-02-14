package dao;

import java.util.ArrayList;
import java.util.List;

import model.TimeLineBean;

public class TimeLineDao {

	public static List<TimeLineBean> getTimeLineInfo() {

		List<TimeLineBean> arrTimeLineInfo = new ArrayList<TimeLineBean>();

		//テストデータ
		TimeLineBean timeLine1 = new TimeLineBean("Z000001E", "Z001", "r-zoon", "OB/OGライブ 2018", "E");
		arrTimeLineInfo.add(timeLine1);

		TimeLineBean timeLine2 = new TimeLineBean("Z000002M", "Z002", "kazzool", "Beatles（コピー）のメンバーを探しています！", "M");
		arrTimeLineInfo.add(timeLine2);

		TimeLineBean timeLine3 = new TimeLineBean("Z000003E", "Z001", "r-zoon", "OB/OGライブ 2018", "E");
		arrTimeLineInfo.add(timeLine3);

		TimeLineBean timeLine4 = new TimeLineBean("Z000004M", "Z002", "kazzool", "Beatles（コピー）のメンバーを探しています！", "M");
		arrTimeLineInfo.add(timeLine4);

		return arrTimeLineInfo;
	}

}
