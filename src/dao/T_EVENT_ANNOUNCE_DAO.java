package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.dto.EventAnnounceDto;
import model.dto.TimeLineDto;

public class T_EVENT_ANNOUNCE_DAO {

	/**
	 * T_EVENT_ANNOUNCEへINSERT
	 * @param eventAnnounceDto  ：T_EVENT_ANNOUNCEのDTO
	 * @return rowExecute  ：実行件数、DB処理失敗の場合2
	 */
	public int insertEventAnnounce(EventAnnounceDto eventAnnounceDto) {

		int rowExecute = 0;

		Connection conn = null;

		try {
			// JDBCドライバを読み込み
			Class.forName("org.mariadb.jdbc.Driver");

			// DBへ接続
			conn = DriverManager.getConnection("jdbc:mariadb://localhost/quetana_dev", "root", "mon202");

			// SELECT文を準備
			String sql =
					"insert into T_EVENT_ANNOUNCE(IDPOST, IDUSER, STTITLE, STPLACE, DTEVENT, STDETAILS, CFDELETE, DTUPDATE, DTRESIST) "
						+ "values (?, ?, ?, ?, ?, ?, ?, now(), now()); ";

			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, eventAnnounceDto.getIdPost());
			pStmt.setString(2, eventAnnounceDto.getIdUser());
			pStmt.setString(3, eventAnnounceDto.getStTitle());
			pStmt.setString(4, eventAnnounceDto.getStPlace());
			pStmt.setDate(5, eventAnnounceDto.getDtEvent());
			pStmt.setString(6, eventAnnounceDto.getStDetails());
			pStmt.setString(7, eventAnnounceDto.getCfDelete());

			// INSERTを実行し、実行結果をrowExecuteに格納
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

	public List<TimeLineDto> selectEventAnnounce(String idPost) {

		List<TimeLineDto> arrEAInfo = new ArrayList();

		Connection conn = null;

		try {
			// JDBCドライバを読み込み
			Class.forName("org.mariadb.jdbc.Driver");

			// DBへ接続
			conn = DriverManager.getConnection("jdbc:mariadb://localhost/quetana_dev", "root", "mon202");

			// SELECT文を準備
			String sql =
					"select * from ("
							+ "(select IDPOST, IDUSER, STTITLE, STPLACE, DTEVENT, STDETAILS, CFDELETE, DTUPDATE, DTRESIST from T_EVENT_ANNOUNCE where IDPOST= ? AND CFDELETE = false) "
						+ ") TE "
						+ "left join ("
							+ "select IDUSER, STACCOUNTNAME, STDISPLAYNAME, STICONURL from T_USER_PROFILE"
						+ ") UP "
						+ "on TE.IDUSER = UP.IDUSER;";

			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, idPost);

			// SELECTを実行し、結果が取得できた場合DTO型のListに格納
			ResultSet rs  = pStmt.executeQuery();
			while (rs.next()) {
				TimeLineDto dto = new TimeLineDto();
				dto.setIdUser(rs.getString("IDUSER"));
				dto.setIdPost(rs.getString("IDPOST"));
				dto.setStTitle(rs.getString("STTITLE"));
				dto.setStPlace(rs.getString("STPLACE"));
				dto.setDtEvent(rs.getDate("DTEVENT"));
				dto.setStDetails(rs.getString("STDETAILS"));
				dto.setCfDelete(rs.getString("CFDELETE"));
				dto.setDtUpdate(rs.getDate("DTUPDATE"));
				dto.setDtResist(rs.getDate("DTRESIST"));
				dto.setStAccountName(rs.getString("STACCOUNTNAME"));
				dto.setStDisplayName(rs.getString("STDISPLAYNAME"));
				dto.setStIconURL(rs.getString("STICONURL"));

				arrEAInfo.add(dto);
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
		return arrEAInfo;
	}
}
