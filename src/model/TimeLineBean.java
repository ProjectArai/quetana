package model;

public class TimeLineBean {
	private String idPost;     //投稿ID
	private String idUser;     //投稿者ID
	private String stUserName; //投稿者名
	private String stTitle;    //タイトル
	private String cfPost;     //投稿区分

	public TimeLineBean() {}

	public TimeLineBean(String idPost, String idUser, String stUserName, String stTitle, String cfPost) {
		this.idPost     = idPost;
		this.idUser     = idUser;
		this.stUserName = stUserName;
		this.stTitle    = stTitle;
		this.cfPost     = cfPost;
	}

	public String getIdPost() {
		return idPost;
	}

	public void setIdPost(String idPost) {
		this.idPost = idPost;
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

	public String getStTitle() {
		return stTitle;
	}

	public void setStTitle(String stTitle) {
		this.stTitle = stTitle;
	}

	public String getCfPost() {
		return cfPost;
	}

	public void setCfPost(String cfPost) {
		this.cfPost = cfPost;
	}

}
