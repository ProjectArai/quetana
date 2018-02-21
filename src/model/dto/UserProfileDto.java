package model.dto;

import java.util.Date;

public class UserProfileDto {
	private String idUser;     //ユーザID
	private String stAccountName; //アカウント名
	private String stDisplayName; //表示名
	private String nmAge;      //年齢
	private String nmAddYear;  //入学年度
	private String stPart;     //担当パート
	private String stFBand;    //好きなバンド
	private String stFGenre;   //好きなジャンル
	private String stIconURL;  //アイコンURL
	private String stVideoURL; //動画URL
	private String stComment;  //コメント
	private String cfDelete;   //削除FLG
	private Date dtUpdate;     //更新日付
	private Date dtResist;     //登録日付

	public UserProfileDto() {}

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
