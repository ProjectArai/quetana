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
	 * @param memberRecruitDto  ：T_MEMBER_RECRUITのDTO
	 * @return rowExecute  ：実行件数、DB処理失敗の場合2
	 */
	public int insertMemberRecruit(MemberRecruitDto memberRecruitDto) {

		int rowExecute = 0;

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
			pStmt.setString(1, memberRecruitDto.getIdPost());
			pStmt.setString(2, memberRecruitDto.getIdUser());
			pStmt.setString(3, memberRecruitDto.getStTitle());
			pStmt.setString(4, memberRecruitDto.getStPart());
			pStmt.setString(5, memberRecruitDto.getStGenre());
			pStmt.setString(6, memberRecruitDto.getStDetails());
			pStmt.setString(7, memberRecruitDto.getCfDelete());

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
}
