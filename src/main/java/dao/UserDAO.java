package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.User;

public class UserDAO {
	private final String JDBC_URL = "jdbc:mariadb://localhost/talkanywhere";
	private final String DB_USER = "root";
	private final String DB_PASS = "admin";
	
	
    // ユーザー情報を確認し一致すればユーザーIDを取得する
    public boolean userCheck(User user) {
	    String sql = "SELECT * FROM user WHERE userName = ? AND password = ?";
	    try (Connection conn= DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)){
	    	PreparedStatement stmt = conn.prepareStatement(sql);
	        stmt.setString(1, user.getUserName());
	        stmt.setString(2, user.getPassword());
	        ResultSet rs = stmt.executeQuery();
	        if (rs.next()) {
	        	user.setUserId(rs.getInt("userId"));
	        	return true;
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return false;
    }
    // ユーザー情報を登録し成功すればユーザーIDを取得する
    public boolean userRegistration(User user) {
        // すでに同じ名前のユーザーがいないか確認
    	 String sql = "INSERT INTO user (userName, password) VALUES (?, ?)";
        try (Connection conn= DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)){
        	PreparedStatement stmt = conn.prepareStatement(sql) ;
            stmt.setString(1, user.getUserName());
            stmt.setString(2, user.getPassword());
            int result = stmt.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    // ユーザー情報を削除する
    public boolean userDelete(User user) {
        // ユーザーIDが一致したら削除
    	 String sql = "DELETE FROM user WHERE userId = ?";
        try (Connection conn= DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)){
        	PreparedStatement stmt = conn.prepareStatement(sql) ;
            stmt.setInt(1, user.getUserId());
            int result = stmt.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
