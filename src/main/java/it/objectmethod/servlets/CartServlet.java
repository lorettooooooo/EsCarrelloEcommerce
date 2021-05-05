package it.objectmethod.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import it.objectmethod.dao.IArticleDao;
import it.objectmethod.daoimpl.ArticleDaoImpl;
import it.objectmethod.domain.Article;

@WebServlet("/CartServlet")
public class CartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		IArticleDao articleDao = new ArticleDaoImpl();
		Integer userCartId = (Integer) session.getAttribute("userCartId");
		List<Article> userCart = new ArrayList<Article>();
		userCart = articleDao.getUserCart(userCartId);
		Integer cartPrice = articleDao.getTotalPrice(userCart);
		session.setAttribute("cart", userCart);
		session.setAttribute("cartPrice", cartPrice);
		request.getRequestDispatcher("/pages/cart.jsp").forward(request, response);
	}

}
