package dao;

//DAO用のインポート
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class T_USER_INFO_DAO {
	public int insertUserInfo(String stUserName, String stMailAddress, String stPassword, String stIconURL) {
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

	public int selectUserName(String stUserName) {
		int rowsSelectCount = 2;
		Connection conn = null;

		try {
			// JDBCドライバを読み込み
			Class.forName("org.mariadb.jdbc.Driver");

			// DBへ接続
			conn = DriverManager.getConnection("jdbc:mariadb://localhost/quetana_dev", "root", "mon202");

			// INSERT文を準備
			String sql =
				"SELECT COUNT(*) FROM T_USER_INFO"
					+ " WHERE STUSERNAME = "
						+ "'" + stUserName + "'"
					+ ";"
				;

			PreparedStatement pStmt = conn.prepareStatement(sql);

			// INSERTを実行し、実行結果をrowInsertに格納
			ResultSet rs = pStmt.executeQuery();
			rs.last();
			rowsSelectCount = rs.getRow();
			System.out.println(rowsSelectCount);

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
		return rowsSelectCount;
	}
}
