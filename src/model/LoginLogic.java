package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dao.T_USER_INFO_DAO;

public class LoginLogic {

	public static Map getLoginUserInfo(String stLoginUser, String stPassword) {

		Map rtnMap = new HashMap();
		String errMsg;
		UserInfoBean loginUserInfo = new UserInfoBean();

		T_USER_INFO_DAO tUserInfo = new T_USER_INFO_DAO();
		List<UserInfoDto> arrUserInfo = new ArrayList();

		//ユーザ名またはメールアドレスを基にDBからユーザ情報を取得
		arrUserInfo = tUserInfo.selectUserInfo(stLoginUser);

		//Daoの返却値によって処理分岐
		if (arrUserInfo.size() == 1) {
			// DBから取得したユーザ情報が1件の場合
			UserInfoDto userInfoDto = new UserInfoDto();
			userInfoDto = arrUserInfo.get(0);
			//入力パスワードと登録パスワードを照合
			if (stPassword.equals(userInfoDto.getStPassword())) {
				//パスワードが一致した場合
				errMsg = "";
				loginUserInfo.setStUserName(userInfoDto.getStUserName());
				loginUserInfo.setStIconURL(userInfoDto.getStIconURL());
			} else {
				//パスワードが一致しない場合
				errMsg = "パスワードが一致しません";
			}
		} else if (arrUserInfo.size() == 0) {
			// DBから取得したユーザ情報が0件の場合
			errMsg = "ユーザ名またはメールアドレスが間違っています";
		} else {
			// DBから取得したユーザ情報が2件以上、 またはDB処理失敗等の場合
			errMsg = "システムエラー 管理者に連絡して下さい";
		}

		rtnMap.put("errMsg", errMsg);
		rtnMap.put("loginUserInfo", loginUserInfo);
		return rtnMap;
	}
}

