package model;

import java.util.HashMap;
import java.util.Map;

import dao.T_USER_PROFILE_DAO;
import model.dto.UserProfileDto;

public class EditUserProfile {

	public static Map editUserProfile(Map inParam) {

		Map rtnMap = new HashMap();
		String errMsg;

		//inParamをDTOに詰め直し
		UserProfileDto userProfileDto = new UserProfileDto();
		userProfileDto.setIdUser((String)inParam.get("idUser"));
		userProfileDto.setStUserName((String)inParam.get("stUserName"));
		userProfileDto.setNmAge((String)inParam.get("nmAge"));
		userProfileDto.setNmAddYear((String)inParam.get("nmAddYear"));
		userProfileDto.setStPart((String)inParam.get("stPart"));
		userProfileDto.setStFBand((String)inParam.get("stFBand"));
		userProfileDto.setStFGenre((String)inParam.get("stFGenre"));
		userProfileDto.setStIconURL((String)inParam.get("stIconURL"));
		userProfileDto.setStVideoURL((String)inParam.get("stVideoURL"));
		userProfileDto.setStComment((String)inParam.get("stComment"));
		userProfileDto.setCfDelete("0");

		// UPDATE処理の実行
		T_USER_PROFILE_DAO tUserProfile = new T_USER_PROFILE_DAO();
		String resultExecute = tUserProfile.updateUserProfile(userProfileDto);

		// UPDATE実行結果の判定
		if (resultExecute.equals("1")) {
			// 更新件数が1件の場合(成功)
			errMsg = "";
		} else {
			// 更新件数が1件以外またはnullの場合(失敗)
			// ★2件更新されちゃった場合どうしよう？WHERE句にUNIQUEカラム指定してるからおｋ？
			errMsg = "システムエラー 管理者に連絡して下さい";
		}

		rtnMap.put("errMsg", errMsg);
		return rtnMap;
	}
}
