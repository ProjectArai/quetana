package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.LoginLogic;
import model.UserInfoBean;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet ("/Login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// response.getWriter().append("Served at: ").append(request.getContextPath());

		// ログイン画面に遷移
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/login.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//リクエストパラメータの取得
		request.setCharacterEncoding("UTF-8");
		String stLoginUser = request.getParameter("stLoginUser");
		String stPassword = request.getParameter("stPassword");

		//ログインユーザを取得
		UserInfoBean loginUserInfo = LoginLogic.getLoginUserInfo(stLoginUser, stPassword);

		//ログイン判定（ユーザIDが空の場合はエラー）
		if(loginUserInfo.getIdUser().equals("")) {

			//ログイン画面にエラーメッセージを表示
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/");
			dispatcher.forward(request, response);

		} else {

			//ユーザ情報をセッションスコープに保存
			HttpSession session = request.getSession();
			session.setAttribute("userInfo", loginUserInfo);

			//ホーム画面に遷移
			RequestDispatcher dispatcher = request.getRequestDispatcher("/Home");
			dispatcher.forward(request, response);

		}
	}
}
