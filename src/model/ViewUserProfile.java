package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dao.T_USER_PROFILE_DAO;
import model.dto.UserProfileDto;

public class ViewUserProfile {

	public static Map getUserProfile(String idUser) {

		Map rtnMap = new HashMap();
		String errMsg;
//		UserProfileBean userPlofile = new UserProfileBean("A001", "r-zoon", "26", "2010", "ボーカル, ギター", "9mm Parabellum Bullet, THE BACK HORN", "J-Rock, HR/HM", "/quetana/img/r-zoon.png", "youtube", "社会人になって4年ほどたちますが、今でもたまーにギターを触ったりしています。タイミングがあればライブにも出たいと思っています！！");
		UserProfileBean userProfile = new UserProfileBean();

		//ユーザIDからユーザプロフィールを取得
		T_USER_PROFILE_DAO tUserProfile = new T_USER_PROFILE_DAO();
		List<UserProfileDto> arrUserProfile = new ArrayList();
		arrUserProfile = tUserProfile.selectUserProfile(idUser);

		if (arrUserProfile.size() == 1) {
			// DBから取得したユーザプロフィールが1件の場合
			UserProfileDto userProfileDto = new UserProfileDto();
			userProfileDto = arrUserProfile.get(0);
			// ユーザプロフィールをセット
			userProfile.setIdUser(userProfileDto.getIdUser());
			userProfile.setStUserName(userProfileDto.getStUserName());
			userProfile.setNmAge(userProfileDto.getNmAge());
			userProfile.setNmAddYear(userProfileDto.getNmAddYear());
			userProfile.setStPart(userProfileDto.getStPart());
			userProfile.setStFBand(userProfileDto.getStFBand());
			userProfile.setStFGenre(userProfileDto.getStFGenre());
			userProfile.setStIconURL(userProfileDto.getStIconURL());
			userProfile.setStVideoURL(userProfileDto.getStVideoURL());
			userProfile.setStComment(userProfileDto.getStComment());
			errMsg = "";
		} else {
			// DBから取得したユーザプロフィールが1件以外の場合
			errMsg = "システムエラー 管理者に連絡して下さい";
		}

		rtnMap.put("errMsg", errMsg);
		rtnMap.put("userProfile", userProfile);
		return rtnMap;
	}
}
