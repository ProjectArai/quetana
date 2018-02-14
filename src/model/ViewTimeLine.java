package model;

import java.util.ArrayList;
import java.util.List;

import dao.TimeLineDao;

public class ViewTimeLine {

	public static List getTimeLineList() {

		List arrTimeLine = new ArrayList();

		arrTimeLine = TimeLineDao.getTimeLineInfo();

		return arrTimeLine;
	}
}
