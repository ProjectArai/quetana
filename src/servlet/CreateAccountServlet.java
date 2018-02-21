package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.CreateAccountLogic;
import model.LoginUserInfoBean;


/**
 * Servlet implementation class CreateAccountServlet
 */
@WebServlet("/CreateAccount")
public class CreateAccountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateAccountServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// response.getWriter().append("Served at: ").append(request.getContextPath());

		// セッションスコープからインスタンスを取得
		HttpSession session = request.getSession();
		LoginUserInfoBean loginUserInfo = (LoginUserInfoBean)session.getAttribute("loginUserInfo");

		// 条件分岐：セッションスコープにインスタンスがない/ある
		if(loginUserInfo == null) {
			// ない場合
			// createAccount.jspにフォワードする
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/createAccount.jsp");
			dispatcher.forward(request, response);
		} else {
			// ある場合
			// /Homeにリダイレクトする
			response.sendRedirect("/quetana/Contents/Home");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// doGet(request, response);

		//リクエストパラメータの取得
		request.setCharacterEncoding("UTF-8");
		String stUserName = request.getParameter("stUserName");
		String stMailAddress = request.getParameter("stMailAddress");
		String stPassword = request.getParameter("stPassword");

		//CreateAccountLogicクラスのcreateAccoutメソッドを実行する。
		String errMsg = CreateAccountLogic.createAccount(stUserName, stMailAddress, stPassword);

		// 条件分岐：アカウント作成の成功/失敗
		if(errMsg == null) {

			// 成功した場合
			// /Loginにリダイレクトする
			response.sendRedirect("/quetana/Login");

		} else {

			// 失敗した場合
			// creacteAccount.jspにフォワードする
			request.setAttribute("errMsg", errMsg);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/createAccount.jsp");
			dispatcher.forward(request, response);

		}
	}
}
