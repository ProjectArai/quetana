package model;

public class UserInfoBean {
	private String idUser;     //ユーザID
	private String stUserName; //名前
	private String stIconURL;  //アイコンURL

	public UserInfoBean() {
		//何もなし
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

	public String getStIconURL() {
		return stIconURL;
	}

	public void setStIconURL(String stIconURL) {
		this.stIconURL = stIconURL;
	}

}

