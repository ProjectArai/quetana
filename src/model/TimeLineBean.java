package model;

public class TimeLineBean {
	private String idPost;     //投稿ID
	private String idUser;     //投稿者ID
	private String stUserName; //投稿者名
	private String stIconURL;  //アイコンURL
	private String stTitle;    //タイトル
	private String cfPost;     //投稿区分
	private String stRecPart;  //M:募集パート
	private String stGenre;    //M:演奏ジャンル
	private String stPlace;    //E:開催場所
	private String dtEvent;      //E:開催日
	private String stDetails;  //詳細情報

	public TimeLineBean() {}

	/**
	 * テスト用コンストラクタ（本コーディングに使いまわしてもおｋ
	 * @param idPost
	 * @param idUser
	 * @param stUserName
	 * @param stIconURL
	 * @param stTitle
	 * @param cfPost
	 * @param stRecPart
	 * @param stGenre
	 * @param stPlace
	 * @param dtEvent
	 * @param stDetails
	 */
	public TimeLineBean(String idPost, String idUser, String stUserName, String stIconURL, String stTitle, String cfPost, String stRecPart,String stGenre, String stPlace, String dtEvent, String stDetails) {
		this.idPost     = idPost;
		this.idUser     = idUser;
		this.stUserName = stUserName;
		this.stIconURL = stIconURL;
		this.stTitle    = stTitle;
		this.cfPost     = cfPost;
		this.idPost     = idPost;
		this.stRecPart     = stRecPart;
		this.stGenre     = stGenre;
		this.stPlace     = stPlace;
		this.dtEvent     = dtEvent;
		this.stDetails     = stDetails;
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

	public String getStIconURL() {
		return stIconURL;
	}

	public void setStIconURL(String stIconURL) {
		this.stIconURL = stIconURL;
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

	public String getStRecPart() {
		return stRecPart;
	}

	public void setStRecPart(String stRecPart) {
		this.stRecPart = stRecPart;
	}

	public String getStGenre() {
		return stGenre;
	}

	public void setStGenre(String stGenre) {
		this.stGenre = stGenre;
	}

	public String getStPlace() {
		return stPlace;
	}

	public void setStPlace(String stPlace) {
		this.stPlace = stPlace;
	}

	public String getDtEvent() {
		return dtEvent;
	}

	public void setDtEvent(String dtEvent) {
		this.dtEvent = dtEvent;
	}

	public String getStDetails() {
		return stDetails;
	}

	public void setStDetails(String stDetails) {
		this.stDetails = stDetails;
	}

}
