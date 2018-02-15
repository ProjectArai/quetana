package dao;

//DAO用のインポート
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class T_USER_INFO_DAO {
	public int insertUserInfo(String idUser, String stUserName, String stMailAddress, String stPassword, String stIconURL) {
		int rowsInsert = 0;
		Connection conn = null;

		try {
			// JDBCドライバを読み込み
			Class.forName("org.mariadb.jdbc.Driver");

			// DBへ接続
			conn = DriverManager.getConnection("jdbc:mariadb://localhost/mysql", "root", "mon202");

			// INSERT文を準備
			String sql = "INSERT INTO mysql.T_USER_INFO VALUES('" +
							idUser + "', '" +
							stUserName + "', '" +
							stMailAddress + "', '" +
							stPassword + "', '" +
							stIconURL +
							"', false, now(), now());";
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
}
