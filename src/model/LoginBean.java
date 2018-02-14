package model;

//ボツ
public class LoginBean {
	private String stMailAddress; //メールアドレス
	private String stPassword;    //パスワード

	public LoginBean() {}

	public LoginBean(String stMailAddress, String stPassword) {
		this.stMailAddress = stMailAddress;
		this.stPassword    = stPassword;
	}

	public String getStMailAddress() {
		return this.stMailAddress;
	}

	public String getStPassword() {
		return this.stPassword;
	}
}
