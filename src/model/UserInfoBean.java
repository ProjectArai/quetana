package model;

public class UserInfoBean {
	private String idUser;     //ユーザID
	private String stUserName; //名前
	private String stPassword; //パスワード
	private String stMailAddress; //メールアドレス
	private String stIconURL;  //アイコンURL

	public UserInfoBean() {
		//何もなし
	}

	public UserInfoBean(String idUser, String stUserName, String stPassword, String stMailAddress, String stIconURL) {
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

}

