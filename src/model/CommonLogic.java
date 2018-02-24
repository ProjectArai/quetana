package model;

import dao.M_SEQ_NO_DAO;
import model.dto.SeqNoDto;

public class CommonLogic {

	/**
	 * 現SEQ番号からインクリメントされたIDを生成
	 * @param idType
	 * @return rtnId  :失敗したら空文字
	 */
	public static String generateSeqNo (String idType) {

		String rtnId;

		// SeqNoDtoのインスタンス生成、検索用にidTypeをセット
		SeqNoDto seqNoDto = new SeqNoDto();
		seqNoDto.setIdType(idType);

		// M_SEQ_NOから対象IDのSEQ番号を取得
		M_SEQ_NO_DAO mSeqNoDao = new M_SEQ_NO_DAO();
		String stSeqNo = mSeqNoDao.selectSeqNo(seqNoDto);

		// 現SEQ番号をインクリメント
		int nmSeqNo = Integer.parseInt(stSeqNo);
		nmSeqNo ++;

		// インクリメントした値にM_SEQ_NOをUPDATE
		seqNoDto.setNoSeq(nmSeqNo);
		int rowExec = mSeqNoDao.updateSeqNo(seqNoDto);

		// UPDATE結果による判定
		if (rowExec == 1) {
			// インクリメントした値をゼロ埋め
			stSeqNo = String.format("%06d", nmSeqNo);
			// idTypeをプレフィックスに付与し、返却用ID完成
			rtnId = idType + stSeqNo;
		} else {
			//失敗したらとりあえず空文字で返却^^;
			rtnId = "";
		}
		return rtnId;
	}

}
