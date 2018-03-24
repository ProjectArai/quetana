package servlet;

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

import model.bean.LoginUserInfoBean;
import model.logic.CreateAccountLogic;


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
	 * アカウント作成画面が呼び出された時の処理
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
	 * アカウント作成ボタンが押下された時の処理
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// リクエストパラメータの取得
		request.setCharacterEncoding("UTF-8");
		Map inParam = new HashMap();
		inParam.put("stAccountName", request.getParameter("stAccountName"));
		inParam.put("stMailAddress", request.getParameter("stMailAddress"));
		inParam.put("stPassword", request.getParameter("stPassword"));
		inParam.put("stConfirmPassword", request.getParameter("stConfirmPassword"));

		// CreateAccountLogicクラスのcreateAccoutメソッドを実行する。
		String errMsg = CreateAccountLogic.createAccount(inParam);

		// 条件分岐：アカウント作成の成功/失敗
		if(errMsg.equals("")) {
			// 成功した場合、/Loginにリダイレクトする
			HttpSession session = request.getSession();
			session.setAttribute("infoMsg", "アカウントを作成しました");
			response.sendRedirect("/quetana/Login");
		} else {
			// 失敗した場合、errMsgとinParamを持たせてcreacteAccount.jspにフォワードする
			request.setAttribute("errMsg", errMsg);
			request.setAttribute("inParam", inParam);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/createAccount.jsp");
			dispatcher.forward(request, response);
		}
	}
}
