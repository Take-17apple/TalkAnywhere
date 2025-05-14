package servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy'年'MM'月'dd'日'E'曜日'K'時'mm'分'ss'秒'");
		
		if (chat == null || chat.isBlank()) {
			// エラーメッセージをセットし、トーク画面へ戻る
			request.setAttribute("errorMsg", "何も入力されていません");
		} else {
			HttpSession session = request.getSession();
			User user = (User)session.getAttribute("user");
			// 送られてきたチャットをデータベースに登録する
			boolean isChatRegistration = new ChatLogic().userChat(user, chat, sdf.format(date));
			if (isChatRegistration == false) {
				// エラーメッセージをセットし、トーク画面へ戻る
				request.setAttribute("errorMsg", "トークが送信されませんでした");
			} else {
				UserChatLog userChatLog = (UserChatLog)session.getAttribute("userChatLog");
				if (userChatLog == null) {
					userChatLog = new UserChatLog(user);
				}
				userChatLog.addChat(chat);
				session.setAttribute("userChatLog", userChatLog);
			}
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/talk.jsp"); 
		dispatcher.forward(request, response);
	}

}
