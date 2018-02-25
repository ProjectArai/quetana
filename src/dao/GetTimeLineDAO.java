package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.dto.TimeLineDto;

public class GetTimeLineDAO {

	public List<TimeLineDto> selectUnionTimeLine() {

		List<TimeLineDto> arrTimeLine = new ArrayList();

		Connection conn = null;

		try {
			// JDBCドライバを読み込み
			Class.forName("org.mariadb.jdbc.Driver");

			// DBへ接続
			conn = DriverManager.getConnection("jdbc:mariadb://localhost/quetana_dev", "root", "mon202");

			// SELECT文を準備
			// ①T_MEMBER_RECRUITから更新日付が新しい順に5件を選択したものと、
			//   T_EVENT_ANNOUNCEから更新日付が新しい順に5件を選択したものを
			//   UNIONし、更新日付が新しい順に並べ替えて「V1」テーブルとする。
			// ②T_USER_PROFILEからIDUSER, STACCOUNTNAME, STICONURLを射影し、
			//   「V2」テーブルとする。
			// ③「V1」テーブルと「V2」テーブルをIDUSERでJOINして紐づける。
			String sql =
					"select * from ("
						+ "(select IDPOST, IDUSER, STTITLE, STPART, STGENRE, NULL as STPLACE, NULL as DTEVENT, STDETAILS, CFDELETE, DTUPDATE, DTRESIST from T_MEMBER_RECRUIT where CFDELETE = false order by DTUPDATE desc limit 5) "
						+ "union "
						+ "(select IDPOST, IDUSER, STTITLE, NULL as STPART, NULL as STGENRE, STPLACE, DTEVENT, STDETAILS, CFDELETE, DTUPDATE, DTRESIST from T_EVENT_ANNOUNCE where CFDELETE = false order by DTUPDATE desc limit 5) "
						+ "order by DTUPDATE desc"
					+ ") V1 "
					+ "join ("
						+ "select IDUSER, STACCOUNTNAME, STDISPLAYNAME, STICONURL from T_USER_PROFILE"
					+ ") V2 "
					+ "on V1.IDUSER = V2.IDUSER;";

			PreparedStatement pStmt = conn.prepareStatement(sql);

			// SELECTを実行し、結果が取得できた場合DTO型のListに格納
			ResultSet rs  = pStmt.executeQuery();
			while (rs.next()) {
				TimeLineDto dto = new TimeLineDto();
				dto.setIdPost(rs.getString("IDPOST"));
				dto.setIdUser(rs.getString("IDUSER"));
				dto.setStAccountName(rs.getString("STACCOUNTNAME"));
				dto.setStDisplayName(rs.getString("STDISPLAYNAME"));
				dto.setStIconURL(rs.getString("STICONURL"));
				dto.setStTitle(rs.getString("STTITLE"));
				dto.setStPart(rs.getString("STPART"));
				dto.setStGenre(rs.getString("STGENRE"));
				dto.setStPlace(rs.getString("STPLACE"));
				dto.setDtEvent(rs.getDate("DTEVENT"));
				dto.setStDetails(rs.getString("STDETAILS"));
				dto.setDtUpdate(rs.getDate("DTUPDATE"));
				dto.setDtResist(rs.getDate("DTRESIST"));

				arrTimeLine.add(dto);
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
		return arrTimeLine;
	}

}