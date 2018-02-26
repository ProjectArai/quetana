package servlet.contents;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.EditUserProfile;
import model.LoginUserInfoBean;
import model.UserProfileBean;
import model.ViewUserProfile;

/**
 * Servlet implementation class HomeServlet
 */
@WebServlet("/Contents/EditProfile")
public class EditProfileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditProfileServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());

		// セッションスコープからログインユーザ情報を取得
		HttpSession session = request.getSession();
		LoginUserInfoBean loginUserInfo = (LoginUserInfoBean)session.getAttribute("loginUserInfo");
		String idUser = loginUserInfo.getIdUser();

		//ユーザIDを基にプロフィールを取得
		Map result = ViewUserProfile.getUserProfile(idUser);

		String errMsg = (String)result.get("errMsg");

		if (errMsg.equals("")) {
			//プロフィール取得に成功した場合
			//ユーザプロフィールをリクエストスコープに保存
			UserProfileBean myProfile = (UserProfileBean)result.get("userProfile");
			request.setAttribute("myProfile", myProfile);
			// editProfile.jspにフォワード
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/editProfile.jsp");
			dispatcher.forward(request, response);
		} else {
			// ★ホーム画面でエラーメッセージを表示？
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/editProfile.jsp");
			dispatcher.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// セッションスコープからログインユーザ情報を取得
		HttpSession session = request.getSession();
		LoginUserInfoBean loginUserInfo = (LoginUserInfoBean)session.getAttribute("loginUserInfo");
		//リクエストパラメータを取得し、マップに詰め込む
		request.setCharacterEncoding("UTF-8");
		Map inParam = new HashMap();
		inParam.put("idUser", loginUserInfo.getIdUser());
		inParam.put("stDisplayName", request.getParameter("stDisplayName"));
		inParam.put("nmAge", request.getParameter("nmAge"));
		inParam.put("nmAddYear", request.getParameter("nmAddYear"));
		inParam.put("stPart", request.getParameter("stPart"));
		inParam.put("stFBand", request.getParameter("stFBand"));
		inParam.put("stFGenre", request.getParameter("stFGenre"));
		inParam.put("stIconURL", "/quetana/img/r-zoon.png"); //一旦デフォルトアイコン
		inParam.put("stVideoURL", request.getParameter("stVideoURL"));
		inParam.put("stComment", request.getParameter("stComment"));

		// 更新処理の実行
		Map resultUpdate = EditUserProfile.editUserProfile(inParam);

		String errMsg = (String)resultUpdate.get("errMsg");

		if (errMsg.equals("")) {
			// 更新処理が成功した場合
			// プロフィール画面を表示（/UserProfileにリダイレクト）
			response.sendRedirect("/quetana/Contents/UserProfile");
		} else {
			// ログインユーザ情報の判定が否の場合
			// 入力情報を格納（★とりあえず初期表示時にmyProfileって入れ物使ったので使いまわし
			UserProfileBean myProfile = new UserProfileBean();
			myProfile.setIdUser(loginUserInfo.getIdUser());
			myProfile.setStAccountName(loginUserInfo.getStAccountName());
			myProfile.setStDisplayName((String)inParam.get("stDisplayName"));
			myProfile.setNmAge((String)inParam.get("nmAge"));
			myProfile.setNmAddYear((String)inParam.get("nmAddYear"));
			myProfile.setStPart((String)inParam.get("stPart"));
			myProfile.setStFBand((String)inParam.get("stFBand"));
			myProfile.setStFGenre((String)inParam.get("stFGenre"));
			myProfile.setStIconURL("/quetana/img/r-zoon.png");
			myProfile.setStVideoURL((String)inParam.get("stVideoURL"));
			myProfile.setStComment((String)inParam.get("stComment"));
			// errMsgとinParamを持たせてeditProfile.jspにフォワード
			request.setAttribute("myProfile", myProfile);
			request.setAttribute("errMsg", errMsg);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/editProfile.jsp");
			dispatcher.forward(request, response);
		}
	}
}
