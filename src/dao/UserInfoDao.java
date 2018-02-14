package dao;

import java.util.HashMap;
import java.util.Map;

//ボツ
public class UserInfoDao {

	public Map getUserInfo(String idUser) {

		Map mapUserInfo = new HashMap<>();

		if(idUser.equals("Z001")) {
			mapUserInfo.put("idUser", "Z001");
			mapUserInfo.put("stUserName", "r-zoon");
			mapUserInfo.put("nmAge", "26");
			mapUserInfo.put("nmAddYear", "2010");
			mapUserInfo.put("stPart", "Vocal, Guitar");
			mapUserInfo.put("stFBand", "9mm Parabellum Bullet, THE BACK HORN");
			mapUserInfo.put("stFGenre", "J-Rock, HR/HM");
			mapUserInfo.put("stIconURL", "/quetana/img/r-zoon.png");
			mapUserInfo.put("stVideoURL", "https://www.youtube.com/watch?v=h0y0VOdyDT0");
			mapUserInfo.put("stComment", "�Љ�l�ɂȂ���4�N�قǂ����܂����A���ł����܁[�ɃM�^�[��G�����肵�Ă��܂��B�^�C�~���O������΃��C�u�ɂ��o�����Ǝv���Ă��܂��I�I");
		} else {
			mapUserInfo.put("idUser", "");
		}

		return mapUserInfo;
	}

}
