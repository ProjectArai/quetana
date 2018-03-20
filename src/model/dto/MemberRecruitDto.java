package model.dto;

import java.util.Date;

public class MemberRecruitDto {
	/* 兼用カラム */
	private String idUser;    //ユーザID

	/* T_MEMBER_RECRUITカラム */
	private String idPost;    //投稿ID
	private String stTitle;   //タイトル
	private String stPart;    //募集パート
	private String stGenre;   //演奏ジャンル
	private String stDetails; //詳細
	private String cfDelete;  //削除FLG
	private Date dtUpdate;    //更新日付
	private Date dtResist;    //登録日付

	/* T_USER_PROFILE用カラム */
	private String stAccountName; //アカウント名
	private String stDisplayName; //表示名
	private String stIconURL;     //アイコンURL

	/* コンストラクタ */
	public MemberRecruitDto() {}

	/* Setter、Getter */
	public String getIdUser() {
		return idUser;
	}

	public void setIdUser(String idUser) {
		this.idUser = idUser;
	}

	public String getIdPost() {
		return idPost;
	}

	public void setIdPost(String idPost) {
		this.idPost = idPost;
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

}
