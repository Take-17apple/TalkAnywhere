package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import model.UserChatLog;

public class ChatDAO {
	private final String JDBC_URL = "jdbc:mariadb://localhost/talkanywhere";
	private final String DB_USER = "root";
	private final String DB_PASS = "admin";
	
    // ユーザーのチャットを時間とともに格納する
	public boolean addUserChat(UserChatLog userChatLog, Timestamp timestamp) {
		// チャットを登録する
		String sql = "INSERT INTO chat (userId, chat, date) VALUES (?, ?, ?)";
		try (Connection conn= DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)){
			PreparedStatement stmt = conn.prepareStatement(sql) ;
			stmt.setInt(1, userChatLog.getUserId());
			stmt.setString(2, userChatLog.getChat());
			stmt.setTimestamp(3, timestamp);
			int result = stmt.executeUpdate();
			if (result > 0) {
				getChatHistory(userChatLog);
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	// ユーザーのチャット履歴を取得する
	public boolean getChatHistory(UserChatLog userChatLog) {
		String sql = "SELECT (chat) FROM chat WHERE userId = ?";
		try (Connection conn= DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)){
			PreparedStatement stmt = conn.prepareStatement(sql) ;
			stmt.setInt(1, userChatLog.getUserId());
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				userChatLog.setChat(rs.getString("chat"));
			}
			if (!userChatLog.getChatHistory().isEmpty()) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
}
