package dao;

//DAO用のインポート
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.UserInfoDto;


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
				"insert into T_USER_INFO "
				+ "(STUSERNAME, STPASSWORD, STMAILADDRESS, STICONURL, CFDELETE, DTUPDATE, DTRESIST) "
				+ "values (?, ?, ?, ?, false, now(), now());";

			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, stUserName);
			pStmt.setString(2, stPassword);
			pStmt.setString(3, stMailAddress);
			pStmt.setString(4, stIconURL);

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
	 * @param stUserName
	 * @return 0 or 1 or 2 ： Selectの結果(行数)を返す。エラーの場合は 2 を返す。
	 */
	public int selectUserName(String stUserName) {

		Connection conn = null;
		int rowsSelectCount = 0;

		try {
			// JDBCドライバを読み込み
			Class.forName("org.mariadb.jdbc.Driver");

			// DBへ接続
			conn = DriverManager.getConnection("jdbc:mariadb://localhost/quetana_dev", "root", "mon202");

			// SELECT文を準備
			String sql =
				"select STUSERNAME from T_USER_INFO where STUSERNAME = ?;";

			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, stUserName);

			// SELECT文を実行し、結果(行数)をカウント
			ResultSet rs = pStmt.executeQuery();
			while(rs.next()) {
				rowsSelectCount++;
			}

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

	/**
	 * @param stUserName
	 * @return 0 or 1 or 2 ： Selectの結果(行数)を返す。エラーの場合は 2 を返す。
	 */
	public int selectMailAddress(String stMailAddress) {

		Connection conn = null;
		int rowsSelectCount = 0;

		try {
			// JDBCドライバを読み込み
			Class.forName("org.mariadb.jdbc.Driver");

			// DBへ接続
			conn = DriverManager.getConnection("jdbc:mariadb://localhost/quetana_dev", "root", "mon202");

			// SELECT文を準備
			String sql =
				"select STMAILADDRESS from T_USER_INFO where STMAILADDRESS = ?;";

			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, stMailAddress);

			// SELECT文を実行し、結果(行数)をカウント
			ResultSet rs = pStmt.executeQuery();
			while(rs.next()) {
				rowsSelectCount++;
			}

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

	/**
	 * ユーザ名またはメールアドレス、パスワードが一致するユーザー情報を取得
	 * （ユーザ名またはメールアドレスは一意のため、該当ユーザ存在する場合は必ず1件）
	 * @param stLoginUser ：ユーザ名またはメールアドレス
	 * @param stPassword ：パスワード
	 * @return userInfoDto or null ：ユーザ情報DTO、DB処理失敗の場合はnull
	 */
	public UserInfoDto selectUserInfo01(String stLoginUser, String stPassword) {

		UserInfoDto userInfoDto = new UserInfoDto();

		Connection conn = null;

		try {
			// JDBCドライバを読み込み
			Class.forName("org.mariadb.jdbc.Driver");

			// DBへ接続
			conn = DriverManager.getConnection("jdbc:mariadb://localhost/quetana_dev", "root", "mon202");

			// SELECT文を準備
			String sql =
					"select IDUSER, STUSERNAME, STPASSWORD, STMAILADDRESS, STICONURL, CFDELETE, DTUPDATE, DTRESIST from T_USER_INFO"
					+ " where (STUSERNAME = ? or STMAILADDRESS = ?) and STPASSWORD = ? ;";

			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, stLoginUser);
			pStmt.setString(2, stLoginUser);
			pStmt.setString(3, stPassword);

			// SELECTを実行し、結果が取得できた場合DTO型のListに格納
			ResultSet rs  = pStmt.executeQuery();
//			while (rs.next()) {
			if (rs.next()) {
				userInfoDto.setIdUser(rs.getString("IDUSER"));
				userInfoDto.setStUserName(rs.getString("STUSERNAME"));
				userInfoDto.setStPassword(rs.getString("STPASSWORD"));
				userInfoDto.setStMailAddress(rs.getString("STMAILADDRESS"));
				userInfoDto.setStIconURL(rs.getString("STICONURL"));
				userInfoDto.setCfDelete(rs.getString("CFDELETE"));
				userInfoDto.setDtUpdate(rs.getDate("DTUPDATE"));
				userInfoDto.setDtResist(rs.getDate("DTRESIST"));
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
		return userInfoDto;
	}
}
