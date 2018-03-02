package model;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Part;

import dao.T_USER_PROFILE_DAO;
import model.dto.UserProfileDto;

public class EditUserProfile {

	public static Map editUserProfile(Map inParam) throws IOException {

		Map rtnMap = new HashMap();
		Boolean cfIconUpdate = false;
		String errMsg;

		//inParamをDTOに詰め直し
		UserProfileDto userProfileDto = new UserProfileDto();
		userProfileDto.setIdUser((String)inParam.get("idUser"));
		userProfileDto.setStDisplayName((String)inParam.get("stDisplayName"));
		if (((String)inParam.get("nmAge")).equals("")) {
			userProfileDto.setNmAge(null);
		} else {
			userProfileDto.setNmAge((String)inParam.get("nmAge"));
		}
		if (((String)inParam.get("nmAddYear")).equals("")) {
			userProfileDto.setNmAddYear(null);
		} else {
			userProfileDto.setNmAddYear((String)inParam.get("nmAddYear"));
		}
		userProfileDto.setStPart((String)inParam.get("stPart"));
		userProfileDto.setStFBand((String)inParam.get("stFBand"));
		userProfileDto.setStFGenre((String)inParam.get("stFGenre"));
		userProfileDto.setStVideoURL((String)inParam.get("stVideoURL"));
		userProfileDto.setStComment((String)inParam.get("stComment"));
		userProfileDto.setCfDelete("0");

		// アイコンのアップロード有無の判定
		Part partImgIcon = (Part)inParam.get("imgIcon");
		if (partImgIcon.getSize() != 0) {
			// アップロードされている場合
			// アイコンをユーザID＋拡張子で保存
			String stIconFileName = "/" + (String)inParam.get("idUser") + ".png";
			partImgIcon.write((String)inParam.get("stImgSavePath") + stIconFileName);
			// Dtoに新規アイコンパスをセット
			userProfileDto.setStIconURL("/quetana/img" + stIconFileName);
			// IconUpdateFLGをオン
			cfIconUpdate = true;
		} else {
			// アップロードされていない場合、Dtoに元々のアイコンパスをセット
			userProfileDto.setStIconURL((String)inParam.get("stIconURL_org"));
		}

		// UPDATE処理の実行
		T_USER_PROFILE_DAO tUserProfile = new T_USER_PROFILE_DAO();
		int resultExecute = tUserProfile.updateUserProfile(userProfileDto);

		// UPDATE実行結果の判定
		if (resultExecute == 1) {
			// 更新件数が1件の場合(成功)
			errMsg = "";
		} else {
			// 更新件数が1件以外の場合(失敗)
			errMsg = "システムエラー 管理者に連絡して下さい";
		}

		rtnMap.put("cfIconUpdate", cfIconUpdate);
		rtnMap.put("errMsg", errMsg);
		return rtnMap;
	}

	public static Map updateLoginUserInfo(String idUser) {

		Map rtnMap = new HashMap();
		String errMsg;
		LoginUserInfoBean loginUserInfo = new LoginUserInfoBean();

		//ユーザIDを基にユーザプロフィールを取得
		UserProfileDto userProfileDto = new UserProfileDto();
		userProfileDto.setIdUser(idUser);
		T_USER_PROFILE_DAO tUserProfile = new T_USER_PROFILE_DAO();
		List<UserProfileDto> arrUserProfile = tUserProfile.selectUserProfile(userProfileDto);

		if (arrUserProfile.size() == 1) {
			// DBから取得したユーザプロフィールが1件の場合
			userProfileDto = arrUserProfile.get(0);
			// ログインユーザ情報をセット
			loginUserInfo.setIdUser(userProfileDto.getIdUser());
			loginUserInfo.setStAccountName(userProfileDto.getStAccountName());
			loginUserInfo.setStIconURL(userProfileDto.getStIconURL());
			errMsg = "";
		} else {
			// DBから取得したユーザプロフィールが1件以外の場合
			errMsg = "システムエラー 管理者に連絡して下さい";
		}
		rtnMap.put("errMsg", errMsg);
		rtnMap.put("loginUserInfo", loginUserInfo);
		return rtnMap;
	}

}
