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

import model.LoginUserInfoBean;
import model.PostSendLogic;
import model.bean.PostViewBean;

/**
 * Servlet implementation class HomeServlet
 */
@WebServlet("/Contents/PostSend")
public class PostSendServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public PostSendServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());

		String cfPostMode;
		String stReturnURL = "";
		PostViewBean beanPV = new PostViewBean();

		HttpSession session = request.getSession();
		String idPost = (String)session.getAttribute("idPost");
		String stHeightArray = "";


		// sessionスコープにidPostの指定があるか判定
		if (idPost == null) {
			// idPost指定なし＝新規投稿
			cfPostMode = "N";
			stReturnURL = "/quetana/Contents/Home"; // 編集終了時の戻り先はホーム画面
			stHeightArray = "stTitle=30,stGenre=30,stPlace=30,stDetails=184"; // 新規投稿用のtextareaの初期height★ここに定義はダメな希ガス

			beanPV = PostSendLogic.setNewPostParam(); // 新規投稿用(基本空)のbean作成

		} else {
			// idPost指定あり＝投稿内容修正
			cfPostMode = "F";
			stReturnURL = "/quetana/Contents/PostView?idPost=" + idPost; // 編集終了時の戻り先は該当IDの投稿内容表示画面
			stHeightArray = (String)session.getAttribute("arrHeight");

			//sessionスコープに残った要素は不要なので破棄
			session.removeAttribute("stHeightArray");
			session.removeAttribute("idPost");

			// 投稿IDを基に投稿内容を取得
			Map result = PostSendLogic.getPostInfo(idPost);
			String errMsg = (String)result.get("errMsg");
			if (errMsg.equals("")) {
				// 投稿内容の取得に成功した場合、画面表示する投稿内容を設定
				beanPV = (PostViewBean)result.get("beanPV");

			} else {
				// 投稿内容の取得に失敗した場合、即エラー画面に遷移
				request.setAttribute("errMsg", errMsg);
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/error.jsp");
				dispatcher.forward(request, response);
			}
		}

		// postSend.jspにフォワード
		request.setAttribute("cfPostMode", cfPostMode); // 投稿モードをリクエストスコープに保存
		request.setAttribute("beanPV", beanPV); // 投稿内容をリクエストスコープに保存
		request.setAttribute("stHeightArray", stHeightArray); // 投稿画面のtextareaの初期heightを設定
		request.setAttribute("stReturnURL", stReturnURL); // 編集終了時の戻り先を設定
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/postSend.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// セッションスコープからユーザ情報を取得
		HttpSession session = request.getSession();
		LoginUserInfoBean loginUserInfo = (LoginUserInfoBean)session.getAttribute("loginUserInfo");

		// リクエストパラメータを取得
		request.setCharacterEncoding("UTF-8");

		// partで選択したチェックボックスはString型配列で来るらしい★
		String[] test = request.getParameterValues("part");
		String stPart = "";
		for (int i = 0 ; i < test.length ; i++){
			stPart += test[i];
			stPart += ",";
		}

		// 投稿情報をマップに詰め込む
		Map inParam = new HashMap();
		inParam.put("idUser", loginUserInfo.getIdUser());
		inParam.put("cfPostType", request.getParameter("cfPostType"));
		inParam.put("stTitle", request.getParameter("stTitle"));
		inParam.put("stPart", stPart);
		inParam.put("stGenre", request.getParameter("stGenre"));
		inParam.put("stPlace", request.getParameter("stPlace"));
		inParam.put("dtEvent", request.getParameter("dtEvent"));
		inParam.put("stDetails", request.getParameter("stDetails"));

		// 投稿内容のDB登録処理の実行
		Map resultPost = PostSendLogic.insertPostInfo(inParam);
		String errMsg = (String)resultPost.get("errMsg");

		// 投稿処理の結果で分岐
		if (errMsg.equals("")) {
			// 成功した場合、ホーム画面を表示（/Homeにリダイレクト）
			response.sendRedirect("/quetana/Contents/Home");
		} else {
			// 失敗した場合、errMsgとinParamを持たせて、postSend.jspにフォワード
			request.setAttribute("errMsg", errMsg);
			request.setAttribute("inParam", inParam);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/postSend.jsp");
			dispatcher.forward(request, response);
		}
	}
}
