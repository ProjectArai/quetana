package model.logic;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.bean.PostViewBean;
import model.dao.T_EVENT_ANNOUNCE_DAO;
import model.dao.T_MEMBER_RECRUIT_DAO;
import model.dto.TimeLineDto;

public class PostViewLogic {

	public static Map getPostInfo(String idPost) {

		Map rtnMap = new HashMap();
		String errMsg = "";

		PostViewBean beanPV = new PostViewBean();
		List<TimeLineDto> arrPostInfo = new ArrayList();

		String cfPostType = idPost.substring(1, 2);

		if (cfPostType.equals("E")) {
			T_EVENT_ANNOUNCE_DAO daoEA = new T_EVENT_ANNOUNCE_DAO();
			arrPostInfo = daoEA.selectEventAnnounce(idPost);
		} else if (cfPostType.equals("M")) {
			T_MEMBER_RECRUIT_DAO daoMR = new T_MEMBER_RECRUIT_DAO();
			arrPostInfo = daoMR.selectMemberRecruit(idPost);
		} else {
			// 投稿区分がEでもMでもないとかいう事態が起きたら即システムエラー
			errMsg = "投稿IDが正しくありません";
			rtnMap.put("errMsg", errMsg);
			rtnMap.put("beanPV", beanPV);
			return rtnMap;
		}

		if (arrPostInfo.size() == 1) {
			// DBから取得したPost情報が1件の場合
			TimeLineDto dtoTL = new TimeLineDto();
			dtoTL = arrPostInfo.get(0);
			// ユーザプロフィールをセット
			beanPV.setIdPost(dtoTL.getIdPost());
			beanPV.setIdUser(dtoTL.getIdUser());
			beanPV.setStAccountName(dtoTL.getStAccountName());
			beanPV.setStDisplayName(dtoTL.getStDisplayName());
			beanPV.setStIconURL(dtoTL.getStIconURL());
			beanPV.setCfPostType(dtoTL.getIdPost().substring(1, 2));
			beanPV.setStTitle(dtoTL.getStTitle());

			if (cfPostType.equals("E")) {
				beanPV.setDtEvent(new SimpleDateFormat("yyyy/MM/dd").format(dtoTL.getDtEvent()));
				beanPV.setStPlace(dtoTL.getStPlace());
			} else if (cfPostType.equals("M")) {
				// パートはコードを名前に変換して渡す
				Map<String, String> mapPart = CommonLogic.getStPartName();
				String stPart = "";
				String stPartName = "";
				String[] arrPart = dtoTL.getStPart().split(",", 0);
				for (int i = 0; i < arrPart.length; i++) {
					stPartName = mapPart.get(arrPart[i]);
					stPart += stPartName;
					// 最後以外は半角スペース区切り
					if (i < (arrPart.length - 1)) {
						stPart += " ";
					}
				}
				beanPV.setStPart(stPart);
				beanPV.setStGenre(dtoTL.getStGenre());
			}
			beanPV.setStDetails(dtoTL.getStDetails());
			beanPV.setDtUpdate(new SimpleDateFormat("yyyy/MM/dd HH:mm").format(dtoTL.getDtUpdate()));
			errMsg = "";
		} else {
			// DBから取得したユーザプロフィールが1件以外の場合
			errMsg = "Database Error";
		}

		rtnMap.put("errMsg", errMsg);
		rtnMap.put("beanPV", beanPV);
		return rtnMap;
	}
}
