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

		// セッションにユーザ情報がない場合
		// createAccount.jspにフォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/createAccount.jsp");
		dispatcher.forward(request, response);

		// セッションにユーザ情報がある場合
		// ●/Homeにリダイレクトする●
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

		// ●アカウント作成成功・失敗判定●
		if(userInfoBean.getIdUser().equals("")) {

			// アカウント作成に失敗した場合
			// creacteAccount.jspにフォワードする●
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/login.jsp");
			dispatcher.forward(request, response);

		} else {

			// アカウント作成に成功した場合
			// /Homeにリダイレクトする●
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/login.jsp");
			dispatcher.forward(request, response);

		}
	}

}
