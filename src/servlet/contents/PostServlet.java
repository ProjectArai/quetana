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

import model.PostLogic;
import model.UserInfoBean;

/**
 * Servlet implementation class HomeServlet
 */
@WebServlet("/Contents/Post")
public class PostServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public PostServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());

		// post.jspにフォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/post.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// セッションスコープからユーザ情報を取得
		HttpSession session = request.getSession();
		UserInfoBean userInfo = (UserInfoBean)session.getAttribute("userInfo");

		// リクエストパラメータを取得
		request.setCharacterEncoding("UTF-8");

		// 投稿情報をマップに詰め込む
		Map inParam = new HashMap();
		inParam.put("idUser", userInfo.getIdUser());
		inParam.put("cfPost", request.getParameter("cfPost"));
		inParam.put("stTitle", request.getParameter("stTitle"));
		inParam.put("stPart", request.getParameter("stPart"));
		inParam.put("stGenre", request.getParameter("stGenre"));
		inParam.put("stPlace", request.getParameter("stPlace"));
		inParam.put("dtEvent", request.getParameter("dtEvent"));
		inParam.put("stDetails", request.getParameter("stDetails"));

		// 投稿内容のDB登録処理の実行
		Map resultPost = PostLogic.postTimeLine(inParam);

		String errMsg = (String)resultPost.get("errMsg");

		if (errMsg.equals("")) {
			// 投稿処理が成功した場合
			// ホーム画面を表示（/Homeにリダイレクト）
			response.sendRedirect("/quetana/Contents/Home");
//		} else {
//			//ログインユーザ情報の判定が否の場合、
//			//エラーメッセージをリクエストスコープに持たせ、editProfile.jspにフォワード
//			request.setAttribute("errMsg", errMsg);
//			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/editProfile.jsp");
//			dispatcher.forward(request, response);
		}
	}
}