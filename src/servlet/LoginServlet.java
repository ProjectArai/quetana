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

		// セッションスコープからインスタンスを取得
		HttpSession session = request.getSession();
		UserInfoBean userInfo = (UserInfoBean)session.getAttribute("userInfo");

		// セッション上のログイン情報の有無を判定
		if(userInfo == null) {
			// ログイン情報がない場合、login.jspにフォワード
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/login.jsp");
			dispatcher.forward(request, response);
		} else {
			// ログイン情報がある場合、/Homeにリダイレクト
			response.sendRedirect("/quetana/Contents/Home");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//リクエストパラメータの取得
		request.setCharacterEncoding("UTF-8");
		String stLoginUser = request.getParameter("stLoginUser");
		String stPassword = request.getParameter("stPassword");

		//ログインユーザを取得（★Daoの型に合わせて直す！）
		UserInfoBean loginUserInfo = LoginLogic.getLoginUserInfo(stLoginUser, stPassword);

		//ログイン判定（ユーザIDが空の場合はエラー）（★Daoの型に合わせて直す！）
		if(loginUserInfo.getIdUser().equals("")) {
			// ログインに失敗した場合、login.jspにフォワード（★エラー出す！）
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/");
			dispatcher.forward(request, response);
		} else {
			// ログインに成功した場合、ユーザ情報をセッションに保存
			HttpSession session = request.getSession();
			session.setAttribute("userInfo", loginUserInfo);

			//本来アクセスしたかった画面(のServlet)にリダイレクト
			String target = (String)session.getAttribute("target");
			response.sendRedirect(target);
		}
	}
}
