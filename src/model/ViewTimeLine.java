package model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import dao.GetTimeLineDAO;
import model.dto.TimeLineDto;


public class ViewTimeLine {

	public static List<TimeLineBean> getTimeLineList() {

		List<TimeLineDto> timeLines = new ArrayList();
		List<TimeLineBean> arrTimeLine = new ArrayList();

		GetTimeLineDAO getTimeLineDao = new GetTimeLineDAO();
		timeLines = getTimeLineDao.selectUnionTimeLine();

		for(TimeLineDto timeLine : timeLines) {
			TimeLineBean timeLineBean = new TimeLineBean();
			String idPost = timeLine.getIdPost();
			timeLineBean.setIdPost(idPost);
			timeLineBean.setIdUser(timeLine.getIdUser());
			timeLineBean.setStAccountName(timeLine.getStAccountName()); //使わないんじゃない？
			timeLineBean.setStDisplayName(timeLine.getStDisplayName());
			timeLineBean.setStIconURL(timeLine.getStIconURL());

			String cfPost = "";
			String stOutLine = "";
			if (idPost.charAt(1) == 'E') {
				cfPost = "E";
				stOutLine = new SimpleDateFormat("yyyy/MM/dd").format(timeLine.getDtEvent());
				stOutLine +=  "＠";
				//stPlaceが17文字以上かどうか判定、16文字以下だったらそのまま
				//17文字以上だったら15文字+「…」とする
				String stPlace = timeLine.getStPlace();
				if (stPlace.length() > 16) {
					stOutLine += stPlace.substring(0, 15);
					stOutLine += "…";
				} else {
					stOutLine += stPlace;
				}
			} else if (idPost.charAt(1) == 'M') {
				cfPost = "M";
				//いずれコードが入るので、コードに紐づくパート名を取得してカンマ区切りで結合する
				stOutLine = timeLine.getStPart();
			}
			timeLineBean.setCfPost(cfPost);
			timeLineBean.setStOutLine(stOutLine);

			timeLineBean.setStTitle(timeLine.getStTitle());
			timeLineBean.setStPart(timeLine.getStPart());
			timeLineBean.setStGenre(timeLine.getStGenre());
			timeLineBean.setStPlace(timeLine.getStPlace());
			timeLineBean.setDtEvent(timeLine.getDtEvent());

			String stDetails = timeLine.getStDetails();
			timeLineBean.setStDetails(stDetails);

			String stDetailsOmit = "";
			if (stDetails.length() > 64) {
				stDetailsOmit = stDetails.substring(0, 64);
			} else {
				stDetailsOmit = stDetails;
			}
			timeLineBean.setStDetailsOmit(stDetailsOmit);

			String dtUpdateDT = new SimpleDateFormat("yyyy/MM/dd HH:mm").format(timeLine.getDtUpdate());
			timeLineBean.setDtUpdateDT(dtUpdateDT);

			timeLineBean.setDtUpdate(timeLine.getDtUpdate());
			timeLineBean.setDtResist(timeLine.getDtResist());
			arrTimeLine.add(timeLineBean);
		}

		return arrTimeLine;
	}
}
