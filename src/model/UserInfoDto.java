package model;

import java.util.Date;

public class UserInfoDto {
	private String idUser;        //ユーザID
	private String stMailAddress; //メールアドレス
	private String stPassword;    //パスワード
	private String cfDelete;      //削除FLG
	private Date dtUpdate;        //更新日付
	private Date dtResist;        //登録日付

	public UserInfoDto() {}

	public UserInfoDto(String idUser, String stMailAddress, String stPassword, String cfDelete, Date dtUpdate, Date dtResist) {
		this.idUser     = idUser;
		this.stMailAddress = stMailAddress;
		this.stPassword    = stPassword;
		this.cfDelete    = cfDelete;
		this.dtUpdate    = dtUpdate;
		this.dtResist    = dtResist;
	}

	public String getIdUser() {
		return idUser;
	}

	public void setIdUser(String idUser) {
		this.idUser = idUser;
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
