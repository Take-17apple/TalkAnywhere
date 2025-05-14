package logic;

import dao.UserDAO;
import model.User;

public class UserLogic {
	UserDAO userDao = new UserDAO();
	StringBuilder sb = new StringBuilder();
	
	public StringBuilder inputCheck(String userName, String password) {
		// 入力された値が適切か判別する
		if (userName == null || userName.isBlank()) {
			sb.append("名前が入力されていません");
		} else if (userName.length() < 3) {
			sb.append("名前が短すぎます");
		}
		
		if (password == null || password.isBlank()) {
			sb.append("パスワードが入力されていません");
		} else if (password.length() < 3) {
			sb.append("パスワードが短すぎます");
		} else if (password.contains(" ")) {
			sb.append("パスワードに空白は使用できません");
		}
		return sb;
	}
	
	public StringBuilder userCheck(User user) {
		// ユーザー情報を確認し一致すればユーザーIDを取得する中継メソッド
		boolean isUserCheck = userDao.userCheck(user);
		if (isUserCheck == false) {
			sb.append("ユーザー情報が登録されていません");
		}
		return sb;
	}
	public StringBuilder userRegistration(User user) {
		// ユーザー情報を確認し一致しなかったらユーザー情報を登録する
		sb = userCheck(user);
		if (sb.length() != 0) {
			// errorMsgを初期化する
			sb.setLength(0);
			boolean isUserRegistration = userDao.userRegistration(user);
			if (isUserRegistration == false) {
				sb.append("ユーザー登録に失敗しました");
			}
		} else {
			sb.append("ユーザー情報が登録されています");
		}
		return sb;
	}
}
