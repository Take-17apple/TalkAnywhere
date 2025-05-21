package servlet;

import java.io.IOException;
import java.sql.Timestamp;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import logic.ChatLogic;
import model.User;
import model.UserChatLog;

/**
 * Servlet implementation class TalkServlet
 */
@WebServlet("/TalkServlet")
public class TalkServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TalkServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		UserChatLog userChatLog = (UserChatLog)session.getAttribute("userChatLog");
		if (userChatLog == null) {
			userChatLog = new UserChatLog(user);
		} else if (userChatLog.getUser() == null) {
			userChatLog.setUser(user);
		}
		new ChatLogic().getChatHistory(userChatLog);
		session.setAttribute("userChatLog", userChatLog);
		// トーク画面へ遷移
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/talk.jsp"); 
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String chat = request.getParameter("chat");
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		
		if (chat == null || chat.isBlank()) {
			// エラーメッセージをセットし、トーク画面へ戻る
			request.setAttribute("errorMsg", "何も入力されていません");
		} else {
			HttpSession session = request.getSession();
			User user = (User)session.getAttribute("user");
			UserChatLog userChatLog = (UserChatLog)session.getAttribute("userChatLog");
			if (userChatLog == null) {
				userChatLog = new UserChatLog(user, chat);
			} else {
				userChatLog.setUserChat(user, chat);
			}
			// 送られてきたチャットをデータベースに登録する
			boolean isChatRegistration = new ChatLogic().addUserChat(userChatLog, timestamp);
			if (isChatRegistration == false) {
				// エラーメッセージをセットし、トーク画面へ戻る
				request.setAttribute("errorMsg", "トークが送信されませんでした");
			} 
			session.setAttribute("userChatLog", userChatLog);
			session.setAttribute("aiChatLog", userChatLog);
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/talk.jsp"); 
		dispatcher.forward(request, response);
	}

}
