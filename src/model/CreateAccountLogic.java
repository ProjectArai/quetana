package model;

import java.util.HashMap;
import java.util.Map;

import dao.T_USER_INFO_DAO;
import dao.T_USER_PROFILE_DAO;
import model.dto.UserProfileDto;


public class CreateAccountLogic {
	public static String createAccount(Map inParam) {

		int rowsInsert;

		String stAccountName = (String)inParam.get("stAccountName");
		String stMailAddress = (String)inParam.get("stMailAddress");
		String stPassword = (String)inParam.get("stPassword");

		// T_USER_INFO用のDTO作成
		UserInfoDto userInfoDto = new UserInfoDto();
		userInfoDto.setStAccountName(stAccountName);
		userInfoDto.setStMailAddress(stMailAddress);

		// T_USER_PROFILE用のDTO作成
		UserProfileDto userProfileDto = new UserProfileDto();
		userProfileDto.setStAccountName(stAccountName);

		// 重複チェック
		Map chkResult = chkDuplicateUser(userInfoDto, userProfileDto);
		int cdErr = (int)chkResult.get("cdErr");
		String stDup = (String)chkResult.get("stDup");
		if(cdErr == 1) {
			return "既に登録されている" + stDup + "です";
		} else if(cdErr == 2) {
			return "アカウント作成に失敗しました";
		}

		// INSERTの準備
		String idUser = CommonLogic.generateSeqNo("UI");
		if (idUser.equals("")) {
			// SEQ番号のインクリメントに失敗したら即エラー
			return "アカウント作成に失敗しました";
		}

		// アカウント作成処理（ユーザ情報）
		userInfoDto.setIdUser(idUser);
		userInfoDto.setStPassword(stPassword);
		userInfoDto.setCfDelete("0");
		T_USER_INFO_DAO tUserInfoDao = new T_USER_INFO_DAO();
		rowsInsert = tUserInfoDao.insertUserInfo(userInfoDto);
		if(rowsInsert != 1) {
			return "アカウント作成に失敗しました";
		}

		// アカウント作成処理（ユーザプロフィール）
		userProfileDto.setIdUser(idUser);
		userProfileDto.setStDisplayName(stAccountName); //デフォルトはアカウント名＝表示名
		userProfileDto.setStIconURL("/quetana/img/r-zoon.png"); // デフォルトアイコンの格納先を指定
		userProfileDto.setCfDelete("0");
		T_USER_PROFILE_DAO tUserProfileDao = new T_USER_PROFILE_DAO();
		rowsInsert = tUserProfileDao.insertUserProfile(userProfileDto);
		if(rowsInsert != 1) {
			return "アカウント作成に失敗しました";
		}
		return null;
	}

	/**
	 * 本当にこれでいいのか…。
	 * @param userInfoDto
	 * @return
	 */
	private static Map chkDuplicateUser(UserInfoDto userInfoDto, UserProfileDto userProfileDto) {

		Map rtnMap = new HashMap();
		int rowExec;

		// T_USER_INFOのアカウント名重複チェック
		T_USER_INFO_DAO tUserInfoDao = new T_USER_INFO_DAO();
		rowExec = tUserInfoDao.selectAccountName(userInfoDto);
		if (rowExec != 0){
			rtnMap.put("cdErr", rowExec);
			rtnMap.put("stDup", "アカウント名");
			return rtnMap;
		}
		// T_USER_INFOのメールアドレス重複チェック
		rowExec = tUserInfoDao.selectMailAddress(userInfoDto);
		if (rowExec != 0){
			rtnMap.put("cdErr", rowExec);
			rtnMap.put("stDup", "メールアドレス");
			return rtnMap;
		}

		// T_USER_PROFILEのアカウント名重複チェック
		T_USER_PROFILE_DAO tUserProfileDao = new T_USER_PROFILE_DAO();
		rowExec = tUserProfileDao.selectAccountName(userProfileDto);
		if (rowExec != 0){
			rtnMap.put("cdErr", rowExec);
			rtnMap.put("stDup", "アカウント名");
			return rtnMap;
		}

		rtnMap.put("cdErr", rowExec);
		rtnMap.put("stDup", "");
		return rtnMap;
	}

	private static int checkDuplicateName(String dbType, UserInfoDto userInfoDto) {
		T_USER_INFO_DAO tUserInfoDao = new T_USER_INFO_DAO();
		int rowsSelectCount = tUserInfoDao.selectAccountName(userInfoDto);
		return rowsSelectCount;
	}

	private static int checkDuplicateMailAddress(UserInfoDto userInfoDto) {
		T_USER_INFO_DAO tUserInfoDao = new T_USER_INFO_DAO();
		int rowsSelectCount = tUserInfoDao.selectMailAddress(userInfoDto);
		return rowsSelectCount;
	}

}
