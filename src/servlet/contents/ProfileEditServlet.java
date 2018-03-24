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

import model.bean.LoginUserInfoBean;
import model.bean.UserProfileBean;
import model.logic.ProfileEditLogic;

/**
 * Servlet implementation class HomeServlet
 */
@WebServlet("/Contents/ProfileEdit")
@MultipartConfig
public class ProfileEditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProfileEditServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());

		// 戻り先には修正元のプロフィール表示画面を設定
		String stReturnURL = "/quetana/Contents/ProfileView";

		// セッションスコープからログインユーザ情報を取得
		HttpSession session = request.getSession();
		LoginUserInfoBean loginUserInfo = (LoginUserInfoBean)session.getAttribute("loginUserInfo");
		String idUser = loginUserInfo.getIdUser();

		String stHeightArray = (String)session.getAttribute("arrHeight");
		// sessionスコープに残った要素は不要なので破棄
		session.removeAttribute("arrHeight");

		// ユーザIDを基にプロフィールを取得
		Map result = ProfileEditLogic.getUserProfile(idUser);

		String errMsg = (String)result.get("errMsg");

		if (errMsg.equals("")) {
			// プロフィール取得に成功した場合
			// profileEdit.jspにフォワード
			UserProfileBean userProfile = (UserProfileBean)result.get("userProfile");
			request.setAttribute("userProfile", userProfile);
			request.setAttribute("stHeightArray", stHeightArray); // 投稿画面のtextareaの初期heightを設定
			request.setAttribute("stReturnURL", stReturnURL); // 編集終了時の戻り先を設定
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/profileEdit.jsp");
			dispatcher.forward(request, response);
		} else {
			// プロフィール取得に失敗した場合
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
		// 選択したpartはString型配列で来るので、対応するコード(カンマ区切り)の文字列へ変換
		String[] arrChkPart = request.getParameterValues("chkPart");
		String stPart = "";
		if (arrChkPart != null) {
			for (int i = 0 ; i < arrChkPart.length ; i++) {
				stPart += arrChkPart[i];
				if (i < (arrChkPart.length - 1)) {
					stPart += ",";
				}
			}
		}
		inParam.put("stPart", stPart);
		inParam.put("stBand", request.getParameter("stBand"));
		inParam.put("stGenre", request.getParameter("stGenre"));
		inParam.put("stIconURL_org", request.getParameter("stIconURL_org"));
		inParam.put("imgIcon", request.getPart("imgIcon"));
		inParam.put("stImgSavePath", getServletContext().getRealPath("/img"));
		inParam.put("stVideoURL", request.getParameter("stVideoURL"));
		inParam.put("stIntroduction", request.getParameter("stIntroduction"));
		inParam.put("loginUserInfo", loginUserInfo);

		// 戻り先には修正元のプロフィール表示画面を設定
		String stReturnURL = "/quetana/Contents/ProfileView";

		// 更新処理の実行
		Map resultUpdate = ProfileEditLogic.editUserProfile(inParam);
		Boolean cfIconUpdate = (Boolean)resultUpdate.get("cfIconUpdate");
		String errMsg = (String)resultUpdate.get("errMsg");

		// 更新処理の結果で分岐
		if (errMsg.equals("")) {
			// DB更新処理が成功した場合
			if (cfIconUpdate) {
				// アイコンを更新している場合、セッションのユーザ情報を更新
				// ★エラーの場合のとか考えなきゃ
//				Map resultLUUpdate = ProfileEditLogic.updateLoginUserInfo(loginUserInfo.getIdUser());
//				loginUserInfo = (LoginUserInfoBean)resultLUUpdate.get("loginUserInfo");
				session.setAttribute("loginUserInfo", loginUserInfo);
			}
			// 戻り先にリダイレクト
			response.sendRedirect(stReturnURL);
		} else {
			// DB更新処理が失敗した場合
			// errMsgとinParamを持たせてprofileEdit.jspにフォワード
			request.setAttribute("errMsg", errMsg);
			request.setAttribute("userProfile", (UserProfileBean)resultUpdate.get("userProfile"));
			request.setAttribute("stHeightArray", request.getParameter("arrHeight")); // textareaの初期heightを設定
			request.setAttribute("stReturnURL", stReturnURL); // 編集終了時の戻り先を設定
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/profileEdit.jsp");
			dispatcher.forward(request, response);
		}
	}
}
