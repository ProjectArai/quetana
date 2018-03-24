package model.logic;

import java.util.Map;

import model.dao.T_USER_INFO_DAO;
import model.dao.T_USER_PROFILE_DAO;
import model.dto.UserInfoDto;
import model.dto.UserProfileDto;


public class CreateAccountLogic {
	public static String createAccount(Map inParam) {

		int cntInsRow;
		String stErrorMsg = "";

		// アカウント情報の重複チェック
		stErrorMsg = chkDuplicateUser(inParam);
		if(!stErrorMsg.equals("")) {
			// 重複があれば即エラー
			return stErrorMsg;
		}

		// ★本当はここから先の処理を1つのトランザクションとしてまとめたい…

		// INSERTの準備で新規ユーザIDを発行
		String idUser = CommonLogic.generateSeqNo("UI");
		if (idUser.equals("")) {
			// ユーザIDの発行に失敗したら即エラー
			stErrorMsg = "システムエラー 管理者に連絡して下さい";
			return stErrorMsg;
		}

		// アカウント作成処理（ユーザ情報）
		// T_USER_INFO用のDTO作成
		UserInfoDto userInfoDto = new UserInfoDto();
		userInfoDto.setIdUser(idUser);
		userInfoDto.setStAccountName((String)inParam.get("stAccountName"));
		userInfoDto.setStMailAddress((String)inParam.get("stMailAddress"));
		userInfoDto.setStPassword((String)inParam.get("stPassword"));
		userInfoDto.setCfDelete("0");
		T_USER_INFO_DAO tUserInfoDao = new T_USER_INFO_DAO();
		cntInsRow = tUserInfoDao.insertUserInfo(userInfoDto);
		if(cntInsRow != 1) {
			// アカウント作成に失敗したら即エラー
			stErrorMsg = "システムエラー 管理者に連絡して下さい";
			return stErrorMsg;
		}

		// アカウント作成処理（ユーザプロフィール）
		// T_USER_PROFILE用のDTO作成
		UserProfileDto userProfileDto = new UserProfileDto();
		userProfileDto.setIdUser(idUser);
		userProfileDto.setStAccountName((String)inParam.get("stAccountName"));
		userProfileDto.setStDisplayName((String)inParam.get("stAccountName")); //デフォルトはアカウント名＝表示名
		userProfileDto.setStIconURL("/quetana/img/default-icon.jpg"); // デフォルトアイコンの格納先を指定
		userProfileDto.setCfDelete("0");
		T_USER_PROFILE_DAO tUserProfileDao = new T_USER_PROFILE_DAO();
		cntInsRow = tUserProfileDao.insertUserProfile(userProfileDto);
		if(cntInsRow != 1) {
			// アカウント作成に失敗したら即エラー
			stErrorMsg = "システムエラー 管理者に連絡して下さい";
			return stErrorMsg;
		}

		// オールクリアで空文字を返す
		return stErrorMsg;
	}

	/**
	 * 本当にこれでいいのか…。→ちょっと直した
	 * @param inParam
	 * @return stErrorMsg
	 */
	private static String chkDuplicateUser(Map inParam) {

		String stErrorMsg = "";
		String cntGetRow = null;

		// T_USER_INFOのアカウント名重複チェック
		T_USER_INFO_DAO tUserInfoDao = new T_USER_INFO_DAO();
		cntGetRow = tUserInfoDao.countAccountName((String)inParam.get("stAccountName"));
		if (cntGetRow == null){
			// nullだった場合DB処理エラー
			stErrorMsg = "システムエラー 管理者に連絡して下さい";
			return stErrorMsg;
		} else if (!cntGetRow.equals("0")) {
			// count結果が0件以外の場合は重複エラー
			stErrorMsg = "登録済みのアカウント名です";
			return stErrorMsg;
		}

		// T_USER_INFOのメールアドレス重複チェック
		cntGetRow = tUserInfoDao.countMailAddress((String)inParam.get("stMailAddress"));
		if (cntGetRow == null){
			// nullだった場合DB処理エラー
			stErrorMsg = "システムエラー 管理者に連絡して下さい";
			return stErrorMsg;
		} else if (!cntGetRow.equals("0")) {
			// count結果が0件以外の場合は重複エラー
			stErrorMsg = "登録済みのメールアドレスです";
			return stErrorMsg;
		}

		// T_USER_PROFILEのアカウント名重複チェック
		T_USER_PROFILE_DAO tUserProfileDao = new T_USER_PROFILE_DAO();
		cntGetRow = tUserProfileDao.countAccountName((String)inParam.get("stAccountName"));
		if (cntGetRow == null){
			// nullだった場合DB処理エラー
			stErrorMsg = "システムエラー 管理者に連絡して下さい";
			return stErrorMsg;
		} else if (!cntGetRow.equals("0")) {
			// count結果が0件以外の場合は重複エラー
			stErrorMsg = "登録済みのアカウント名です";
			return stErrorMsg;
		}

		// オールクリアで空文字を返す
		return stErrorMsg;
	}

}
