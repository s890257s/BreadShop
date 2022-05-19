package tw.com.eeit.breadshop.shop.service;

import java.sql.Connection;
import java.util.ArrayList;

import com.google.gson.Gson;

import tw.com.eeit.breadshop.conn.ConnectionFactory;
import tw.com.eeit.breadshop.shop.model.bean.Products;
import tw.com.eeit.breadshop.shop.model.bean.ShoppingCart;
import tw.com.eeit.breadshop.shop.model.dao.ProductsDAO;
import tw.com.eeit.breadshop.shop.model.dao.ShoppingCartDAO;
import tw.com.eeit.breadshop.user.model.bean.Users;

public class ShopService {

	/**
	 * 開啟連線 → 取得所有products的資料 → 關閉連線 → 回傳List。
	 * 
	 * @return 若有資料則回傳 ArrayList<Products>，任何程式錯誤皆回傳 null
	 */
	public ArrayList<Products> getAllProducts() {
		try (Connection conn = ConnectionFactory.getconnection()) {

			System.out.println("TEST");
			ArrayList<Products> aL = new ProductsDAO(conn).getAllData();

			return aL;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * 新增指定商品到指定使用者的購物車中，若商品已經存在，則讓商品數量+1。 此方法無回傳值。
	 * 
	 * @param productId 指定商品Id
	 * @param u         指定使用者
	 */
	public void addOneProductToCart(String productId, Users u) {

		try (Connection conn = ConnectionFactory.getconnection()) {
			ShoppingCartDAO sCDAO = new ShoppingCartDAO(conn);
			ShoppingCart shoppingCart = sCDAO.checkUsersProduct(u, productId);

			if (shoppingCart == null) {
				sCDAO.addProductIntoUserCart(productId, u, 1);
			} else {
				sCDAO.updateOneProductQuantity(shoppingCart, shoppingCart.getQuantity() + 1);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 取得指定使用的購物車列表，回傳ArrayList<ShoppingCart>，若購物車沒有商品或程式出錯則回傳null。
	 * 
	 * @param u 指定使用者
	 * @return ArrayList<ShoppingCart> 購物車列表，如果購物車內無商品或程式出錯則回傳null
	 */
	public ArrayList<ShoppingCart> getUserShoppingCart(Users u) {
		try (Connection conn = ConnectionFactory.getconnection()) {
			ShoppingCartDAO sCDAO = new ShoppingCartDAO(conn);
			ArrayList<ShoppingCart> aL = sCDAO.getUserCart(u);
			return aL;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 刪除指定使用者的購物車中，指定的商品。此方法無回傳值。
	 * 
	 * @param u 指定使用者。
	 * @param productId 指定商品ID。
	 */
	public void deleteShoppingCartProduct(Users u, String productId) {
		try (Connection conn = ConnectionFactory.getconnection()) {

			new ShoppingCartDAO(conn).deleteOneProduct(u, new Products(productId));;

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
