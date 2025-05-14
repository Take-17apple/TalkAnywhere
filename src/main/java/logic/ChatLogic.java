package logic;

import dao.ChatDAO;
import model.User;

public class ChatLogic {
	ChatDAO chatDao = new ChatDAO();
	StringBuilder sb = new StringBuilder();
	
	public boolean userChat(User user, String chat, String date) {
		// ユーザーのチャットを登録する中継メソッド
		return chatDao.addChat(user, chat, date);
	}
}
