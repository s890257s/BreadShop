package tw.com.eeit.breadshop.shop.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tw.com.eeit.breadshop.shop.model.bean.Products;
import tw.com.eeit.breadshop.shop.service.ShopService;

/**
 * 【取得所有商品】的控制器，此控制器主要行為： 
 * 1.呼叫「取得所有商品」的方法，得到所有商品列表(List)。
 * 2.將所有商品的列表(List)，存成session區間的屬性(Attribute)。
 * 3.將頁面導向「展示商品的頁面」shop.jsp
 */
@WebServlet("/GetAllProducts.do")
public class GetAllProductsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		ArrayList<Products> productList = new ShopService().getAllProducts();

		request.getSession().setAttribute("products", productList);

		response.sendRedirect("/BreadShop/page/shop.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
