package model.dto;

import java.util.Date;

public class TimeLineDto {
	private String idPost;   //投稿ID
	private String cfPost;   //投稿区分
	private String cfDelete; //削除FLG
	private Date dtUpdate;   //更新日付
	private Date dtResist;   //登録日付

	public TimeLineDto() {}

	public String getIdPost() {
		return idPost;
	}

	public void setIdPost(String idPost) {
		this.idPost = idPost;
	}

	public String getCfPost() {
		return cfPost;
	}

	public void setCfPost(String cfPost) {
		this.cfPost = cfPost;
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
