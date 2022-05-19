package tw.com.eeit.breadshop.user.service;

import java.sql.Connection;

import tw.com.eeit.breadshop.conn.ConnectionFactory;
import tw.com.eeit.breadshop.user.model.bean.Users;
import tw.com.eeit.breadshop.user.model.dao.UsersDAO;

public class UsersService {

	/**
	 * 根據傳入的UserBean，驗證是否登入成功，登入成功則回傳UserBean，登入失敗或程式出錯則回傳null
	 * 
	 * @param u User的JavaBean，須包含account與password
	 * @return Users 登入成功回傳UserBean，登入失敗或程式出錯回傳null
	 */
	public Users login(Users u) {
		try (Connection conn = ConnectionFactory.getconnection()) {

			Users user = new UsersDAO(conn).GetOneUserByAccount(u.getAccount());

			if (user != null && user.getAccount().equals(u.getAccount())
					&& user.getPassword().equals(u.getPassword())) {
				return user;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

}
