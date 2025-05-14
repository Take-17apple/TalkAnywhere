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
 * Servlet implementation class EntryServlet
 */
@WebServlet("/EntryServlet")
public class EntryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EntryServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// アカウント登録画面へ遷移
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/entry.jsp"); 
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
			// 入力が適切ではなかったので、元の画面へ遷移
			request.setAttribute("errorMsg", sb);
			url = "WEB-INF/jsp/entry.jsp";
		} else {
			User user = new User(userName, password);
			sb = userLogic.userRegistration(user);
			if (!sb.toString().equals("ユーザー情報が登録されています")) {
				// アカウント情報が登録されていたので、ログイン画面へ遷移
				request.setAttribute("errorMsg", sb);
				url = "index.jsp";
			} else if (!sb.toString().equals("ユーザー登録に失敗しました")) {
				// 登録に失敗したので、再度アカウント登録画面へ遷移
				request.setAttribute("errorMsg", sb);
				url = "WEB-INF/jsp/entry.jsp";
			} else {
				// アカウント情報が登録されたのでセッションスコープに保存して、メイン画面へ遷移する
				request.setAttribute("successMsg", "ユーザー登録をしました<br>ログインしてください");
				url = "WEB-INF/jsp/main.jsp";
				HttpSession session = request.getSession();
				session.setAttribute("user", user);
			}
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher(url); 
		dispatcher.forward(request, response);
	}
	

}
