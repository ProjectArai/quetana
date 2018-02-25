package model.dto;

import java.sql.Date;

public class TimeLineDto {
	private String idPost;    //投稿ID
	private String idUser;    //ユーザID
	private String stAccountName;    //アカウント名
	private String stDisplayName;    //表示名
	private String stIconURL; //アイコンURL
	private String stTitle;   //タイトル
	private String stPart;    //募集パート
	private String stGenre;   //演奏ジャンル
	private String stPlace;   //開催場所
	private Date dtEvent;     //開催日
	private String stDetails; //詳細
	private String cfDelete; //削除フラグ
	private Date dtUpdate;    //更新日付
	private Date dtResist;    //登録日付

	public TimeLineDto() {}

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

	public String getStAccountName() {
		return stAccountName;
	}

	public void setStAccountName(String stAccountName) {
		this.stAccountName = stAccountName;
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

	public String getStTitle() {
		return stTitle;
	}

	public void setStTitle(String stTitle) {
		this.stTitle = stTitle;
	}

	public String getStPart() {
		return stPart;
	}

	public void setStPart(String stPart) {
		this.stPart = stPart;
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

	public Date getDtEvent() {
		return dtEvent;
	}

	public void setDtEvent(Date dtEvent) {
		this.dtEvent = dtEvent;
	}

	public String getStDetails() {
		return stDetails;
	}

	public void setStDetails(String stDetails) {
		this.stDetails = stDetails;
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
