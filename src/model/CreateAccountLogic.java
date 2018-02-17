package model;

import dao.T_USER_INFO_DAO;


public class CreateAccountLogic {
	public static boolean createAccount(String stUserName, String stMailAddress, String stPassword) {

		//T_USER_INFO_DAOのcreateAccount()メソッドを実行し、成功したらuserInfoBeanインスタンスに結果を格納する。
		T_USER_INFO_DAO t_user_info_dao = new T_USER_INFO_DAO();
		int count = checkDuplicateName(stUserName);
		System.out.println("●●●●●");
		System.out.println("エラーコード" + count + "です");
		System.out.println("●●●●●");
		String stIconURL = "img/r-zoon.png";
		int rowsInsert = t_user_info_dao.insertUserInfo(stUserName, stMailAddress, stPassword, stIconURL);
		if(rowsInsert == 1) {
			return true;
		} else {
			return false;
		}
	}

	public static int checkDuplicateName(String stUserName) {

		//T_USER_INFO_DAOのcreateAccount()メソッドを実行し、成功したらuserInfoBeanインスタンスに結果を格納する。
		T_USER_INFO_DAO t_user_info_dao = new T_USER_INFO_DAO();
		int rowsSelectCount = t_user_info_dao.selectUserName(stUserName);
		return rowsSelectCount;
	}
}
