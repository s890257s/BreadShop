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
 * 【刪除使用者購物車中的商品】的控制器，此控制器主要行為： 
 * 1.取得client傳來，欲刪除的商品ID(deleteProductId)。
 * 2.從session中撈出登入的user資料，若無user資料則導向登入頁面。
 * 3.呼叫「從指定使用者的購物車中，刪除指定商品」的方法，傳入UserBean跟deleteProductId。
 * 4.將頁面導向【取得使用者購物車內容】的控制器。
 */
@WebServlet("/DeleteShoppingCartProduct.do")
public class DeleteShoppingCartProductServelt extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String deleteProductId = request.getParameter("deleteProductId");
		HttpSession session = request.getSession();
		
		Users u = (Users) session.getAttribute("user");
		if (u == null) {
			request.setAttribute("message", "使用購物車前必須先進行登入!");
			request.getRequestDispatcher("page/login.jsp").forward(request, response);
			return;
		}

		new ShopService().deleteShoppingCartProduct(u, deleteProductId);

		response.sendRedirect("/BreadShop/GetUserShoppingCart.do");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
