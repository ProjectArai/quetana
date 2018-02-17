package model;

import dao.T_USER_INFO_DAO;


public class CreateAccountLogic {
	public static String createAccount(String stUserName, String stMailAddress, String stPassword) {

		//T_USER_INFO_DAOのインスタンスを生成
		T_USER_INFO_DAO t_user_info_dao = new T_USER_INFO_DAO();

		// ユーザ名の重複チェック
		int checkNameResult = checkDuplicateName(stUserName);
		if(checkNameResult == 1) {
			return "既に登録されているアカウント名です。";
		} else if(checkNameResult == 2) {
			return "アカウントを作成できませんでした。";
		}

		// メールアドレスの重複チェック
		int checkMailAddressResult = checkDuplicateMailAddress(stMailAddress);
		if(checkMailAddressResult == 1) {
			return "既に登録されているメールアドレスです。";
		} else if(checkMailAddressResult == 2) {
			return "アカウントを作成できませんでした。";
		}

		// デフォルトアイコンの格納先を指定
		String defaultIconURL = "/quetana/img/r-zoon.png";

		// アカウント作成処理
		int rowsInsert = t_user_info_dao.insertUserInfo(stUserName, stMailAddress, stPassword, defaultIconURL);
		if(rowsInsert == 1) {
			return null;
		} else {
			return "アカウントを作成できませんでした。";
		}
	}

	public static int checkDuplicateName(String stUserName) {
		T_USER_INFO_DAO t_user_info_dao = new T_USER_INFO_DAO();
		int rowsSelectCount = t_user_info_dao.selectUserName(stUserName);
		return rowsSelectCount;
	}

	public static int checkDuplicateMailAddress(String stMailAddress) {
		T_USER_INFO_DAO t_user_info_dao = new T_USER_INFO_DAO();
		int rowsSelectCount = t_user_info_dao.selectMailAddress(stMailAddress);
		return rowsSelectCount;
	}

}
