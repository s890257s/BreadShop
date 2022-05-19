package tw.com.eeit.breadshop.shop.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import tw.com.eeit.breadshop.shop.service.ShopService;
import tw.com.eeit.breadshop.user.model.bean.Users;

/**
 * 【新增商品到購物車】的控制器，此控制器主要行為： 
 * 1.取得client傳來，欲新增的商品ID(productId)。
 * 2.從session中撈出登入的user資料，若無user資料則導向登入頁面。
 * 3.呼叫「新增商品到使用者的購物車」的方法，傳入UserBean跟productId。 
 * 4.將頁面導向【取得所有商品】的控制器。
 */
@WebServlet("/AddProductToCart.do")
public class AddProductToCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String productId = request.getParameter("productId");
		HttpSession session = request.getSession();
		
		Users u = (Users) session.getAttribute("user");
		if (u == null) {
			request.setAttribute("message", "使用購物車前必須先進行登入!");
			request.getRequestDispatcher("page/login.jsp").forward(request, response);
			return;
		}

		new ShopService().addOneProductToCart(productId, u);

		response.sendRedirect("/BreadShop/GetAllProducts.do");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
