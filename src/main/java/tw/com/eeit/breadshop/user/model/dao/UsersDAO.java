package tw.com.eeit.breadshop.user.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import tw.com.eeit.breadshop.user.model.bean.Users;

public class UsersDAO {
	private Connection conn;

	public UsersDAO(Connection conn) {
		this.conn = conn;
	}

	/**
	 * 根據傳入的User Account，取得對應的User Bean，若Account不存在則回傳null
	 * 
	 * @param account 使用者帳號
	 * @return UserBean 使用者的JavaBean，若不存在則回傳null
	 * @throws SQLException 由Service處理錯誤
	 */
	public Users GetOneUserByAccount(String account) throws SQLException {
		final String SQL = "SELECT * FROM users WHERE account = ?";
		PreparedStatement preState = conn.prepareStatement(SQL);
		preState.setString(1, account);
		ResultSet rs = preState.executeQuery();
		if (rs.next()) {
			Users u = new Users(rs.getString("id"), rs.getString("account"), rs.getString("password"),
					rs.getString("photo"));
			return u;
		}
		rs.close();
		return null;
	}

	/**
	 * 根據傳入的User id，取得對應的User Bean，若id不存在則回傳null
	 * 
	 * @param id 使用者ID
	 * @return UserBean 使用者的JavaBean，若不存在則回傳null
	 * @throws SQLException 由Service處理錯誤
	 */
	public Users GetOneUserById(String id) throws SQLException {
		final String SQL = "SELECT * FROM users WHERE id = ?";
		PreparedStatement preState = conn.prepareStatement(SQL);
		preState.setString(1, id);
		ResultSet rs = preState.executeQuery();
		if (rs.next()) {
			Users u = new Users(rs.getString("id"), rs.getString("account"), rs.getString("password"),
					rs.getString("photo"));
			return u;
		}
		rs.close();
		return null;
	}
}
