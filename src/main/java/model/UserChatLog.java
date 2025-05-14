package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class UserChatLog implements Serializable {
	private User user;
	private List<String> chatHistory;
	
	public UserChatLog() {
		this.chatHistory = new ArrayList<>();
	}
	public UserChatLog(User user) {
		this.user = user;
		this.chatHistory = new ArrayList<>();
	}
	public void addChat(String chat) {
		this.chatHistory.add(chat);
	}
	public User getUser() {
		return user;
	}
	public List<String> getChatHistory() {
		return chatHistory;
	}
	@Override
	public String toString() {
		// チャットの中身を返す
		if (!this.chatHistory.isEmpty()) {
			return chatHistory.toString();
		}
		return "チャットは保存されていません";
	}
}
