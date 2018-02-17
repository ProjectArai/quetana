package dao;

//DAO用のインポート
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.UserInfoDto;


public class T_USER_INFO_DAO {
	public int insertUserInfo(String idUser, String stUserName, String stMailAddress, String stPassword, String stIconURL) {
		int rowsInsert = 0;
		Connection conn = null;

		try {
			// JDBCドライバを読み込み
			Class.forName("org.mariadb.jdbc.Driver");

			// DBへ接続
			conn = DriverManager.getConnection("jdbc:mariadb://localhost/quetana_dev", "root", "mon202");

			// INSERT文を準備
			String sql =
				"INSERT INTO T_USER_INFO (STUSERNAME, STPASSWORD, STMAILADDRESS, STICONURL, CFDELETE, DTUPDATE, DTRESIST) "
					+ "VALUES ("
						+ "'" + stUserName + "', "
						+ "'" + stPassword + "', "
						+ "'" + stMailAddress + "', "
						+ "'" + stIconURL + "', "
						+" false, "
						+ "now(), "
						+ "now()"
					+ ");"
				;

			PreparedStatement pStmt = conn.prepareStatement(sql);

			// INSERTを実行し、実行結果をrowInsertに格納
			rowsInsert = pStmt.executeUpdate();

		} catch(SQLException e) {
			e.printStackTrace();
			return 0;
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
			return 0;
		} finally {
			// DB切断
			if(conn != null) {
				try {
					conn.close();
				} catch(SQLException e) {
					e.printStackTrace();
					return 0;
				}
			}
		}
		return rowsInsert;
	}

	/**
	 * ユーザ名またはメールアドレスが一致するユーザー情報を取得
	 * @param stLoginUser ：ユーザ名またはメールアドレス
	 * @return arrUserInfo ：該当するユーザ情報、DB処理失敗の場合はnull
	 */
	public List<UserInfoDto> selectUserInfo(String stLoginUser) {

		List<UserInfoDto> arrUserInfo = new ArrayList();

		Connection conn = null;

		try {
			// JDBCドライバを読み込み
			Class.forName("org.mariadb.jdbc.Driver");

			// DBへ接続
			conn = DriverManager.getConnection("jdbc:mariadb://localhost/quetana_dev", "root", "mon202");

			// SELECT文を準備
			String sql =
					"select IDUSER, STUSERNAME, STPASSWORD, STMAILADDRESS, STICONURL, CFDELETE, DTUPDATE, DTRESIST from T_USER_INFO"
					+ " where STUSERNAME = ? or STMAILADDRESS = ?;";

			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, stLoginUser);
			pStmt.setString(2, stLoginUser);

			// SELECTを実行し、結果が取得できた場合DTO型のListに格納
			ResultSet rs  = pStmt.executeQuery();
			while (rs.next()) {
				UserInfoDto dto = new UserInfoDto();
				dto.setIdUser(rs.getString("IDUSER"));
				dto.setStUserName(rs.getString("STUSERNAME"));
				dto.setStPassword(rs.getString("STPASSWORD"));
				dto.setStMailAddress(rs.getString("STMAILADDRESS"));
				dto.setStIconURL(rs.getString("STICONURL"));
				dto.setCfDelete(rs.getString("CFDELETE"));
				dto.setDtUpdate(rs.getDate("DTUPDATE"));
				dto.setDtResist(rs.getDate("DTRESIST"));
				arrUserInfo.add(dto);
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
		return arrUserInfo;
	}
}
