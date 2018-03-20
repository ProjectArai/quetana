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

import model.LoginUserInfoBean;
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

		String idPost = request.getParameter("idPost");

		// 投稿IDを基に投稿内容を取得
		Map result = PostViewLogic.getPostInfo(idPost);
		String errMsg = (String)result.get("errMsg");

		if (errMsg.equals("")) {
			// 投稿内容の取得に成功した場合
			PostViewBean beanPV = (PostViewBean)result.get("beanPV");

			String perEdit = "N";
			// セッションスコープからログインユーザ情報(のID)を取得
			HttpSession session = request.getSession();
			LoginUserInfoBean loginUserInfo = (LoginUserInfoBean)session.getAttribute("loginUserInfo");
			String idLoginUser = loginUserInfo.getIdUser();
			// ログインユーザ＝投稿者本人の場合は編集可の権限を付与
			if (idLoginUser.equals(beanPV.getIdUser())) {
				perEdit = "Y";
			}

			// 投稿内容をリクエストスコープに保存
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

		// 各textareのheight情報をSessionスコープに保存
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		session.setAttribute("arrHeight", request.getParameter("arrHeight"));
		session.setAttribute("idPost", request.getParameter("idPost"));

		// 投稿内容修正画面に遷移（/PostSendにリダイレクト）
		response.sendRedirect("/quetana/Contents/PostSend");
	}
}
