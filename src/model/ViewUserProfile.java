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
		UserProfileBean userProfile = new UserProfileBean();

		UserProfileDto userProfileDto = new UserProfileDto();
		userProfileDto.setIdUser(idUser);

		//ユーザIDからユーザプロフィールを取得
		List<UserProfileDto> arrUserProfile = new ArrayList();
		T_USER_PROFILE_DAO tUserProfile = new T_USER_PROFILE_DAO();
		arrUserProfile = tUserProfile.selectUserProfile(userProfileDto);

		if (arrUserProfile.size() == 1) {
			// DBから取得したユーザプロフィールが1件の場合
			userProfileDto = arrUserProfile.get(0);
			// ユーザプロフィールをセット
			userProfile.setIdUser(userProfileDto.getIdUser());
			userProfile.setStAccountName(userProfileDto.getStAccountName());
			userProfile.setStDisplayName(userProfileDto.getStDisplayName());
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
