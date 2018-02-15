package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.CreateAccountLogic;
import model.UserInfoBean;

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

		// アカウント作成画面に遷移
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/createAccount.jsp");
		dispatcher.forward(request, response);
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

		//CreateAccountLogicクラスのcreateAccoutメソッドを実行し、戻り値をもとにuserInfoBeanインスタンスを生成
		UserInfoBean userInfoBean = CreateAccountLogic.createAccount(stUserName, stMailAddress, stPassword);

		//ログイン判定（ユーザIDが空の場合はエラー）
		if(userInfoBean.getIdUser().equals("")) {

			//ログイン画面にエラーメッセージを表示
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/login.jsp");
			dispatcher.forward(request, response);

		} else {

			//ログイン画面にアカウントが作成された旨を表示
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/login.jsp");
			dispatcher.forward(request, response);

		}
	}

}
