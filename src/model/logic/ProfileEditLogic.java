package model.logic;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Part;

import model.bean.LoginUserInfoBean;
import model.bean.UserProfileBean;
import model.dao.T_USER_PROFILE_DAO;
import model.dto.UserProfileDto;

public class ProfileEditLogic {

	/**
	 * プロフィール編集時に初期表示する値を生成
	 * @param idUser
	 * @return rtnMap(errMsg,userProfile)
	 */
	public static Map getUserProfile(String idUser) {

		Map rtnMap = new HashMap();
		String errMsg;
		UserProfileBean userProfile = new UserProfileBean();

		UserProfileDto userProfileDto = new UserProfileDto();
		userProfileDto.setIdUser(idUser);

		//ユーザIDからユーザプロフィールを取得
		List<UserProfileDto> arrUserProfile = new ArrayList();
		T_USER_PROFILE_DAO tUserProfile = new T_USER_PROFILE_DAO();
		arrUserProfile = tUserProfile.selectUserProfile(userProfileDto);

		if (arrUserProfile.size() == 1) {
			// DBから取得したユーザプロフィールが1件の場合
			userProfileDto = arrUserProfile.get(0);
			// ユーザプロフィールをセット
			userProfile.setIdUser(userProfileDto.getIdUser());
			userProfile.setStAccountName(userProfileDto.getStAccountName());
			userProfile.setStDisplayName(userProfileDto.getStDisplayName());
			if (userProfileDto.getNmAge() == null) {
				userProfile.setNmAge("");
			} else {
				userProfile.setNmAge(userProfileDto.getNmAge());
			}
			if (userProfileDto.getNmAddYear() == null) {
				userProfile.setNmAddYear("");
			} else {
				userProfile.setNmAddYear(userProfileDto.getNmAddYear());
			}
			// パートはコードに対するcheckを指定して渡す、nullの時は全て未選択で渡す
			Map<String, String> mapPartChk= CommonLogic.getPartCheck();
			if (userProfileDto.getStPart() != null) {
				String[] arrPart = userProfileDto.getStPart().split(",", 0);
				for (int i = 0; i < arrPart.length; i++) {
					mapPartChk.put(arrPart[i], "checked");
				}
			}
			userProfile.setChkPart(mapPartChk);
			if (userProfileDto.getStFBand() == null) {
				userProfile.setStFBand("");
			} else {
				userProfile.setStFBand(userProfileDto.getStFBand());
			}
			if (userProfileDto.getStFGenre() == null) {
				userProfile.setStFGenre("");
			} else {
				userProfile.setStFGenre(userProfileDto.getStFGenre());
			}
			userProfile.setStIconURL(userProfileDto.getStIconURL());
			if (userProfileDto.getStVideoURL() == null) {
				userProfile.setStVideoURL("");
			} else {
				userProfile.setStVideoURL(userProfileDto.getStVideoURL());
			}
			if (userProfileDto.getStComment() == null) {
				userProfile.setStComment("");
			} else {
				userProfile.setStComment(userProfileDto.getStComment());
			}
			errMsg = "";
		} else {
			// DBから取得したユーザプロフィールが1件以外の場合
			errMsg = "システムエラー 管理者に連絡して下さい";
		}

		rtnMap.put("errMsg", errMsg);
		rtnMap.put("userProfile", userProfile);
		return rtnMap;
	}

	/**
	 * プロフィール更新処理の実行
	 * @param inParam
	 * @return rtnMap(cfIconUpdate,errMsg)
	 * @throws IOException
	 */
	public static Map editUserProfile(Map inParam) throws IOException {

		Map rtnMap = new HashMap();
		Boolean cfIconUpdate = false;
		String errMsg;

		// inParamをDTOに詰め直し(必須項目以外入力がなければnullを設定)
		UserProfileDto dtoUP = new UserProfileDto();
		dtoUP.setIdUser((String)inParam.get("idUser"));
		dtoUP.setStDisplayName((String)inParam.get("stDisplayName"));
		if (((String)inParam.get("nmAge")).equals("")) {
			dtoUP.setNmAge(null);
		} else {
			dtoUP.setNmAge((String)inParam.get("nmAge"));
		}
		if (((String)inParam.get("nmAddYear")).equals("")) {
			dtoUP.setNmAddYear(null);
		} else {
			dtoUP.setNmAddYear((String)inParam.get("nmAddYear"));
		}
		if (((String)inParam.get("stPart")).equals("")) {
			dtoUP.setStPart(null);
		} else {
			dtoUP.setStPart((String)inParam.get("stPart"));
		}
		if (((String)inParam.get("stBand")).equals("")) {
			dtoUP.setStFBand(null);
		} else {
			dtoUP.setStFBand((String)inParam.get("stBand"));
		}
		if (((String)inParam.get("stGenre")).equals("")) {
			dtoUP.setStFGenre(null);
		} else {
			dtoUP.setStFGenre((String)inParam.get("stGenre"));
		}
		if (((String)inParam.get("stVideoURL")).equals("")) {
			dtoUP.setStVideoURL(null);
		} else {
			dtoUP.setStVideoURL((String)inParam.get("stVideoURL"));
		}
		if (((String)inParam.get("stIntroduction")).equals("")) {
			dtoUP.setStComment(null);
		} else {
			dtoUP.setStComment((String)inParam.get("stIntroduction"));
		}

		// アイコンのアップロード有無の判定
		Part partImgIcon = (Part)inParam.get("imgIcon");
		if (partImgIcon.getSize() != 0) {
			// アップロードされている場合
			// アイコンをユーザID＋拡張子で保存
			String stIconFileName = "/" + (String)inParam.get("idUser") + ".png";
			partImgIcon.write((String)inParam.get("stImgSavePath") + stIconFileName);
			// Dtoに新規アイコンパスをセット
			dtoUP.setStIconURL("/quetana/img" + stIconFileName);
			// IconUpdateFLGをオン
			cfIconUpdate = true;

			LoginUserInfoBean loginUserInfo = (LoginUserInfoBean)inParam.get("loginUserInfo");
			loginUserInfo.setStIconURL("/quetana/img" + stIconFileName);
		} else {
			// アップロードされていない場合、Dtoに元々のアイコンパスをセット
			dtoUP.setStIconURL((String)inParam.get("stIconURL_org"));
		}

		// UPDATE処理の実行
		T_USER_PROFILE_DAO daoUP = new T_USER_PROFILE_DAO();
		int rowExec = daoUP.updateUserProfile(dtoUP);

		// UPDATE実行結果の判定
		if (rowExec == 1) {
			// 更新件数が1件の場合(成功)
			errMsg = "";

		} else {
			// 更新件数が1件以外の場合(失敗)

			// 入力内容を保持するための情報を設定
			UserProfileBean userProfile = new UserProfileBean();
			userProfile.setIdUser((String)inParam.get("idUser"));
			userProfile.setStDisplayName((String)inParam.get("stDisplayName"));
			userProfile.setNmAge((String)inParam.get("nmAge"));
			userProfile.setNmAddYear((String)inParam.get("nmAddYear"));
			// パートはコードに対するcheckを指定して渡す
			Map<String, String> mapPartChk= CommonLogic.getPartCheck();
			String[] arrPart = ((String)inParam.get("stPart")).split(",", 0);
			for (int i = 0; i < arrPart.length; i++) {
				mapPartChk.put(arrPart[i], "checked");
			}
			userProfile.setChkPart(mapPartChk);
			userProfile.setStFBand((String)inParam.get("stBand"));
			userProfile.setStFGenre((String)inParam.get("stGenre"));
			userProfile.setStIconURL((String)inParam.get("stIconURL_org"));
			userProfile.setStVideoURL((String)inParam.get("stVideoURL"));
			userProfile.setStComment((String)inParam.get("stIntroduction"));
			// userProfileの設定は失敗時のみ
			rtnMap.put("userProfile", userProfile);

			// エラーメッセージを設定★メッセージ内容
			errMsg = "システムエラー 管理者に連絡して下さい";
		}

		rtnMap.put("cfIconUpdate", cfIconUpdate);
		rtnMap.put("errMsg", errMsg);
		return rtnMap;
	}

	/**
	 * アイコン更新時のSession上のユーザ情報更新用★このクラスで持つべきか微妙
	 * @param idUser
	 * @return rtnMap(errMsg,loginUserInfo)
	 */
	public static Map updateLoginUserInfo(String idUser) {

		Map rtnMap = new HashMap();
		String errMsg;
		LoginUserInfoBean loginUserInfo = new LoginUserInfoBean();

		//ユーザIDを基にユーザプロフィールを取得
		UserProfileDto userProfileDto = new UserProfileDto();
		userProfileDto.setIdUser(idUser);
		T_USER_PROFILE_DAO tUserProfile = new T_USER_PROFILE_DAO();
		List<UserProfileDto> arrUserProfile = tUserProfile.selectUserProfile(userProfileDto);

		if (arrUserProfile.size() == 1) {
			// DBから取得したユーザプロフィールが1件の場合
			userProfileDto = arrUserProfile.get(0);
			// ログインユーザ情報をセット
			loginUserInfo.setIdUser(userProfileDto.getIdUser());
			loginUserInfo.setStAccountName(userProfileDto.getStAccountName());
			loginUserInfo.setStIconURL(userProfileDto.getStIconURL());
			errMsg = "";
		} else {
			// DBから取得したユーザプロフィールが1件以外の場合
			errMsg = "システムエラー 管理者に連絡して下さい";
		}
		rtnMap.put("errMsg", errMsg);
		rtnMap.put("loginUserInfo", loginUserInfo);
		return rtnMap;
	}

}
