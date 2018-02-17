package model;

import java.util.Map;

import dao.LoginDao;
import dao.T_USER_INFO_DAO;

public class LoginLogic {

	public static UserInfoBean getLoginUserInfo(String stLoginUser, String stPassword) {

		//ユーザ名またはメールアドレスを基にDBからユーザ情報を取得
		LoginDao loginDao = new LoginDao();
		Map mapLoginInfo = loginDao.getLoginInfo(stLoginUser);

		T_USER_INFO_DAO tUserInfo = new T_USER_INFO_DAO();
		UserInfoDto tst = new UserInfoDto();
		tst = tUserInfo.selectUserInfo01(stLoginUser, stPassword);

		//DB登録パスワードと入力パスワードを比較
		if(tst.getIdUser() == null) {

			//パスワードが一致した場合、UserInfoBean型インスタンスに格納・返却
			UserInfoBean userInfo =  new UserInfoBean((String)mapLoginInfo.get("idUser"), (String)mapLoginInfo.get("stUserName"), (String)mapLoginInfo.get("stPassword"), (String)mapLoginInfo.get("stMailAddress"), (String)mapLoginInfo.get("stIconURL")) ;
			return userInfo;

		} else {
			//ダメだったときどうしよう。。。今ここのせいでぬるぽ発生
			UserInfoBean userInfo =  new UserInfoBean() ;
			return userInfo;
		}
	}
}

