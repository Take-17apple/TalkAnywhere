package model;

import java.io.Serializable;

public class User implements Serializable {
	private int userId;
	private String userName;
	private String password;
	
	public User() {
		
	}
	public User(String userName, String password) {
		this.userName = userName;
		this.password = password;
	}
	public void setUserInformation(String userName, String password) {
		this.userName = userName;
		this.password = password;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getUserId() {
		return userId;
	}
	public String getUserName() {
		return userName;
	}
	public String getPassword() {
		return password;
	}
	
}
