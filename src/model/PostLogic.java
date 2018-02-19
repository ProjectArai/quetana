package model;

import java.util.HashMap;
import java.util.Map;

import dao.M_SEQ_NO_DAO;
import dao.T_MEMBER_RECRUIT_DAO;
import dao.T_TIMELINE_DAO;
import model.dto.EventAnnounceDto;
import model.dto.MemberRecruitDto;
import model.dto.SeqNoDto;
import model.dto.TimeLineDto;
import model.dto.UserProfileDto;

public class PostLogic {

	public static Map postTimeLine(Map inParam) {

		Map rtnMap = new HashMap();
		String errMsg = "";

		// 新規投稿IDを取得
		String cfPost = (String)inParam.get("cfPost");
		String idPost = generateIdPost(cfPost);

		// T_TIMELINEへのInsert
		UserProfileDto userProfileDto = new UserProfileDto();
		TimeLineDto timeLineDto = new TimeLineDto();
		timeLineDto.setIdPost(idPost);
		timeLineDto.setCfPost(cfPost);

		T_TIMELINE_DAO tTimeLineDao = new T_TIMELINE_DAO();
		String resultExecute = tTimeLineDao.insertTimeLine(timeLineDto);

		// 投稿区分によって分岐
		if (cfPost.equals("M")) {
			//メンバー募集の場合、T_MEMBER_RECRUITへInsert
			MemberRecruitDto memberRecruitDto = new MemberRecruitDto();
			memberRecruitDto.setIdPost(idPost);
			memberRecruitDto.setIdUser((String)inParam.get("idUser"));
			memberRecruitDto.setStTitle((String)inParam.get("stTitle"));
			memberRecruitDto.setStPart((String)inParam.get("stPart"));
			memberRecruitDto.setStGenre((String)inParam.get("stGenre"));
			memberRecruitDto.setStDetails((String)inParam.get("stDetails"));

			T_MEMBER_RECRUIT_DAO tMemberRecruit = new T_MEMBER_RECRUIT_DAO();
			resultExecute = tMemberRecruit.insertMemberRecruit(memberRecruitDto);

		} else if (cfPost.equals("E")) {
			//イベント告知の場合、T_EVENT_ANNOUNCEへInsert
			EventAnnounceDto eventAnnounceDto = new EventAnnounceDto();
		}


		rtnMap.put("errMsg", errMsg);
		return rtnMap;
	}

	/**
	 * 新規投稿IDの発行、SEQNOのインクリメント
	 * @param cfPost
	 * @return idPost
	 */
	private static String generateIdPost (String cfPost) {

		String idPost;

		SeqNoDto seqNoDto = new SeqNoDto();
		seqNoDto.setIdType("T");

		// M_SEQ_NOから投稿IDのSEQ番号を取得
		M_SEQ_NO_DAO mSeqNoDao = new M_SEQ_NO_DAO();
		String stSeqNo = mSeqNoDao.selectTimeLineSEQ(seqNoDto);

		// 現SEQ番号をインクリメントしてゼロ埋め
		int seqNo = Integer.parseInt(stSeqNo);
		seqNo = seqNo + 1;

		//インクリメントしたSEQ番号にUPDATE
		seqNoDto.seqNoSeq(seqNo);
		String stResult = mSeqNoDao.updateTimeLineSEQ(seqNoDto);

		//ゼロ埋め処理
		stSeqNo = String.format("%06d", seqNo);

		//プレフィックスと投稿区分を付与して返却
		idPost = "T" +  stSeqNo + cfPost;
		return idPost;
	}

}
