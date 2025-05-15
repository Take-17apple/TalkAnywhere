package servlet;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import logic.UserLogic;
import model.User;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
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
		// ログイン画面へ遷移
		RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp"); 
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "";
		request.setCharacterEncoding("UTF-8");
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		UserLogic userLogic = new UserLogic();
		StringBuilder sb = userLogic.inputCheck(userName, password);
		if (sb.length() != 0) {
			request.setAttribute("errorMsg", sb);
			// 入力が適切ではなかったので、ログイン画面へ遷移
			url = "index.jsp";
		} else {
			User user = new User(userName, password);
			sb = userLogic.userCheck(user);
			if (sb.length() != 0) {
				request.setAttribute("errorMsg", sb);
				// アカウント情報が登録されていなかったので、アカウント登録画面へ遷移
				url = "WEB-INF/jsp/entry.jsp";
			} else {
				// 入力が適切で、アカウント情報が登録されていたのでセッションスコープに保存して、メイン画面へ遷移
				url = "WEB-INF/jsp/main.jsp";
				HttpSession session = request.getSession();
				session.setAttribute("user", user);
			}
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher(url); 
		dispatcher.forward(request, response);
	}

}
