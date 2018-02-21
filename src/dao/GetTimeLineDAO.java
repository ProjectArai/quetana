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
			// T_MEMBER_RECRUITから更新日付が新しい順に5件を選択したものと、
			// T_EVENT_ANNOUNCEから更新日付が新しい順に5件を選択したものを
			// 結合し、更新日付が新しい順に並べ替えるSQL文
			String sql =
					"(select IDPOST, IDUSER, STTITLE, STPART, STGENRE, NULL as STPLACE, NULL as DTEVENT, STDETAILS, CFDELETE, DTUPDATE, DTRESIST from T_MEMBER_RECRUIT order by DTUPDATE desc limit 5) "
					+ "union "
					+ "(select IDPOST, IDUSER, STTITLE, NULL as STPART, NULL as STGENRE, STPLACE, DTEVENT, STDETAILS, CFDELETE, DTUPDATE, DTRESIST from T_EVENT_ANNOUNCE order by DTUPDATE desc limit 5) "
					+ "order by DTUPDATE desc;";

			PreparedStatement pStmt = conn.prepareStatement(sql);

			// SELECTを実行し、結果が取得できた場合DTO型のListに格納
			ResultSet rs  = pStmt.executeQuery();
			while (rs.next()) {
				TimeLineDto dto = new TimeLineDto();
				dto.setIdPost(rs.getString("IDPOST"));
				dto.setIdUser(rs.getString("IDUSER"));
				dto.setStTitle(rs.getString("STTITLE"));
				dto.setStPart(rs.getString("STPART"));
				dto.setStGenre(rs.getString("STGENRE"));
				dto.setStPlace(rs.getString("STPLACE"));
				dto.setDtEvent(rs.getDate("DTEVENT"));
				dto.setStDetails(rs.getString("STDETAILS"));
				dto.setDtUpdate(rs.getDate("DTUPDATE"));
				dto.setDtResist(rs.getDate("DTRESIT"));

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