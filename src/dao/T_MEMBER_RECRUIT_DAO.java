package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.dto.MemberRecruitDto;
import model.dto.TimeLineDto;

public class T_MEMBER_RECRUIT_DAO {

	/**
	 * T_MEMBER_RECRUITへINSERT
	 * @param dtoMR  ：T_MEMBER_RECRUITのDTO
	 * @return rowExec  ：実行件数、DB処理失敗の場合2
	 */
	public int insertMemberRecruit(MemberRecruitDto dtoMR) {

		int rowExec = 0;

		Connection conn = null;

		try {
			// JDBCドライバを読み込み
			Class.forName("org.mariadb.jdbc.Driver");

			// DBへ接続
			conn = DriverManager.getConnection("jdbc:mariadb://localhost/quetana_dev", "root", "mon202");

			// SELECT文を準備
			String sql =
					"insert into T_MEMBER_RECRUIT(IDPOST, IDUSER, STTITLE, STPART, STGENRE, STDETAILS, CFDELETE, DTUPDATE, DTRESIST) "
						+ "values (?, ?, ?, ?, ?, ?, ?, now(), now()); ";

			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, dtoMR.getIdPost());
			pStmt.setString(2, dtoMR.getIdUser());
			pStmt.setString(3, dtoMR.getStTitle());
			pStmt.setString(4, dtoMR.getStPart());
			pStmt.setString(5, dtoMR.getStGenre());
			pStmt.setString(6, dtoMR.getStDetails());
			pStmt.setString(7, dtoMR.getCfDelete());

			// INSERTを実行し、実行結果をrowExecuteに格納
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

	public List<TimeLineDto> selectMemberRecruit(String idPost) {

		List<TimeLineDto> arrMRInfo = new ArrayList();

		Connection conn = null;

		try {
			// JDBCドライバを読み込み
			Class.forName("org.mariadb.jdbc.Driver");

			// DBへ接続
			conn = DriverManager.getConnection("jdbc:mariadb://localhost/quetana_dev", "root", "mon202");

			// SELECT文を準備
			String sql =
					"select * from ("
							+ "(select IDPOST, IDUSER, STTITLE, STPART, STGENRE, STDETAILS, CFDELETE, DTUPDATE, DTRESIST from T_MEMBER_RECRUIT where IDPOST= ? AND CFDELETE = false) "
						+ ") TM "
						+ "left join ("
							+ "select IDUSER, STACCOUNTNAME, STDISPLAYNAME, STICONURL from T_USER_PROFILE"
						+ ") UP "
						+ "on TM.IDUSER = UP.IDUSER;";

			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, idPost);

			// SELECTを実行し、結果が取得できた場合DTO型のListに格納
			ResultSet rs  = pStmt.executeQuery();
			while (rs.next()) {
				TimeLineDto dto = new TimeLineDto();
				dto.setIdUser(rs.getString("IDUSER"));
				dto.setIdPost(rs.getString("IDPOST"));
				dto.setStTitle(rs.getString("STTITLE"));
				dto.setStPart(rs.getString("STPART"));
				dto.setStGenre(rs.getString("STGENRE"));
				dto.setStDetails(rs.getString("STDETAILS"));
				dto.setCfDelete(rs.getString("CFDELETE"));
				dto.setDtUpdate(rs.getDate("DTUPDATE"));
				dto.setDtResist(rs.getDate("DTRESIST"));
				dto.setStAccountName(rs.getString("STACCOUNTNAME"));
				dto.setStDisplayName(rs.getString("STDISPLAYNAME"));
				dto.setStIconURL(rs.getString("STICONURL"));

				arrMRInfo.add(dto);
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
		return arrMRInfo;
	}

	/**
	 * 投稿IDをWHERE句に指定し、T_MEMBER_RECRUITを更新
	 * @param dtoMR  ：T_MEMBER_RECRUITのDTO
	 * @return rowExec  ：実行件数 0:更新なし？、1:更新成功、2:DB処理失敗
	 */
	public int updateMemberRecruit(MemberRecruitDto dtoMR) {

		int rowExec = 0;

		Connection conn = null;

		try {
			// JDBCドライバを読み込み
			Class.forName("org.mariadb.jdbc.Driver");

			// DBへ接続
			conn = DriverManager.getConnection("jdbc:mariadb://localhost/quetana_dev", "root", "mon202");

			// SELECT文を準備
			String sql =
					"UPDATE T_MEMBER_RECRUIT"
							+ " SET"
							+ " STTITLE = ?"
							+ " , STPART = ?"
							+ " , STGENRE = ?"
							+ " , STDETAILS = ?"
							+ " , DTUPDATE = now()"
							+ " WHERE"
							+ " IDPOST = ?"
							+ ";";

			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, dtoMR.getStTitle());
			pStmt.setString(2, dtoMR.getStPart());
			pStmt.setString(3, dtoMR.getStGenre());
			pStmt.setString(4, dtoMR.getStDetails());
			pStmt.setString(5, dtoMR.getIdPost());

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
