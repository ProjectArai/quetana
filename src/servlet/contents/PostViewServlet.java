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
import model.PostLogic;
import model.bean.PostViewBean;
import model.logic.PostViewLogic;

/**
 * Servlet implementation class HomeServlet
 */
@WebServlet("/Contents/PostView")
public class PostViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public PostViewServlet() {
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
		String perEdit = "N";

		String idPost = request.getParameter("idPost");

		// 投稿IDを基に投稿内容を取得
		Map result = PostViewLogic.getPostInfo(idPost);
		String errMsg = (String)result.get("errMsg");

		if (errMsg.equals("")) {
			// 投稿内容の取得に成功した場合
			// 投稿内容をリクエストスコープに保存
			PostViewBean beanPV = (PostViewBean)result.get("beanPV");

			// ログインユーザ＝投稿者本人の場合は編集可の権限を付与
			if (idLoginUser.equals(beanPV.getIdUser())) {
				perEdit = "Y";
			}

			request.setAttribute("beanPV", beanPV);
			//投稿内容修正権限をリクエストスコープに保存
			request.setAttribute("perEdit", perEdit);
			// postView.jspにフォワード
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/postView.jsp");
			dispatcher.forward(request, response);
		} else {
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
		inParam.put("cfPost", request.getParameter("cfPost"));
		inParam.put("stTitle", request.getParameter("stTitle"));
		inParam.put("stPart", stPart);
		inParam.put("stGenre", request.getParameter("stGenre"));
		inParam.put("stPlace", request.getParameter("stPlace"));
		inParam.put("dtEvent", request.getParameter("dtEvent"));
		inParam.put("stDetails", request.getParameter("stDetails"));

		// 投稿内容のDB登録処理の実行
		Map resultPost = PostLogic.postTimeLine(inParam);
		String errMsg = (String)resultPost.get("errMsg");

		// 投稿処理の結果で分岐
		if (errMsg.equals("")) {
			// 成功した場合、ホーム画面を表示（/Homeにリダイレクト）
			response.sendRedirect("/quetana/Contents/Home");
		} else {
			// 失敗した場合、errMsgとinParamを持たせて、post.jspにフォワード
			request.setAttribute("errMsg", errMsg);
			request.setAttribute("inParam", inParam);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/post.jsp");
			dispatcher.forward(request, response);
		}
	}
}
