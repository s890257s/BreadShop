package tw.com.eeit.breadshop.shop.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import tw.com.eeit.breadshop.shop.model.bean.Products;
import tw.com.eeit.breadshop.user.model.bean.Users;

public class ProductsDAO {
	private Connection conn;

	public ProductsDAO(Connection conn) {
		this.conn = conn;
	}

	/**
	 * 取得products表格中的所有資料。
	 * 
	 * @return ArrayList<Products> 產品資料列表
	 * @throws SQLException 由Service處理錯誤
	 */
	public ArrayList<Products> getAllData() throws SQLException {
		final String SQL = "SELECT * FROM products";
		ArrayList<Products> aL = new ArrayList<Products>();

		ResultSet rs = conn.createStatement().executeQuery(SQL);

		while (rs.next()) {
			Products p = new Products(rs.getString("id"), rs.getString("name"), rs.getInt("price"),
					rs.getString("photo"));
			aL.add(p);
		}

		rs.close();

		return aL;
	}

	/**
	 * 根據傳入的Product id，取得對應的Product Bean，若id不存在則回傳null
	 * 
	 * @return Products 產品JavaBean，若id不存在則回傳null
	 * @throws SQLException 由Service處理錯誤
	 */
	public Products GetOneProductById(String id) throws SQLException {
		final String SQL = "SELECT * FROM products WHERE id = ?";
		PreparedStatement preState = conn.prepareStatement(SQL);

		preState.setString(1, id);
		ResultSet rs = preState.executeQuery();

		if (rs.next()) {
			Products p = new Products(rs.getString("id"), rs.getString("name"), rs.getInt("price"),
					rs.getString("photo"));
			return p;
		}

		rs.close();

		return null;
	}
}
