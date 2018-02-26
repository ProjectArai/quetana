package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.dto.SeqNoDto;


public class M_SEQ_NO_DAO {

	/**
	 * 現在のSEQ番号を取得
	 * @param idUser ：ユーザID
	 * @return DB処理失敗の場合はnull
	 */
	public String selectSeqNo(SeqNoDto seqNoDto) {

		String rtnSeqNo = "";

		Connection conn = null;

		try {
			// JDBCドライバを読み込み
			Class.forName("org.mariadb.jdbc.Driver");

			// DBへ接続
			conn = DriverManager.getConnection("jdbc:mariadb://localhost/quetana_dev", "root", "mon202");

			// SELECT文を準備
			String sql = "select NOSEQ from M_SEQ_NO where IDTYPE = ?;";

			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, seqNoDto.getIdType());

			// SELECTを実行し、結果が取得できた場合戻り値に格納
			ResultSet rs  = pStmt.executeQuery();
			if (rs.next()) {
				rtnSeqNo = rs.getString("NOSEQ");
			}

		} catch(SQLException e) {
			e.printStackTrace();
			return null;
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		} finally {
			// DB切断
			if(conn != null) {
				try {
					conn.close();
				} catch(SQLException e) {
					e.printStackTrace();
					return null;
				}
			}
		}
		return rtnSeqNo;
	}

	/**
	 * SEQ番号を更新
	 * @param seqNoDto
	 * @return DB処理失敗の場合はnull
	 */
	public int updateSeqNo(SeqNoDto seqNoDto) {

		int rowExecute = 0;

		Connection conn = null;

		try {
			// JDBCドライバを読み込み
			Class.forName("org.mariadb.jdbc.Driver");

			// DBへ接続
			conn = DriverManager.getConnection("jdbc:mariadb://localhost/quetana_dev", "root", "mon202");

			// SELECT文を準備
			String sql = "update M_SEQ_NO set NOSEQ = ? where IDTYPE = ?;";

			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1, seqNoDto.getNoSeq());
			pStmt.setString(2, seqNoDto.getIdType());

			// UPDATEを実行し、実行結果をrowExecuteに格納
			rowExecute = pStmt.executeUpdate();

		} catch(SQLException e) {
			e.printStackTrace();
			return 2;
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
			return 2;
		} finally {
			// DB切断
			if(conn != null) {
				try {
					conn.close();
				} catch(SQLException e) {
					e.printStackTrace();
					return 2;
				}
			}
		}
		return rowExecute;
	}

	/**
	 * ユーザIDが一致するユーザプロフィールを取得
	 * @param idUser ：ユーザID
	 * @return arrUserProfile ：該当するユーザプロフィール、DB処理失敗の場合はnull
	 */
	public String selectTimeLineSEQ(SeqNoDto seqNoDto) {

		String rtnSeqNo = "";

		Connection conn = null;

		try {
			// JDBCドライバを読み込み
			Class.forName("org.mariadb.jdbc.Driver");

			// DBへ接続
			conn = DriverManager.getConnection("jdbc:mariadb://localhost/quetana_dev", "root", "mon202");

			// SELECT文を準備
			String sql = "select NOSEQ from M_SEQ_NO where IDTYPE = ?;";

			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, seqNoDto.getIdType());

			// SELECTを実行し、結果が取得できた場合戻り値に格納
			ResultSet rs  = pStmt.executeQuery();
			if (rs.next()) {
				rtnSeqNo = rs.getString("NOSEQ");
			}

		} catch(SQLException e) {
			e.printStackTrace();
			return null;
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		} finally {
			// DB切断
			if(conn != null) {
				try {
					conn.close();
				} catch(SQLException e) {
					e.printStackTrace();
					return null;
				}
			}
		}
		return rtnSeqNo;
	}

	/**
	 * ユーザIDが一致するユーザプロフィールを取得
	 * @param idUser ：ユーザID
	 * @return arrUserProfile ：該当するユーザプロフィール、DB処理失敗の場合はnull
	 */
	public String updateTimeLineSEQ(SeqNoDto seqNoDto) {

		String rtnSeqNo = "";

		Connection conn = null;

		try {
			// JDBCドライバを読み込み
			Class.forName("org.mariadb.jdbc.Driver");

			// DBへ接続
			conn = DriverManager.getConnection("jdbc:mariadb://localhost/quetana_dev", "root", "mon202");

			// SELECT文を準備
			String sql = "update M_SEQ_NO set NOSEQ = ? where IDTYPE = ?;";

			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1, seqNoDto.getNoSeq());
			pStmt.setString(2, seqNoDto.getIdType());

			// SELECTを実行し、結果が取得できた場合戻り値に格納
			ResultSet rs  = pStmt.executeQuery();
			if (rs.next()) {
				rtnSeqNo = rs.getString("NOSEQ");
			}

		} catch(SQLException e) {
			e.printStackTrace();
			return null;
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		} finally {
			// DB切断
			if(conn != null) {
				try {
					conn.close();
				} catch(SQLException e) {
					e.printStackTrace();
					return null;
				}
			}
		}
		return rtnSeqNo;
	}
}
