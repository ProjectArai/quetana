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

		// 投稿モードはN(=New：新規)を設定
		beanPV.setCfPostType("N");
		beanPV.setStTitle("");
		// 開催日は当日日付を設定
		Date date = new Date(System.currentTimeMillis());
		beanPV.setDtEvent(new SimpleDateFormat("yyyy-MM-dd").format(date));
		beanPV.setStPlace("");
		beanPV.setChkPart(CommonLogic.getPartCheck());
		beanPV.setStPart("");
		beanPV.setStGenre("");
		beanPV.setStDetails("");

		return beanPV;
	}

	/**
	 * 投稿内容修正時に初期表示する値を生成
	 * @param idPost
	 * @return rtnMap（errMsg：必須、beanPV：必須？）
	 */
	public static Map getPostInfo(String idPost) {

		Map rtnMap = new HashMap();
		String errMsg = "";

		PostViewBean beanPV = new PostViewBean();
		List<TimeLineDto> arrPostInfo = new ArrayList();

		// 投稿IDから投稿種別を抽出
		String cfPostType = idPost.substring(1, 2);

		if (cfPostType.equals("E")) {
			// 投稿種別がイベント告知の場合、T_EVENT_ANNOUNCEからSelect
			T_EVENT_ANNOUNCE_DAO daoEA = new T_EVENT_ANNOUNCE_DAO();
			arrPostInfo = daoEA.selectEventAnnounce(idPost);
		} else if (cfPostType.equals("M")) {
			// 投稿種別がメンバー募集の場合、T_MEMBER_RECRUITからSelect
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
			// 投稿内容をセット
			beanPV.setIdPost(dtoTL.getIdPost());
			beanPV.setCfPostType(dtoTL.getIdPost().substring(1, 2));
			beanPV.setStTitle(dtoTL.getStTitle());
			if (cfPostType.equals("E")) {
				// 投稿種別がイベント告知の場合
				beanPV.setDtEvent(new SimpleDateFormat("yyyy-MM-dd").format(dtoTL.getDtEvent()));
				beanPV.setStPlace(dtoTL.getStPlace());
				// パートはコードに対するcheckなしのMapを渡す
				Map<String, String> mapPartChk= CommonLogic.getPartCheck();
				beanPV.setChkPart(mapPartChk);
				beanPV.setStGenre("");
			} else if (cfPostType.equals("M")) {
				// 投稿種別がメンバー募集の場合
				// 開催日には当日日付を設定しておく
				Date date = new Date(System.currentTimeMillis());
				beanPV.setDtEvent(new SimpleDateFormat("yyyy-MM-dd").format(date));
				beanPV.setStPlace("");
				// パートはコードに対するcheckを指定して渡す
				Map<String, String> mapPartChk= CommonLogic.getPartCheck();
				String[] arrPart = dtoTL.getStPart().split(",", 0);
				for (int i = 0; i < arrPart.length; i++) {
					mapPartChk.put(arrPart[i], "checked");
				}
				beanPV.setChkPart(mapPartChk);
				beanPV.setStGenre(dtoTL.getStGenre());
			}
			beanPV.setStDetails(dtoTL.getStDetails());
			errMsg = "";
		} else {
			// DBから取得したユーザプロフィールが1件以外の場合
			errMsg = "Database Error";
		}

		rtnMap.put("errMsg", errMsg);
		rtnMap.put("beanPV", beanPV);
		return rtnMap;
	}

	/**
	 * 新規投稿内容のINSERTを実行する
	 * @param inParam
	 * @return rtnMap（errMsg：必須、beanPV：Insert失敗時のみ）
	 */
	public static Map insertPostInfo(Map inParam) {

		Map rtnMap = new HashMap();
		String errMsg = "";
		int rowExec = 0;

		// inParamから投稿種別を取得、以降投稿種別で処理分岐
		String cfPostType = (String)inParam.get("cfPostType");

		if (cfPostType.equals("E")) {
			// 投稿種別がイベント告知の場合
			// SEQ番号の取得
			String idPost = CommonLogic.generateSeqNo("TE");
			if (idPost.equals("")) {
				// SEQ番号のインクリメントに失敗したら即エラー★Msg
				errMsg = "SystemLogic or Database Error";
				rtnMap.put("errMsg", errMsg);
				return rtnMap;
			}
			// INSERT準備
			EventAnnounceDto dtoEA = new EventAnnounceDto();
			dtoEA.setIdPost(idPost);
			dtoEA.setIdUser((String)inParam.get("idUser"));
			dtoEA.setStTitle((String)inParam.get("stTitle"));
			dtoEA.setStPlace((String)inParam.get("stPlace"));
			Date dtEvent = Date.valueOf((String)inParam.get("dtEvent"));// inParamの日付をSQLDate値へ変換
			dtoEA.setDtEvent(dtEvent);
			dtoEA.setStDetails((String)inParam.get("stDetails"));
			dtoEA.setCfDelete("0");
			// T_EVENT_ANNOUNCEへINSERT実行
			T_EVENT_ANNOUNCE_DAO daoEA = new T_EVENT_ANNOUNCE_DAO();
			rowExec = daoEA.insertEventAnnounce(dtoEA);

		} else if (cfPostType.equals("M")) {
			// 投稿種別がメンバー募集の場合
			// SEQ番号の取得
			String idPost = CommonLogic.generateSeqNo("TM");
			if (idPost.equals("")) {
				// SEQ番号のインクリメントに失敗したら即エラー★Msg
				errMsg = "SystemLogic or Database Error";
				rtnMap.put("errMsg", errMsg);
				return rtnMap;
			}
			// INSERT準備
			MemberRecruitDto dtoMR = new MemberRecruitDto();
			dtoMR.setIdPost(idPost);
			dtoMR.setIdUser((String)inParam.get("idUser"));
			dtoMR.setStTitle((String)inParam.get("stTitle"));
			dtoMR.setStPart((String)inParam.get("stPart"));
			dtoMR.setStGenre((String)inParam.get("stGenre"));
			dtoMR.setStDetails((String)inParam.get("stDetails"));
			dtoMR.setCfDelete("0");
			// T_MEMBER_RECRUITへINSERT実行
			T_MEMBER_RECRUIT_DAO daoMR = new T_MEMBER_RECRUIT_DAO();
			rowExec = daoMR.insertMemberRecruit(dtoMR);

		}

		// INSERT実行結果の判定
		if (rowExec == 1 ) {
			// 更新件数が1件の場合(成功)
			errMsg = "";

		} else {
			// 更新件数が1件以外の場合(失敗)

			// 入力内容を保持するための情報を設定
			PostViewBean beanPV = new PostViewBean();
			beanPV.setStTitle((String)inParam.get("stTitle"));
			if (cfPostType.equals("E")) {
				// 投稿種別がイベント告知の場合
				// パートはコードに対するcheckなしのMapを渡す
				Map<String, String> mapPartChk= CommonLogic.getPartCheck();
				beanPV.setChkPart(mapPartChk);
				beanPV.setStGenre("");
				beanPV.setDtEvent((String)inParam.get("dtEvent"));
				beanPV.setStPlace((String)inParam.get("stPlace"));

			} else if (cfPostType.equals("M")) {
				// 投稿種別がメンバー募集の場合
				// 開催日には当日日付を設定しておく
				Date date = new Date(System.currentTimeMillis());
				beanPV.setDtEvent(new SimpleDateFormat("yyyy-MM-dd").format(date));
				beanPV.setStPlace("");
				// パートはコードに対するcheckを指定して渡す
				Map<String, String> mapPartChk= CommonLogic.getPartCheck();
				String[] arrPart = ((String)inParam.get("stPart")).split(",", 0);
				for (int i = 0; i < arrPart.length; i++) {
					mapPartChk.put(arrPart[i], "checked");
				}
				beanPV.setChkPart(mapPartChk);
				beanPV.setStGenre((String)inParam.get("stGenre"));

			}
			beanPV.setStDetails((String)inParam.get("stDetails"));
			beanPV.setCfPostType(cfPostType);
			// beanPVの設定は失敗時のみ
			rtnMap.put("beanPV", beanPV);

			// エラーメッセージを設定★メッセージ内容
			errMsg = "Database Error";

		}
		rtnMap.put("errMsg", errMsg);
		return rtnMap;
	}

	/**
	 * 投稿内容修正のUPDATEを実行する
	 * @param inParam
	 * @return rtnMap（errMsg：必須、beanPV：Insert失敗時のみ）
	 */
	public static Map updatePostInfo(Map inParam) {

		Map rtnMap = new HashMap();
		String errMsg = "";
		int rowExec = 0;

		// inParamから投稿種別を取得、以降投稿種別で処理分岐
		String cfPostType = (String)inParam.get("cfPostType");

		if (cfPostType.equals("E")) {
			// 投稿種別がイベント告知の場合
			// UPDATE準備
			EventAnnounceDto dtoEA = new EventAnnounceDto();
			dtoEA.setIdPost((String)inParam.get("idPost"));
			dtoEA.setIdUser((String)inParam.get("idUser"));
			dtoEA.setStTitle((String)inParam.get("stTitle"));
			dtoEA.setStPlace((String)inParam.get("stPlace"));
			Date dtEvent = Date.valueOf((String)inParam.get("dtEvent"));// inParamの日付をSQLDate値へ変換
			dtoEA.setDtEvent(dtEvent);
			dtoEA.setStDetails((String)inParam.get("stDetails"));
			// T_EVENT_ANNOUNCEへUPDATE実行
			T_EVENT_ANNOUNCE_DAO daoEA = new T_EVENT_ANNOUNCE_DAO();
			rowExec = daoEA.updateEventAnnounce(dtoEA);

		} else if (cfPostType.equals("M")) {
			// 投稿種別がメンバー募集の場合
			// UPDATE準備
			MemberRecruitDto dtoMR = new MemberRecruitDto();
			dtoMR.setIdPost((String)inParam.get("idPost"));
			dtoMR.setIdUser((String)inParam.get("idUser"));
			dtoMR.setStTitle((String)inParam.get("stTitle"));
			dtoMR.setStPart((String)inParam.get("stPart"));
			dtoMR.setStGenre((String)inParam.get("stGenre"));
			dtoMR.setStDetails((String)inParam.get("stDetails"));
			// T_MEMBER_RECRUITへUPDATE実行
			T_MEMBER_RECRUIT_DAO daoMR = new T_MEMBER_RECRUIT_DAO();
			rowExec = daoMR.updateMemberRecruit(dtoMR);

		}

		// UPDATE実行結果の判定
		if (rowExec == 1 ) {
			// 更新件数が1件の場合(成功)
			errMsg = "";

		} else {
			// 更新件数が1件以外の場合(失敗)

			// 入力内容を保持するための情報を設定
			PostViewBean beanPV = new PostViewBean();
			beanPV.setIdPost((String)inParam.get("idPost"));
			beanPV.setStTitle((String)inParam.get("stTitle"));
			if (cfPostType.equals("E")) {
				// 投稿種別がイベント告知の場合
				// パートはコードに対するcheckなしのMapを渡す
				Map<String, String> mapPartChk= CommonLogic.getPartCheck();
				beanPV.setChkPart(mapPartChk);
				beanPV.setStGenre("");
				beanPV.setDtEvent((String)inParam.get("dtEvent"));
				beanPV.setStPlace((String)inParam.get("stPlace"));
			} else if (cfPostType.equals("M")) {
				// 投稿種別がメンバー募集の場合
				// 開催日には当日日付を設定しておく
				Date date = new Date(System.currentTimeMillis());
				beanPV.setDtEvent(new SimpleDateFormat("yyyy-MM-dd").format(date));
				beanPV.setStPlace("");
				// パートはコードに対するcheckを指定して渡す
				Map<String, String> mapPartChk= CommonLogic.getPartCheck();
				String[] arrPart = ((String)inParam.get("stPart")).split(",", 0);
				for (int i = 0; i < arrPart.length; i++) {
					mapPartChk.put(arrPart[i], "checked");
				}
				beanPV.setChkPart(mapPartChk);
				beanPV.setStGenre((String)inParam.get("stGenre"));
			}
			beanPV.setStDetails((String)inParam.get("stDetails"));
			beanPV.setCfPostType(cfPostType);
			// beanPVの設定は失敗時のみ
			rtnMap.put("beanPV", beanPV);

			// エラーメッセージを設定★メッセージ内容
			errMsg = "Database Error";
		}
		rtnMap.put("errMsg", errMsg);
		return rtnMap;
	}
}
