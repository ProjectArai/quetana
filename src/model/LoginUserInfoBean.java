package model;

/**
 * @author Admin
 *セッションスコープに持たせるログインユーザの情報
 *（認証やヘッダー表示に使用）
 */
public class LoginUserInfoBean {
	private String idUser;        //ユーザID
	private String stAccountName; //アカウント名前
	private String stIconURL;     //アイコンURL

	public LoginUserInfoBean() {}

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

	public String getStIconURL() {
		return stIconURL;
	}

	public void setStIconURL(String stIconURL) {
		this.stIconURL = stIconURL;
	}

}

