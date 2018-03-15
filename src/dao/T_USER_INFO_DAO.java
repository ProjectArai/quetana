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

	public int insertUserInfo(UserInfoDto userInfoDto) {
		int rowExec = 0;
		Connection conn = null;

		try {
			// JDBCドライバを読み込み
			Class.forName("org.mariadb.jdbc.Driver");

			// DBへ接続
			conn = DriverManager.getConnection("jdbc:mariadb://localhost/quetana_dev", "root", "mon202");

			// INSERT文を準備
			String sql =
				"insert into T_USER_INFO "
				+ "(IDUSER, STACCOUNTNAME, STMAILADDRESS, STPASSWORD, CFDELETE, DTUPDATE, DTRESIST) "
				+ "values (?, ?, ?, ?, ?, now(), now());";

			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, userInfoDto.getIdUser());
			pStmt.setString(2, userInfoDto.getStAccountName());
			pStmt.setString(3, userInfoDto.getStMailAddress());
			pStmt.setString(4, userInfoDto.getStPassword());
			pStmt.setString(5, userInfoDto.getCfDelete());

			// INSERTを実行し、実行結果をrowExecに格納
			rowExec = pStmt.executeUpdate();

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
		return rowExec;
	}

	/**
	 * @param stAccountName
	 * @return cntRow or null ： countの結果(件数)、エラーの場合はnullを返す。
	 */
	public String countAccountName(String stAccountName) {

		Connection conn = null;
		String cntRow = null;

		try {
			// JDBCドライバを読み込み
			Class.forName("org.mariadb.jdbc.Driver");

			// DBへ接続
			conn = DriverManager.getConnection("jdbc:mariadb://localhost/quetana_dev", "root", "mon202");

			// SELECT文を準備
			String sql =
				"select COUNT(*) as cnt from T_USER_INFO where STACCOUNTNAME =  ?;";

			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, stAccountName);

			// SELECT文を実行し、結果(行数)をカウント
			ResultSet rs = pStmt.executeQuery();
			while(rs.next()) {
				cntRow = rs.getString("cnt");
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
		return cntRow;
	}

	/**
	 * @param stMailAddress
	 * @return cntRow or null ： countの結果(件数)、エラーの場合はnullを返す。
	 */
	public String countMailAddress(String stMailAddress) {

		Connection conn = null;
		String cntRow = null;

		try {
			// JDBCドライバを読み込み
			Class.forName("org.mariadb.jdbc.Driver");

			// DBへ接続
			conn = DriverManager.getConnection("jdbc:mariadb://localhost/quetana_dev", "root", "mon202");

			// SELECT文を準備
			String sql =
				"select COUNT(*) as cnt from T_USER_INFO where STMAILADDRESS =  ?;";

			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, stMailAddress);

			// SELECT文を実行し、結果(行数)をカウント
			ResultSet rs = pStmt.executeQuery();
			while(rs.next()) {
				cntRow = rs.getString("cnt");
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
		return cntRow;
	}

	/**
	 * ユーザ名またはメールアドレスが一致するユーザー情報を取得
	 * @param userInfoDto ：入力されたユーザ名またはメールアドレス
	 * @return arrUserInfo ：該当するユーザ情報、DB処理失敗の場合はnull
	 */
	public List<UserInfoDto> selectUserInfo(UserInfoDto userInfoDto) {

		List<UserInfoDto> arrUserInfo = new ArrayList();

		Connection conn = null;

		try {
			// JDBCドライバを読み込み
			Class.forName("org.mariadb.jdbc.Driver");

			// DBへ接続
			conn = DriverManager.getConnection("jdbc:mariadb://localhost/quetana_dev", "root", "mon202");

			// SELECT文を準備
			String sql =
					"select UI.IDUSER, UI.STACCOUNTNAME, UI.STMAILADDRESS, UI.STPASSWORD, UP.STICONURL "
					+ "from T_USER_INFO as UI, T_USER_PROFILE as UP "
					+ "where (UI.IDUSER = UP.IDUSER) and (UI.STACCOUNTNAME = ? or UI.STMAILADDRESS = ?) and UI.CFDELETE = '0';";

			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, userInfoDto.getStAccountName());
			pStmt.setString(2, userInfoDto.getStMailAddress());

			// SELECTを実行し、結果が取得できた場合DTO型のListに格納
			ResultSet rs  = pStmt.executeQuery();
			while (rs.next()) {
				UserInfoDto dto = new UserInfoDto();
				dto.setIdUser(rs.getString("UI.IDUSER"));
				dto.setStAccountName(rs.getString("UI.STACCOUNTNAME"));
				dto.setStMailAddress(rs.getString("UI.STMAILADDRESS"));
				dto.setStPassword(rs.getString("UI.STPASSWORD"));
				dto.setStIconURL(rs.getString("UP.STICONURL"));
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
