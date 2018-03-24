package model.logic;

import java.util.HashMap;
import java.util.Map;

import model.dao.M_SEQ_NO_DAO;
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

	public static Map<String, String> getStPartName () {

		Map<String, String> mapPart = new HashMap<String, String>(){
			{
				put("01", "Vo.");
				put("02", "Gt.");
				put("03", "Ba.");
				put("04", "Key.");
				put("05", "Dr.");
				put("06", "Per.");
				put("07", "Cho.");
				put("08", "Other");
			}
		};

		return mapPart;
	}

	public static Map<String, String> getPartCheck () {

		Map<String, String> mapPartCheck = new HashMap<String, String>(){
			{
				put("01", "");
				put("02", "");
				put("03", "");
				put("04", "");
				put("05", "");
				put("06", "");
				put("07", "");
				put("08", "");
			}
		};

		return mapPartCheck;
	}
}
