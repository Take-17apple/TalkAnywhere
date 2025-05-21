package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class UserChatLog implements Serializable {
	private User user;
	private String chat;
	private List<String> chatHistory;
	
	public UserChatLog() {
		
	}
	public UserChatLog(User user) {
		if (this.chatHistory == null || this.chatHistory.isEmpty()) {
			this.chatHistory = new ArrayList<>();
		}
		this.user = user;
	}
	public UserChatLog(User user, String chat) {
		this.user = user;
		this.chat = chat;
	}
	public User getUser() {
		return this.user;
	}
	public String getChat() {
		return this.chat;
	}
	public List<String> getChatHistory() {
		return this.chatHistory;
	}
	public int getUserId() {
		if (user != null) {
			return this.user.getUserId();
		}
		return 0;
	}
	public void setUserChat(User user, String chat) {
		this.user = user;
		this.chat = chat;
	}
	public void setUser(User user) {
		if (this.user == null) {
			this.user = new User();
		}
		this.user = user;
	}
	public void setChat(String chat) {
		if (this.chatHistory == null || this.chatHistory.isEmpty()) {
			chatHistory = new ArrayList<>();
		}
		this.chatHistory.add(chat);
	}
}
