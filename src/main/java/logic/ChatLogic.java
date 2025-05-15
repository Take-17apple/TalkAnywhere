package logic;

import java.sql.Timestamp;

import dao.ChatDAO;
import model.UserChatLog;

public class ChatLogic {
	ChatDAO chatDao = new ChatDAO();
	StringBuilder sb = new StringBuilder();
	
	public boolean addUserChat(UserChatLog userChatLog, Timestamp timestamp) {
		// ユーザーのチャットを登録する中継メソッド
		if (userChatLog.getUserId() == 0) {
			sb.append("ユーザーがログインされていません or ユーザーが異常な値です");
			return false;
		}
		return chatDao.addUserChat(userChatLog, timestamp);
	}
	
	public boolean getChatHistory(UserChatLog userChatLog) {
		// ユーザーのチャット履歴を取得する中継メソッド
		if (userChatLog.getUserId() == 0) {
			sb.append("ユーザーがログインされていません or ユーザーが異常な値です");
			return false;
		}
		if (chatDao.getChatHistory(userChatLog) == false) {
			sb.append("トーク履歴がありません");
			return false;
		}
		return true;
	}
}
