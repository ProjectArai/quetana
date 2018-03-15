package servlet.contents;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
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
@MultipartConfig
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
			UserProfileBean userProfile = (UserProfileBean)result.get("userProfile");
			request.setAttribute("userProfile", userProfile);
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

		//リクエストパラメータを取得し、更新処理に使用するマップに詰め込む
		request.setCharacterEncoding("UTF-8");
		Map inParam = new HashMap();
		inParam.put("idUser", loginUserInfo.getIdUser());
		inParam.put("stDisplayName", request.getParameter("stDisplayName"));
		inParam.put("nmAge", request.getParameter("nmAge"));
		inParam.put("nmAddYear", request.getParameter("nmAddYear"));
		inParam.put("stPart", request.getParameter("stPart"));
		inParam.put("stFBand", request.getParameter("stBand"));
		inParam.put("stFGenre", request.getParameter("stGenre"));
		inParam.put("stIconURL_org", request.getParameter("stIconURL"));
		inParam.put("imgIcon", request.getPart("imgIcon"));
		inParam.put("stImgSavePath", getServletContext().getRealPath("/img"));
		inParam.put("stVideoURL", request.getParameter("stVideoURL"));
		inParam.put("stComment", request.getParameter("stIntroduction"));
		inParam.put("part", request.getParameterValues("part"));

		// partで選択したチェックボックスはString型配列で来るらしい★
		String[] test = request.getParameterValues("part");

		// 更新処理の実行
		Map resultUpdate = EditUserProfile.editUserProfile(inParam);
		Boolean cfIconUpdate = (Boolean)resultUpdate.get("cfIconUpdate");
		String errMsg = (String)resultUpdate.get("errMsg");

		if (errMsg.equals("")) {
			// DB更新処理が成功した場合
			if (cfIconUpdate) {
				// アイコンを更新した場合、セッションのユーザ情報を更新
				// ★エラーの場合のとか考えなきゃ
				Map resultLUUpdate = EditUserProfile.updateLoginUserInfo(loginUserInfo.getIdUser());
				loginUserInfo = (LoginUserInfoBean)resultLUUpdate.get("loginUserInfo");
				session.setAttribute("loginUserInfo", loginUserInfo);
			}
			// プロフィール画面を表示（/UserProfileにリダイレクト）
			response.sendRedirect("/quetana/Contents/UserProfile");
		} else {
			// DB更新処理が失敗した場合
			// 入力情報を格納（★とりあえず初期表示時にuserProfileって入れ物使ったので使いまわし
			UserProfileBean userProfile = new UserProfileBean();
			userProfile.setIdUser(loginUserInfo.getIdUser());
			userProfile.setStAccountName(loginUserInfo.getStAccountName());
			userProfile.setStDisplayName((String)inParam.get("stDisplayName"));
			userProfile.setNmAge((String)inParam.get("nmAge"));
			userProfile.setNmAddYear((String)inParam.get("nmAddYear"));
			userProfile.setStPart((String)inParam.get("stPart"));
			userProfile.setStFBand((String)inParam.get("stBand"));
			userProfile.setStFGenre((String)inParam.get("stGenre"));
			userProfile.setStIconURL((String)inParam.get("stIconURL_org"));
			userProfile.setStVideoURL((String)inParam.get("stVideoURL"));
			userProfile.setStComment((String)inParam.get("stIntroduction"));
			// errMsgとinParamを持たせてeditProfile.jspにフォワード
			request.setAttribute("userProfile", userProfile);
			request.setAttribute("errMsg", errMsg);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/editProfile.jsp");
			dispatcher.forward(request, response);
		}
	}
}
