package tw.com.eeit.breadshop.shop.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.UUID;

import tw.com.eeit.breadshop.shop.model.bean.Products;
import tw.com.eeit.breadshop.shop.model.bean.ShoppingCart;
import tw.com.eeit.breadshop.user.model.bean.Users;
import tw.com.eeit.breadshop.user.model.dao.UsersDAO;

public class ShoppingCartDAO {
	private Connection conn;
	private UsersDAO uDAO;
	private ProductsDAO pDAO;

	public ShoppingCartDAO(Connection conn) {
		this.conn = conn;
		uDAO = new UsersDAO(conn);
		pDAO = new ProductsDAO(conn);
	}

	/**
	 * 新增指定商品到指定使用者的購物中，數量為quantity。 使用UUID作為shoppingCart資料表主鍵。此方法無回傳值。
	 * 
	 * @param productId 指定商品的ID。
	 * @param users     使用者的JavaBean。
	 * @param quantity  商品數量。
	 * @throws SQLException 由Service處理錯誤。
	 */
	public void addProductIntoUserCart(String productId, Users users, int quantity) throws SQLException {
		final String SQL = "INSERT INTO shoppingCart(id, userId, productId, quantity) VALUES (?, ?, ?, ?)";
		PreparedStatement preState = conn.prepareStatement(SQL);

		preState.setString(1, UUID.randomUUID().toString());
		preState.setString(2, users.getId());
		preState.setString(3, productId);
		preState.setInt(4, quantity);

		preState.execute();
		preState.close();
	}

	/**
	 * 根據使用者User取得使用者的購物車列表，回傳ArrayList<ShoppingCart>。
	 * 
	 * @param u 使用者的JavaBean。
	 * @return ArrayList<ShoppingCart> 使用者的購物車資訊，如無資料則回傳null。
	 * @throws SQLException 由Service處理錯誤。
	 */
	public ArrayList<ShoppingCart> getUserCart(Users u) throws SQLException {
		final String SQL = "SELECT * FROM shoppingCart WHERE userId=?";
		ArrayList<ShoppingCart> aL = new ArrayList<ShoppingCart>();

		PreparedStatement preState = conn.prepareStatement(SQL);
		preState.setString(1, u.getId());

		ResultSet rs = preState.executeQuery();
		while (rs.next()) {
			aL.add(new ShoppingCart(rs.getString("id"), uDAO.GetOneUserById(rs.getString("userId")),
					pDAO.GetOneProductById(rs.getString("productId")), rs.getInt("quantity")));
		}
		rs.close();
		preState.close();

		if (aL.size() == 0) {
			return null;
		}

		return aL;
	}

	/**
	 * 檢查指定使用者Users的購物車是否有指定商品，若有則回傳單筆購物車資訊ShoppingCartBean，若無則回傳null。
	 * 回傳的ShoppingCartBean裡頭會包含UserBean和ProductBean。
	 * 
	 * @param u         目標使用者，UserBean。
	 * @param productId 商品ID。
	 * @return ShoppingCart 使用者的單筆購物車資訊，如無資料則回傳null。
	 * @throws SQLException 由Service處理錯誤。
	 */
	public ShoppingCart checkUsersProduct(Users u, String productId) throws SQLException {
		final String SQL = "SELECT * FROM shoppingCart WHERE userId = ? AND productId = ?";
		PreparedStatement preState = conn.prepareStatement(SQL);
		preState.setString(1, u.getId());
		preState.setString(2, productId);
		ResultSet rs = preState.executeQuery();

		if (rs.next()) {
			return new ShoppingCart(rs.getString("id"), uDAO.GetOneUserById(rs.getString("userId")),
					pDAO.GetOneProductById(rs.getString("productId")), rs.getInt("quantity"));
		}

		rs.close();
		preState.close();
		return null;
	}

	/**
	 * 修改指定shoppingCartId的商品數量，此方法無回傳值。
	 * 
	 * @param shoppingCart 購物車ShoppingCartBean，須包含id。
	 * @param quantity     目標修改數量。
	 * @throws SQLException 由Service處理錯誤。
	 */
	public void updateOneProductQuantity(ShoppingCart shoppingCart, int quantity) throws SQLException {
		final String SQL = "UPDATE shoppingCart SET quantity = ? WHERE id = ?";
		PreparedStatement preState = conn.prepareStatement(SQL);

		preState.setInt(1, quantity);
		preState.setString(2, shoppingCart.getId());
		preState.executeUpdate();
		preState.close();
	}

	
	/**
	 * 刪除指定使用者購物車中的指定商品，若商品或使用者不存在會拋出SQLException。
	 * 
	 * @param u 使用者UserBean，須包含ID。
	 * @param p 商品ProductBean，須包含ID。
	 * @throws SQLException 
	 * */
	public void deleteOneProduct(Users u,Products p) throws SQLException {
		final String SQL = "DELETE FROM shoppingCart WHERE userId = ? AND productId = ?";
		PreparedStatement preState = conn.prepareStatement(SQL);

		preState.setString(1, u.getId());
		preState.setString(2, p.getId());
		preState.execute();
		preState.close();
	}

}
