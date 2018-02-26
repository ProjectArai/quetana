package dao;

import java.util.HashMap;
import java.util.Map;

public class LoginDao {

	public Map getLoginInfo(String stLoginUser) {

		Map<String, String> mapLoginInfo = new HashMap<>();

		if(stLoginUser.equals("r-zoon")||stLoginUser.equals("ryota.maezono@gmail.com")) {
			mapLoginInfo.put("idUser", "Z001");
			mapLoginInfo.put("stUserName", "r-zoon");
			mapLoginInfo.put("stPassword", "ryota1229");
			mapLoginInfo.put("stMailAddress", "ryota.maezono@gmail.com");
			mapLoginInfo.put("stIconURL", "/quetana/img/r-zoon.png");
		} else {
			mapLoginInfo.put("idUser", "");
			mapLoginInfo.put("stUserName", "");
			mapLoginInfo.put("stPassword", "");
			mapLoginInfo.put("stMailAddress", "");
			mapLoginInfo.put("stIconURL", "");
		}

		return mapLoginInfo;
	}

}
