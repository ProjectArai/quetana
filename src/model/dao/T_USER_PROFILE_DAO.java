package model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.dto.UserProfileDto;


public class T_USER_PROFILE_DAO {

	/**
	 * ユーザIDが一致するユーザプロフィールを取得
	 * @param idUser ：ユーザID
	 * @return arrUserProfile ：該当するユーザプロフィール、DB処理失敗の場合はnull
	 */
	public List<UserProfileDto> selectUserProfile(UserProfileDto dtoUP) {

		List<UserProfileDto> arrUserProfile = new ArrayList();

		Connection conn = null;

		try {
			// JDBCドライバを読み込み
			Class.forName("org.mariadb.jdbc.Driver");

			// DBへ接続
			conn = DriverManager.getConnection("jdbc:mariadb://localhost/quetana_dev", "root", "mon202");

			// SELECT文を準備
			String sql =
					"select IDUSER, STACCOUNTNAME, STDISPLAYNAME, NMAGE, NMADDYEAR, STPART, STFBAND, STFGENRE, STICONURL, STVIDEOURL, STCOMMENT, CFDELETE, DTUPDATE, DTRESIST from T_USER_PROFILE"
					+ " where IDUSER = ?;";

			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, dtoUP.getIdUser());

			// SELECTを実行し、結果が取得できた場合DTO型のListに格納
			ResultSet rs  = pStmt.executeQuery();
			while (rs.next()) {
				UserProfileDto dto = new UserProfileDto();
				dto.setIdUser(rs.getString("IDUSER"));
				dto.setStAccountName(rs.getString("STACCOUNTNAME"));
				dto.setStDisplayName(rs.getString("STDISPLAYNAME"));
				dto.setNmAge(rs.getString("NMAGE"));
				dto.setNmAddYear(rs.getString("NMADDYEAR"));
				dto.setStPart(rs.getString("STPART"));
				dto.setStFBand(rs.getString("STFBAND"));
				dto.setStFGenre(rs.getString("STFGENRE"));
				dto.setStIconURL(rs.getString("STICONURL"));
				dto.setStVideoURL(rs.getString("STVIDEOURL"));
				dto.setStComment(rs.getString("STCOMMENT"));
				dto.setCfDelete(rs.getString("CFDELETE"));
				dto.setDtUpdate(rs.getDate("DTUPDATE"));
				dto.setDtResist(rs.getDate("DTRESIST"));
				arrUserProfile.add(dto);
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
		return arrUserProfile;
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
				"select COUNT(*) as cnt from T_USER_PROFILE where STACCOUNTNAME =  ?;";

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
	 * ユーザIDをWHERE句に指定し、T_USER_PROFILEを更新
	 * @param dtoUP  ：T_USER_PROFILEのDTO
	 * @return rtnExec  ：実行件数、DB処理失敗の場合null
	 */
	public int insertUserProfile(UserProfileDto dtoUP) {

		int rowExec = 0;

		List<UserProfileDto> arrUserProfile = new ArrayList();

		Connection conn = null;

		try {
			// JDBCドライバを読み込み
			Class.forName("org.mariadb.jdbc.Driver");

			// DBへ接続
			conn = DriverManager.getConnection("jdbc:mariadb://localhost/quetana_dev", "root", "mon202");

			// SELECT文を準備
			String sql =
					"insert into T_USER_PROFILE "
							+ "(IDUSER, STACCOUNTNAME, STDISPLAYNAME, STICONURL, CFDELETE, DTUPDATE, DTRESIST) "
							+ "values (?, ?, ?, ?, ?, now(), now());";

			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, dtoUP.getIdUser());
			pStmt.setString(2, dtoUP.getStAccountName());
			pStmt.setString(3, dtoUP.getStDisplayName());
			pStmt.setString(4, dtoUP.getStIconURL());
			pStmt.setString(5, dtoUP.getCfDelete());

			// INSERTを実行し、実行結果をrowExecに格納
			rowExec = pStmt.executeUpdate();

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

		return rowExec;
	}

	/**
	 * ユーザIDをWHERE句に指定し、T_USER_PROFILEを更新
	 * @param dtoUP  ：T_USER_PROFILEのDTO
	 * @return rowExec  ：実行件数 0:更新なし？、1:更新成功、2:DB処理失敗
	 */
	public int updateUserProfile(UserProfileDto dtoUP) {

		int rowExec = 0;

		Connection conn = null;

		try {
			// JDBCドライバを読み込み
			Class.forName("org.mariadb.jdbc.Driver");

			// DBへ接続
			conn = DriverManager.getConnection("jdbc:mariadb://localhost/quetana_dev", "root", "mon202");

			// SELECT文を準備
			String sql =
					"UPDATE T_USER_PROFILE"
							+ " SET"
							+ " STDISPLAYNAME = ?"
							+ " , NMAGE = ?"
							+ " , NMADDYEAR = ?"
							+ " , STPART = ?"
							+ " , STFBAND = ?"
							+ " , STFGENRE = ?"
							+ " , STICONURL = ?"
							+ " , STVIDEOURL = ?"
							+ " , STCOMMENT = ?"
							+ " , DTUPDATE = now()"
							+ " WHERE"
							+ " IDUSER = ?"
							+ ";";

			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, dtoUP.getStDisplayName());
			pStmt.setString(2, dtoUP.getNmAge());
			pStmt.setString(3, dtoUP.getNmAddYear());
			pStmt.setString(4, dtoUP.getStPart());
			pStmt.setString(5, dtoUP.getStFBand());
			pStmt.setString(6, dtoUP.getStFGenre());
			pStmt.setString(7, dtoUP.getStIconURL());
			pStmt.setString(8, dtoUP.getStVideoURL());
			pStmt.setString(9, dtoUP.getStComment());
			pStmt.setString(10, dtoUP.getIdUser());

			// UPDATEを実行し、実行結果をrowExecに格納
			rowExec = pStmt.executeUpdate();

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
		return rowExec;
	}
}
