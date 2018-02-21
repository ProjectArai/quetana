package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import model.dto.MemberRecruitDto;

public class T_MEMBER_RECRUIT_DAO {

	/**
	 * T_MEMBER_RECRUITへINSERT
	 * @param timeLineDto  ：T_MEMBER_RECRUITのDTO
	 * @return rtnExecute  ：実行件数、DB処理失敗の場合null
	 */
	public String insertMemberRecruit(MemberRecruitDto memberRecruitDto) {

		int rowExecute = 0;
		String rtnExecute = null;

		Connection conn = null;

		try {
			// JDBCドライバを読み込み
			Class.forName("org.mariadb.jdbc.Driver");

			// DBへ接続
			conn = DriverManager.getConnection("jdbc:mariadb://localhost/quetana_dev", "root", "mon202");

			// SELECT文を準備
			String sql =
					"insert into T_MEMBER_RECRUIT(IDPOST, IDUSER, STTITLE, STPART, STGENRE, STDETAILS, CFDELETE, DTUPDATE, DTRESIST) "
						+ "values (?, ?, ?, ?, ?, ?, '0', now(), now()); ";

			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, memberRecruitDto.getIdPost());
			pStmt.setString(2, memberRecruitDto.getIdUser());
			pStmt.setString(3, memberRecruitDto.getStTitle());
			pStmt.setString(4, memberRecruitDto.getStPart());
			pStmt.setString(5, memberRecruitDto.getStGenre());
			pStmt.setString(6, memberRecruitDto.getStDetails());

			// INSERTを実行し、実行結果をrowExecuteに格納
			rowExecute = pStmt.executeUpdate();

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

		// 実行件数をString型にキャストしてreturn
		rtnExecute = String.valueOf(rowExecute);
		return rtnExecute;
	}
}
