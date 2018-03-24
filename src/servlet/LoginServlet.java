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
import model.logic.LoginLogic;

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
	 * ログイン画面が呼び出された時の処理
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// セッションスコープからログイン情報を取得
		HttpSession session = request.getSession();
		LoginUserInfoBean loginUserInfo = (LoginUserInfoBean)session.getAttribute("loginUserInfo");

		//本来アクセスしたかったURL(リクエストがあったURL)を取得
		String targetURI = ((HttpServletRequest)request).getRequestURI();
		//ターゲットURLの指定が無い場合、固定で/Homeを設定
		if (targetURI == null) {
			session.setAttribute("target", "/quetana/Contents/Home");
		}

		// セッション上のログイン情報の有無を判定
		if (loginUserInfo == null) {
			// ログイン情報がない場合、login.jspにフォワード
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/login.jsp");
			dispatcher.forward(request, response);
		} else {
			// ログイン情報がある場合、/Homeにリダイレクト
			response.sendRedirect("/quetana/Contents/Home");
		}
	}

	/**
	 * ログインボタンが押下された時の処理
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//リクエストパラメータの取得
		request.setCharacterEncoding("UTF-8");
		Map inParam = new HashMap();
		inParam.put("stLoginUser", request.getParameter("stLoginUser"));
		inParam.put("stPassword", request.getParameter("stPassword"));

		//ログインユーザ情報の判定
		Map mapResult = LoginLogic.getLoginUserInfo(inParam);
		String errType = (String)mapResult.get("errType");
		String errMsg = (String)mapResult.get("errMsg");

		// ログインユーザ情報の判定が正の場合
		if (errType == null) {
			// ユーザ情報をセッションに保存
			LoginUserInfoBean loginUserInfo = (LoginUserInfoBean)mapResult.get("loginUserInfo");
			HttpSession session = request.getSession();
			session.setAttribute("loginUserInfo", loginUserInfo);
			// 本来アクセスしたかった画面(のServlet)にリダイレクト
			String target = (String)session.getAttribute("target");
			response.sendRedirect(target);

		// ログインユーザ情報の判定が否の場合
		} else if (errType.equals("input")){
			// errMsgとinParamを持たせてlogin.jspにフォワード
			request.setAttribute("errMsg", errMsg);
			request.setAttribute("inParam", inParam);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/login.jsp");
			dispatcher.forward(request, response);

		// システムエラー(DB処理失敗)の場合
		} else if (errType.equals("system")){
			// ★errMsgと遷移元画面情報を持たせて/Errorにリダイレクト？
			HttpSession session = request.getSession();
			session.setAttribute("errMsg", errMsg);
			session.setAttribute("source", "/quetana/Login");
			response.sendRedirect("/quetana/Error");
		}
	}
}
