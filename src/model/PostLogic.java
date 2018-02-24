package model;

import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

import dao.T_EVENT_ANNOUNCE_DAO;
import dao.T_MEMBER_RECRUIT_DAO;
import model.dto.EventAnnounceDto;
import model.dto.MemberRecruitDto;

public class PostLogic {

	public static Map postTimeLine(Map inParam) {

		Map rtnMap = new HashMap();
		String errMsg = "";
		int resultExecute = 0;

		// inParamから投稿区分を取得、以降投稿区分で処理分岐
		String cfPost = (String)inParam.get("cfPost");

		if (cfPost.equals("M")) {
			// メンバー募集の場合
			// SEQ番号の取得
			String idPost = CommonLogic.generateSeqNo("TM");
			if (idPost.equals("")) {
				// SEQ番号のインクリメントに失敗したら即エラー
				errMsg = "アカウント作成に失敗しました";
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

		} else if (cfPost.equals("E")) {
			// イベント告知の場合
			// SEQ番号の取得
			String idPost = CommonLogic.generateSeqNo("TE");
			if (idPost.equals("")) {
				// SEQ番号のインクリメントに失敗したら即エラー
				errMsg = "アカウント作成に失敗しました";
				rtnMap.put("errMsg", errMsg);
				return rtnMap;
			}

			// T_EVENT_ANNOUNCEへInsert
			Date dtEvent = Date.valueOf((String)inParam.get("dtEvent")); //inParamの日付をSQLDate値へ変換

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
			// 更新件数が1件以外の場合(失敗)
			errMsg = "システムエラー 管理者に連絡して下さい";
		}

		rtnMap.put("errMsg", errMsg);
		return rtnMap;
	}
}
