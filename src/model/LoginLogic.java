package model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dao.T_USER_INFO_DAO;

public class LoginLogic {

	public static Map getLoginUserInfo(Map inParam) {

		// 戻り値に指定する要素
		Map rtnMap = new HashMap();
		String errType = null;
		String errMsg = null;
		LoginUserInfoBean loginUserInfo = new LoginUserInfoBean();

		// inputを基にDBからユーザ情報を取得
		UserInfoDto userInfoDto = new UserInfoDto();
		userInfoDto.setStAccountName((String)inParam.get("stLoginUser"));
		userInfoDto.setStMailAddress((String)inParam.get("stLoginUser"));
		T_USER_INFO_DAO tUserInfo = new T_USER_INFO_DAO();
		List<UserInfoDto> arrUserInfo = tUserInfo.selectUserInfo(userInfoDto);

		// Daoの返却値によって処理分岐
		if (arrUserInfo == null) {
			// DB処理失敗の場合
			errType = "system";
			errMsg = "DataBase Error";

		} else if (arrUserInfo.size() >= 2){
			// DBから取得したユーザ情報が2件以上の場合：そんなわけない、とりあえずSysErr
			errType = "system";
			errMsg = "DataBase Error";

		} else if (arrUserInfo.size() == 0) {
			// DBから取得したユーザ情報が0件の場合：アカウントが存在しない
			errType = "input";
			errMsg = "アカウント名またはメールアドレスが間違っています";

		} else if (arrUserInfo.size() == 1) {
			// DBから取得したユーザ情報が1件の場合：該当するアカウントが存在する

			// selectUserInfoの結果からDto取得
			userInfoDto = arrUserInfo.get(0);

			// 入力パスワードと登録パスワードを照合
			if (((String)inParam.get("stPassword")).equals(userInfoDto.getStPassword())) {
				// パスワードが一致した場合
				// ログインユーザ情報をセット
				loginUserInfo.setIdUser(userInfoDto.getIdUser());
				loginUserInfo.setStAccountName(userInfoDto.getStAccountName());
				loginUserInfo.setStIconURL(userInfoDto.getStIconURL());
			} else {
				//パスワードが一致しない場合
				errType = "input";
				errMsg = "パスワードが間違っています";
			}
		}

		rtnMap.put("errType", errType);
		rtnMap.put("errMsg", errMsg);
		rtnMap.put("loginUserInfo", loginUserInfo);
		return rtnMap;
	}
}
