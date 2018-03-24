package model.dto;

import java.sql.Date;

public class UserInfoDto {
	private String idUser;        //ユーザID
	private String stAccountName; //アカウント名
	private String stMailAddress; //メールアドレス
	private String stPassword;    //パスワード
	private String stDisplayName; //表示名
	private String stIconURL;     //アイコンURL(T_USER_PROFILEの)
	private String cfDelete;      //削除FLG
	private Date dtUpdate;        //更新日付
	private Date dtResist;        //登録日付

	public UserInfoDto() {}

	public String getIdUser() {
		return idUser;
	}

	public void setIdUser(String idUser) {
		this.idUser = idUser;
	}

	public String getStAccountName() {
		return stAccountName;
	}

	public void setStAccountName(String stAccountName) {
		this.stAccountName = stAccountName;
	}

	public String getStMailAddress() {
		return stMailAddress;
	}

	public void setStMailAddress(String stMailAddress) {
		this.stMailAddress = stMailAddress;
	}

	public String getStPassword() {
		return stPassword;
	}

	public void setStPassword(String stPassword) {
		this.stPassword = stPassword;
	}

	public String getStDisplayName() {
		return stDisplayName;
	}

	public void setStDisplayName(String stDisplayName) {
		this.stDisplayName = stDisplayName;
	}

	public String getStIconURL() {
		return stIconURL;
	}

	public void setStIconURL(String stIconURL) {
		this.stIconURL = stIconURL;
	}

	public String getCfDelete() {
		return cfDelete;
	}

	public void setCfDelete(String cfDelete) {
		this.cfDelete = cfDelete;
	}

	public Date getDtUpdate() {
		return dtUpdate;
	}

	public void setDtUpdate(Date dtUpdate) {
		this.dtUpdate = dtUpdate;
	}

	public Date getDtResist() {
		return dtResist;
	}

	public void setDtResist(Date dtResist) {
		this.dtResist = dtResist;
	}

}
