package tw.com.eeit.breadshop.init;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import tw.com.eeit.breadshop.conn.ConnectionFactory;
import tw.com.eeit.breadshop.shop.model.bean.Products;
import tw.com.eeit.breadshop.user.model.bean.Users;

@WebListener
public class Init implements ServletContextListener {

	private final String CREATE_USERS_TABLE = "CREATE TABLE IF NOT EXISTS users(id TEXT PRIMARY KEY NOT NULL, account TEXT NOT NULL, password TEXT NOT NULL, photo BLOB)";
	private final String CREATE_PRODUCTS_TABLE = "CREATE TABLE IF NOT EXISTS products(id TEXT PRIMARY KEY NOT NULL, name TEXT NOT NULL, price INT, photo BLOB)";
	private final String CREATE_SHOPPINGCART_TABLE = "CREATE TABLE IF NOT EXISTS shoppingcart(id TEXT NOT NULL, userId TEXT NOT NULL, productId TEXT NOT NULL, quantity INT NOT NULL, FOREIGN KEY(userId) REFERENCES users(id), FOREIGN KEY(productId) REFERENCES products(id))";
	private final String DROP_USERS_TABLE = "DROP TABLE IF EXISTS users";
	private final String DROP_PRODUCTS_TABLE = "DROP TABLE IF EXISTS products";
	private final String DROP_SHOPPINGCART_TABLE = "DROP TABLE IF EXISTS shoppingcart";

	private final String INSERTP_USERS_TABLE = "INSERT INTO users(id, account, password, photo) VALUES (?, ?, ?, ?)";
	private final String INSERTP_PRODUCTS_TABLE = "INSERT INTO products(id, name, price, photo) VALUES (?, ?, ?, ?)";

	private final String USERS_PATH = "assets/users.json";
	private final String PRODUCTS_PATH = "assets/products.json";
	private String realPath = "";

	/**
	 * 初始化，第一次啟動server時，建立資料庫SQLite.db，建立資料表users、products、shoppingCart；
	 * 並於users、products中填入預設資料。
	 *
	 * 如web.xml中的「SQLiteReset」屬性為true，則每次啟動server時都會重建資料表。
	 */
	public void contextInitialized(ServletContextEvent sce) {
		realPath=sce.getServletContext().getRealPath("");
		
		Boolean resetDb = Boolean.valueOf(sce.getServletContext().getInitParameter("SQLiteReset"));


		try (Connection conn = ConnectionFactory.getconnection()) {

			if (resetDb) {
				dropTables(conn);
			}

			createTables(conn);

			addBasicData(conn);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 建立資料表users、products、shoppingCart，如果資料表已經存在，則不做任何事情。
	 */
	private void createTables(Connection conn) throws Exception {

		Statement state = conn.createStatement();

		state.addBatch(CREATE_USERS_TABLE);
		state.addBatch(CREATE_PRODUCTS_TABLE);
		state.addBatch(CREATE_SHOPPINGCART_TABLE);
		state.executeBatch();
		state.close();

	}

	/**
	 * 刪除資料表users、products、shoppingCart，如果資料表不存在，則不做任何事情。
	 */
	private void dropTables(Connection conn) throws Exception {

		Statement state = conn.createStatement();

		state.addBatch(DROP_USERS_TABLE);
		state.addBatch(DROP_PRODUCTS_TABLE);
		state.addBatch(DROP_SHOPPINGCART_TABLE);

		state.executeBatch();
		state.close();

	}

	/**
	 * 新增預設資料，如果users、products資料表中有任何一筆資料，則終止其資料新增的行為。
	 * 預設資料來源 webapp/assets/users.json & products.json
	 */
	private void addBasicData(Connection conn) throws Exception {

		//如果users資料表中有任何一筆資料，則終止此方法。
		if (conn.createStatement().executeQuery("SELECT id FROM users").next()) {
			return;
		}

		//使用Gson、JavaIO，讀取webapp/assets/users.json的資料
		BufferedReader brForUsers = new BufferedReader(new FileReader(realPath + USERS_PATH));
		List<Users> userList = new Gson().fromJson(brForUsers, new TypeToken<List<Users>>() {
		}.getType());

		//新增資料到資料表
		PreparedStatement userPreState = conn.prepareStatement(INSERTP_USERS_TABLE);
		for (Users u : userList) {
			userPreState.setString(1, u.getId());
			userPreState.setString(2, u.getAccount());
			userPreState.setString(3, u.getPassword());
			userPreState.setString(4, u.getPhoto());
			userPreState.addBatch();
		}
		userPreState.executeBatch();
		userPreState.close();

		//如果products資料表中有任何一筆資料，則終止此方法。
		if (conn.createStatement().executeQuery("SELECT id FROM products").next()) {
			return;
		}
		
		//使用Gson、JavaIO，讀取webapp/assets/products.json的資料
		BufferedReader brForProducts = new BufferedReader(new FileReader(realPath + PRODUCTS_PATH));
		List<Products> productList = new Gson().fromJson(brForProducts, new TypeToken<List<Products>>() {
		}.getType());

		//新增資料到資料表
		PreparedStatement productPreState = conn.prepareStatement(INSERTP_PRODUCTS_TABLE);
		for (Products p : productList) {
			productPreState.setString(1, p.getId());
			productPreState.setString(2, p.getName());
			productPreState.setInt(3, p.getPrice());
			productPreState.setString(4, p.getPhoto());
			productPreState.addBatch();
		}
		productPreState.executeBatch();
		productPreState.close();

	}

}
