package tw.com.eeit.breadshop.user.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import tw.com.eeit.breadshop.user.model.bean.Users;
import tw.com.eeit.breadshop.user.service.UsersService;

/**
 * 【使用者登入】的控制器，此控制器主要行為： 
 * 1.取得client傳來的帳號(account)、密碼(password)、是否記住我(remeberMe)。
 * 2.呼叫「驗證登入」的方法，傳入帳號與密碼，得到登入結果。
 * 3.若登入成功，則先進行「記住我」的處理；
 *   之後將登入資訊設為session的屬性(Attribute)以供其他程式存取，最後將頁面導向商品頁面shop.jsp。
 * 4.若登入失敗，則於request區間新增一筆屬性(Attribute)「登入失敗訊息」，並將頁面調派請求(Dispatcher)回登入頁面login.jsp。
 */
@WebServlet("/UserLogin.do")
public class UserLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String account = request.getParameter("account");
		String password = request.getParameter("password");
		String remeberMe = request.getParameter("remeberMe");

		HttpSession session = request.getSession();

		//請將滑鼠移到login上，查看登入方法的使用方式
		Users u = new UsersService().login(new Users(account, password));
		
		if (u != null) { //登入成功
			if (remeberMe != null) { //有勾選「記住我」的處理
				session.setMaxInactiveInterval(60 * 60 * 24 * 7);
				for (Cookie c : request.getCookies()) {
					if (c.getName().equals("JSESSIONID")) {
						c.setMaxAge(60 * 60 * 24 * 7);
						response.addCookie(c);
					}
				}
			}
			
			session.setAttribute("user", u);
			response.sendRedirect("/BreadShop/page/shop.jsp");
		} else {
			request.setAttribute("message", "帳號不存在或密碼錯誤，請重新登入!");
			request.getRequestDispatcher("page/login.jsp").forward(request, response);
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
