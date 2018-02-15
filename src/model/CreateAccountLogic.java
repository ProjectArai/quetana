package model;

import dao.T_USER_INFO_DAO;


public class CreateAccountLogic {
	public static boolean createAccount(String stUserName, String stMailAddress, String stPassword) {

		//T_USER_INFO_DAOのcreateAccount()メソッドを実行し、成功したらuserInfoBeanインスタンスに結果を格納する。
		T_USER_INFO_DAO t_user_info_dao = new T_USER_INFO_DAO();
		String idUser = "0001";
		String stIconURL = "/icon/zono.ico";
		int rowsInsert = t_user_info_dao.insertUserInfo(idUser, stUserName, stMailAddress, stPassword, stIconURL);
		if(rowsInsert == 1) {
			return true;
		} else {
			return false;
		}
	}

}
