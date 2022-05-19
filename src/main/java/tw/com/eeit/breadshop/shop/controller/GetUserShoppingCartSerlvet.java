package tw.com.eeit.breadshop.shop.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import tw.com.eeit.breadshop.shop.model.bean.ShoppingCart;
import tw.com.eeit.breadshop.shop.service.ShopService;
import tw.com.eeit.breadshop.user.model.bean.Users;

/**
 * 【取得使用者購物車內容】的控制器，此控制器主要行為： 
 * 1.從session中撈出登入的user資料，若無user資料則導向登入頁面。
 * 2.呼叫「取得指定使用者購物車內容」的方法，傳入UserBean，得到指定使用者的購物車內容列表(List)。
 * 3.將使用者購物車列表(List)存成session區間的屬性(Attribute)。
 * 4.將頁面導向「展示使用者購物車內容的頁面」shoppingCart.jsp
 */
@WebServlet("/GetUserShoppingCart.do")
public class GetUserShoppingCartSerlvet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		Users u = (Users) session.getAttribute("user");
		if (u == null) {
			request.setAttribute("message", "使用購物車前必須先進行登入!");
			request.getRequestDispatcher("page/login.jsp").forward(request, response);
			return;
		}
		
		ArrayList<ShoppingCart> aL = new ShopService().getUserShoppingCart(u);
		session.setAttribute("shoppingCart", aL);
		
		response.sendRedirect("/BreadShop/page/shoppingCart.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
