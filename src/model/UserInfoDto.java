package model;

import java.util.Date;

public class UserInfoDto {
	private String idUser;        //ユーザID
	private String stUserName;    //名前
	private String stPassword;    //パスワード
	private String stMailAddress; //メールアドレス
	private String stIconURL;     //アイコンURL
	private String cfDelete;      //削除FLG
	private Date dtUpdate;        //更新日付
	private Date dtResist;        //登録日付

	public UserInfoDto() {}

	public UserInfoDto(String idUser, String stUserName, String stPassword, String stMailAddress, String stIconURL) {
		this.idUser     = idUser;
		this.stUserName = stUserName;
		this.stPassword    = stPassword;
		this.stMailAddress = stMailAddress;
		this.stIconURL  = stIconURL;
	}

	public String getIdUser() {
		return idUser;
	}

	public void setIdUser(String idUser) {
		this.idUser = idUser;
	}

	public String getStUserName() {
		return stUserName;
	}

	public void setStUserName(String stUserName) {
		this.stUserName = stUserName;
	}

	public String getStPassword() {
		return stPassword;
	}

	public void setStPassword(String stPassword) {
		this.stPassword = stPassword;
	}

	public String getStMailAddress() {
		return stMailAddress;
	}

	public void setStMailAddress(String stMailAddress) {
		this.stMailAddress = stMailAddress;
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
