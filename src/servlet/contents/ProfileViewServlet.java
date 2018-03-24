package servlet.contents;

import java.io.IOException;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.bean.LoginUserInfoBean;
import model.bean.UserProfileBean;
import model.logic.ProfileViewLogic;

/**
 * Servlet implementation class HomeServlet
 */
@WebServlet("/Contents/ProfileView")
public class ProfileViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProfileViewServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());

		// セッションスコープからログインユーザ情報(のID)を取得
		HttpSession session = request.getSession();
		LoginUserInfoBean loginUserInfo = (LoginUserInfoBean)session.getAttribute("loginUserInfo");
		String idLoginUser = loginUserInfo.getIdUser();

		// プロフィールを表示するユーザを設定
		String idUser;
		String perEdit = "N";
		if (request.getParameter("idUser") == null) {
			// idUser指定なし(MENUから遷移)の場合＝ログインユーザ本人
			idUser = idLoginUser;
		} else {
			idUser = request.getParameter("idUser");
		}

		// ログインユーザ本人の場合は編集可の権限を付与
		if (idUser.equals(idLoginUser)) {
			perEdit = "Y";
		}

		//ユーザIDを基にプロフィールを取得
		Map result = ProfileViewLogic.getUserProfile(idUser);

		String errMsg = (String)result.get("errMsg");

		if (errMsg.equals("")) {
			//プロフィール取得に成功した場合
			//ユーザプロフィールをリクエストスコープに保存
			UserProfileBean userProfile = (UserProfileBean)result.get("userProfile");
			request.setAttribute("userProfile", userProfile);
			//プロフィール編集権限をリクエストスコープに保存
			request.setAttribute("perEdit", perEdit);
			// profileView.jspにフォワード
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/profileView.jsp");
			dispatcher.forward(request, response);
		} else {
			//プロフィール取得に失敗した場合
			// ★エラー画面でエラーメッセージを表示？
			request.setAttribute("errMsg", errMsg);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/error.jsp");
			dispatcher.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// 各textareのheight情報をSessionスコープに保存
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		session.setAttribute("arrHeight", request.getParameter("arrHeight"));

		// プロフィール編集画面に遷移（/ProfileEditにリダイレクト）
		response.sendRedirect("/quetana/Contents/ProfileEdit");
		}
}
