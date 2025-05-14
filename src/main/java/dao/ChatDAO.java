package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import model.User;

public class ChatDAO {
	private final String JDBC_URL = "jdbc:mariadb://localhost/talkanywhere";
	private final String DB_USER = "root";
	private final String DB_PASS = "admin";
	
	
    // ユーザーのチャットを時間とともに格納する
	public boolean addChat(User user, String chat, String date) {
		// チャットを登録する
		String sql = "INSERT INTO chat (userId, chat, date) VALUES (?, ?, ?) WHERE userId = ?";
		try (Connection conn= DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)){
			PreparedStatement stmt = conn.prepareStatement(sql) ;
			stmt.setInt(1, user.getUserId());
			stmt.setString(2, chat);
			stmt.setString(3, date);
			stmt.setInt(4, user.getUserId());
			int result = stmt.executeUpdate();
			return result > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
}
