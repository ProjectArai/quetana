package model;

public class UserProfileBean {
	private String idUser;     //ユーザID
	private String stUserName; //名前
	private String nmAge;      //年齢
	private String nmAddYear;  //入学年度
	private String stPart;     //担当パート
	private String stFBand;    //好きなバンド
	private String stFGenre;   //好きなジャンル
	private String stIconURL;  //アイコンURL
	private String stVideoURL; //動画URL
	private String stComment;  //コメント

	public UserProfileBean() {}

	/**
	 * テスト用コンストラクタ（本コーディングに使いまわしてもおｋ
	 * @param idUser
	 * @param stUserName
	 * @param nmAge
	 * @param nmAddYear
	 * @param stPart
	 * @param stFBand
	 * @param stFGenre
	 * @param stIconURL
	 * @param stVideoURL
	 * @param stComment
	 */
	public UserProfileBean(String idUser, String stUserName, String nmAge, String nmAddYear, String stPart, String stFBand,String stFGenre, String stIconURL, String stVideoURL, String stComment) {
		this.setIdUser(idUser);
		this.setStUserName(stUserName);
		this.setNmAge(nmAge);
		this.setNmAddYear(nmAddYear);
		this.setStPart(stPart);
		this.setStFBand(stFBand);
		this.setStFGenre(stFGenre);
		this.setStIconURL(stIconURL);
		this.setStVideoURL(stVideoURL);
		this.setStComment(stComment);
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

	public String getNmAge() {
		return nmAge;
	}

	public void setNmAge(String nmAge) {
		this.nmAge = nmAge;
	}

	public String getNmAddYear() {
		return nmAddYear;
	}

	public void setNmAddYear(String nmAddYear) {
		this.nmAddYear = nmAddYear;
	}

	public String getStPart() {
		return stPart;
	}

	public void setStPart(String stPart) {
		this.stPart = stPart;
	}

	public String getStFBand() {
		return stFBand;
	}

	public void setStFBand(String stFBand) {
		this.stFBand = stFBand;
	}

	public String getStFGenre() {
		return stFGenre;
	}

	public void setStFGenre(String stFGenre) {
		this.stFGenre = stFGenre;
	}

	public String getStIconURL() {
		return stIconURL;
	}

	public void setStIconURL(String stIconURL) {
		this.stIconURL = stIconURL;
	}

	public String getStVideoURL() {
		return stVideoURL;
	}

	public void setStVideoURL(String stVideoURL) {
		this.stVideoURL = stVideoURL;
	}

	public String getStComment() {
		return stComment;
	}

	public void setStComment(String stComment) {
		this.stComment = stComment;
	}

}
