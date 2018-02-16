package servlet;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class AuthServlet
 */
public class AuthServlet implements Filter{
	  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain){
		try {
			//本来アクセスしたかったURL(リクエストがあったURL)を取得、保持
			String targetURI = ((HttpServletRequest)request).getRequestURI();

			//セッションを取得
			HttpSession session = ((HttpServletRequest)request).getSession();

			//セッションの開始有無を判定
			if (session == null) {
				//セッションが開始されていない場合、セッションを開始してログイン画面へ
				session = ((HttpServletRequest)request).getSession(true);
				session.setAttribute("target", targetURI);
				((HttpServletResponse)response).sendRedirect("/quetana/Login");
			} else {
				//セッションからログイン情報の有無を判定
				Object loginUserInfo = session.getAttribute("userInfo");
				if (loginUserInfo == null) {
					//ログイン情報がない場合、ログイン画面へ
					session.setAttribute("target", targetURI);
					((HttpServletResponse)response).sendRedirect("/quetana/Login");
				} else {
					//ログイン情報がある場合、本来実行されるべきサーブレットへ処理を移す
					chain.doFilter(request, response);
				}
			}

		} catch (ServletException se) {
		} catch (IOException e) {
		}
	}

	public void init(FilterConfig filterConfig){
	}

	public void destroy(){
	}
}
