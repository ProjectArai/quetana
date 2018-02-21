package model;

import java.util.HashMap;
import java.util.Map;

import dao.M_SEQ_NO_DAO;
import dao.T_USER_INFO_DAO;
import dao.T_USER_PROFILE_DAO;
import model.dto.SeqNoDto;
import model.dto.UserProfileDto;


public class CreateAccountLogic {
	public static String createAccount(String stAccountName, String stMailAddress, String stPassword) {

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
			return "アカウントを作成できませんでした";
		}

		// INSERTの準備
		String idUser = generateSeqNo("UI");
		if (idUser.equals("")) {
			// SEQ番号のインクリメントに失敗したら即エラー
			return "アカウントを作成できませんでした";
		}
		userInfoDto.setIdUser("UI" + idUser);
		userInfoDto.setStPassword(stPassword);
		userInfoDto.setCfDelete("0");
		userProfileDto.setIdUser("UI" + idUser);
		userProfileDto.setStDisplayName(stAccountName); //デフォルトはアカウント名＝表示名
		userProfileDto.setStIconURL("/quetana/img/r-zoon.png"); // デフォルトアイコンの格納先を指定
		userProfileDto.setCfDelete("0");

		// アカウント作成処理
		int rowsInsert;
		T_USER_INFO_DAO tUserInfoDao = new T_USER_INFO_DAO();
		rowsInsert = tUserInfoDao.insertUserInfo(userInfoDto);
		if(rowsInsert != 1) {
			return "アカウントを作成できませんでした";
		}
		T_USER_PROFILE_DAO tUserProfileDao = new T_USER_PROFILE_DAO();
		rowsInsert = tUserProfileDao.insertUserProfile(userProfileDto);
		if(rowsInsert != 1) {
			return "アカウントを作成できませんでした";
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

	/**
	 * SEQ番号の取得とインクリメント
	 * @param cfPost
	 * @return stSeqNo  :失敗したら空文字
	 */
	private static String generateSeqNo (String idType) {

		SeqNoDto seqNoDto = new SeqNoDto();
		seqNoDto.setIdType(idType);

		// M_SEQ_NOから投稿IDのSEQ番号を取得
		M_SEQ_NO_DAO mSeqNoDao = new M_SEQ_NO_DAO();
		String stSeqNo = mSeqNoDao.selectSeqNo(seqNoDto);

		// 現SEQ番号をインクリメント
		int noSeq = Integer.parseInt(stSeqNo);
		noSeq = noSeq + 1;
		seqNoDto.setNoSeq(noSeq);
		int rowExec = mSeqNoDao.updateSeqNo(seqNoDto);

		if (rowExec == 1) {
			//ゼロ埋め処理
			stSeqNo = String.format("%06d", noSeq);
		} else {
			stSeqNo = "";
		}
		return stSeqNo;
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
