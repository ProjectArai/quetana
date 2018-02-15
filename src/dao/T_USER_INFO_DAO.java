package dao;

//DAO用のインポート
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import model.UserInfoBean;


public class T_USER_INFO_DAO {
	public UserInfoBean insertUserInfo(String idUser, String stUserName, String stMailAddress, String stPassword, String stIconURL) {
		Connection conn = null;

		try {
			// JDBCドライバを読み込み
			Class.forName("org.mariadb.jdbc.Driver");

			// DBへ接続
			conn = DriverManager.getConnection("jdbc:mariadb://localhost/mysql", "root", "mon202");

			// INSERT文を準備
			String sql = "INSERT INTO mysql.T_USER_INFO VALUES('" + idUser + "', '" + stUserName + "', '" + stMailAddress + "', '" + stPassword + "', '" + stIconURL + "', false, now(), now());";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			// INSERTを実行
			pStmt.executeQuery();
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
		UserInfoBean userInfoBean = new UserInfoBean(idUser, stUserName, stMailAddress, stPassword, stIconURL);
		return userInfoBean;
	}
}
