package model.logic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.bean.UserProfileBean;
import model.dao.T_USER_PROFILE_DAO;
import model.dto.UserProfileDto;

public class ProfileViewLogic {

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
			if (userProfileDto.getNmAge() == null) {
				userProfile.setNmAge("");
			} else {
				userProfile.setNmAge(userProfileDto.getNmAge());
			}
			if (userProfileDto.getNmAddYear() == null) {
				userProfile.setNmAddYear("");
			} else {
				userProfile.setNmAddYear(userProfileDto.getNmAddYear());
			}
			if (userProfileDto.getStPart() == null) {
				userProfile.setStPart("");
			} else {
				Map<String, String> mapPart = CommonLogic.getStPartName();
				String stPart = "";
				String stPartName = "";
				String[] arrPart = userProfileDto.getStPart().split(",", 0);
				for (int i = 0; i < arrPart.length; i++) {
					stPartName = mapPart.get(arrPart[i]);
					stPart += stPartName;
					// 最後以外は半角スペース区切り
					if (i < (arrPart.length - 1)) {
						stPart += " ";
					}
				}
				userProfile.setStPart(stPart);
			}
			if (userProfileDto.getStFBand() == null) {
				userProfile.setStFBand("");
			} else {
				userProfile.setStFBand(userProfileDto.getStFBand());
			}
			if (userProfileDto.getStFGenre() == null) {
				userProfile.setStFGenre("");
			} else {
				userProfile.setStFGenre(userProfileDto.getStFGenre());
			}
			userProfile.setStIconURL(userProfileDto.getStIconURL());
			if (userProfileDto.getStVideoURL() == null) {
				userProfile.setStVideoURL("");
			} else {
				userProfile.setStVideoURL(userProfileDto.getStVideoURL());
			}
			if (userProfileDto.getStComment() == null) {
				userProfile.setStComment("");
			} else {
				userProfile.setStComment(userProfileDto.getStComment());
			}
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
