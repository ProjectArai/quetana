package model.dto;

public class SeqNoDto {
	private String idType;   //投稿ID
	private int noSeq;   //投稿区分

	public SeqNoDto() {}

	public String getIdType() {
		return idType;
	}

	public void setIdType(String idType) {
		this.idType = idType;
	}

	public int getNoSeq() {
		return noSeq;
	}

	public void setNoSeq(int noSeq) {
		this.noSeq = noSeq;
	}

}
