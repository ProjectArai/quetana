package model;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dao.T_EVENT_ANNOUNCE_DAO;
import dao.T_MEMBER_RECRUIT_DAO;
import model.bean.PostViewBean;
import model.dto.EventAnnounceDto;
import model.dto.MemberRecruitDto;
import model.dto.TimeLineDto;

public class PostSendLogic {

	/**
	 * 新規投稿用(基本空)のbean作成
	 * @return PostViewBean
	 */
	public static PostViewBean setNewPostParam() {

		PostViewBean beanPV = new PostViewBean();

		beanPV.setCfPostType("N"); // 新規投稿の場合は投稿モードはN(New：新規)
		beanPV.setStTitle("");
		Date date = new Date(System.currentTimeMillis());
		beanPV.setDtEvent(new SimpleDateFormat("yyyy-MM-dd").format(date)); // 新規投稿の場合は当日日付を設定
		beanPV.setStPlace("");
		beanPV.setChkPart(CommonLogic.getPartCheck());
		beanPV.setStPart("");
		beanPV.setStGenre("");
		beanPV.setStDetails("");

		return beanPV;
	}

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
				beanPV.setDtEvent(new SimpleDateFormat("yyyy-MM-dd").format(dtoTL.getDtEvent()));
				beanPV.setStPlace(dtoTL.getStPlace());
			} else if (cfPostType.equals("M")) {
				// パートはコードを名前に変換して渡す
				Map<String, String> mapPartChk= CommonLogic.getPartCheck();
				String[] arrPart = dtoTL.getStPart().split(",", 0);
				for (int i = 0; i < arrPart.length; i++) {
					mapPartChk.put(arrPart[i], "checked");
				}
				beanPV.setChkPart(mapPartChk);
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

	public static Map insertPostInfo(Map inParam) {

		Map rtnMap = new HashMap();
		String errMsg = "";
		int resultExecute = 0;

		// inParamから投稿区分を取得、以降投稿区分で処理分岐
		String cfPostType = (String)inParam.get("cfPostType");

		if (cfPostType.equals("M")) {
			// メンバー募集の場合
			// SEQ番号の取得
			String idPost = CommonLogic.generateSeqNo("TM");
			if (idPost.equals("")) {
				// SEQ番号のインクリメントに失敗したら即エラー★Msg
				errMsg = "SystemLogic or Database Error";
				rtnMap.put("errMsg", errMsg);
				return rtnMap;
			}

			// T_MEMBER_RECRUITへInsert
			MemberRecruitDto memberRecruitDto = new MemberRecruitDto();
			memberRecruitDto.setIdPost(idPost);
			memberRecruitDto.setIdUser((String)inParam.get("idUser"));
			memberRecruitDto.setStTitle((String)inParam.get("stTitle"));
			memberRecruitDto.setStPart((String)inParam.get("stPart"));
			memberRecruitDto.setStGenre((String)inParam.get("stGenre"));
			memberRecruitDto.setStDetails((String)inParam.get("stDetails"));
			memberRecruitDto.setCfDelete("0");

			T_MEMBER_RECRUIT_DAO tMemberRecruit = new T_MEMBER_RECRUIT_DAO();
			resultExecute = tMemberRecruit.insertMemberRecruit(memberRecruitDto);

		} else if (cfPostType.equals("E")) {
			// イベント告知の場合
			// SEQ番号の取得
			String idPost = CommonLogic.generateSeqNo("TE");
			if (idPost.equals("")) {
				// SEQ番号のインクリメントに失敗したら即エラー★Msg
				errMsg = "SystemLogic or Database Error";
				rtnMap.put("errMsg", errMsg);
				return rtnMap;
			}

			//inParamの日付をSQLDate値へ変換
			Date dtEvent = Date.valueOf((String)inParam.get("dtEvent"));
			// T_EVENT_ANNOUNCEへInsert
			EventAnnounceDto eventAnnounceDto = new EventAnnounceDto();
			eventAnnounceDto.setIdPost(idPost);
			eventAnnounceDto.setIdUser((String)inParam.get("idUser"));
			eventAnnounceDto.setStTitle((String)inParam.get("stTitle"));
			eventAnnounceDto.setStPlace((String)inParam.get("stPlace"));
			eventAnnounceDto.setDtEvent(dtEvent);
			eventAnnounceDto.setStDetails((String)inParam.get("stDetails"));
			eventAnnounceDto.setCfDelete("0");

			T_EVENT_ANNOUNCE_DAO tEventAnnounce = new T_EVENT_ANNOUNCE_DAO();
			resultExecute = tEventAnnounce.insertEventAnnounce(eventAnnounceDto);
		}

		// INSERT実行結果の判定
		if (resultExecute == 1 ) {
			// 更新件数が1件の場合(成功)
			errMsg = "";
		} else {
			// 更新件数が1件以外の場合(失敗)★Msg
			errMsg = "Database Error";
		}

		rtnMap.put("errMsg", errMsg);
		return rtnMap;
	}
}
