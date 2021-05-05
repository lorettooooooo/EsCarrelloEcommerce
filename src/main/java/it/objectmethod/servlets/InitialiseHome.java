package it.objectmethod.servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import it.objectmethod.dao.IArticleDao;
import it.objectmethod.dao.IUserDao;
import it.objectmethod.daoimpl.ArticleDaoImpl;
import it.objectmethod.daoimpl.UserDaoImpl;
import it.objectmethod.domain.Article;
import it.objectmethod.domain.User;

@WebServlet("/InitialiseHome")
public class InitialiseHome extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		IArticleDao articleDao = new ArticleDaoImpl();
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("loggedUser");
		IUserDao userDao = new UserDaoImpl();
		List<Article> articleList = new ArrayList<Article>();
		try {
			articleList = articleDao.getArticleList();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		Integer userCartId;
		userCartId = userDao.getUserCart(user.getId());
		session.setAttribute("userCartId", userCartId);
		request.setAttribute("purchasableArticles", articleList);
		request.getRequestDispatcher("/pages/home.jsp").forward(request, response);
		}
}
