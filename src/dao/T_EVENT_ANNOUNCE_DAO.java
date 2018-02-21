package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import model.dto.EventAnnounceDto;

public class T_EVENT_ANNOUNCE_DAO {

	/**
	 * T_EVENT_ANNOUNCEへINSERT
	 * @param timeLineDto  ：T_EVENT_ANNOUNCEのDTO
	 * @return rtnExecute  ：実行件数、DB処理失敗の場合null
	 */
	public String insertEventAnnounce(EventAnnounceDto eventAnnounceDto) {

		int rowExecute = 0;
		String rtnExecute = null;

		Connection conn = null;

		try {
			// JDBCドライバを読み込み
			Class.forName("org.mariadb.jdbc.Driver");

			// DBへ接続
			conn = DriverManager.getConnection("jdbc:mariadb://localhost/quetana_dev", "root", "mon202");

			// SELECT文を準備
			String sql =
					"insert into T_EVENT_ANNOUNCE(IDPOST, IDUSER, STTITLE, STPLACE, DTEVENT, STDETAILS, CFDELETE, DTUPDATE, DTRESIST) "
						+ "values (?, ?, ?, ?, ?, ?, '0', now(), now()); ";

			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, eventAnnounceDto.getIdPost());
			pStmt.setString(2, eventAnnounceDto.getIdUser());
			pStmt.setString(3, eventAnnounceDto.getStTitle());
			pStmt.setString(4, eventAnnounceDto.getStPlace());
			pStmt.setDate(5, eventAnnounceDto.getDtEvent());
			pStmt.setString(6, eventAnnounceDto.getStDetails());

			// INSERTを実行し、実行結果をrowExecuteに格納
			rowExecute = pStmt.executeUpdate();

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

		// 実行件数をString型にキャストしてreturn
		rtnExecute = String.valueOf(rowExecute);
		return rtnExecute;
	}
}
