package it.objectmethod.servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import it.objectmethod.dao.IArticleDao;
import it.objectmethod.daoimpl.ArticleDaoImpl;


@WebServlet("/AddToCartServlet")
public class AddToCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Integer articleId = Integer.parseInt(request.getParameter("articleId"));
		HttpSession session = request.getSession();
		IArticleDao articleDao = new ArticleDaoImpl();
		Integer userCartId = (Integer) session.getAttribute("userCartId");
		boolean doesArticleCartIdExist = false;
		try {
			doesArticleCartIdExist = articleDao.checkForItemInCart(userCartId, articleId);
			if(doesArticleCartIdExist) {//controllo se esiste un'implementazione dell'articolo e del carrello dell'utente, nella tabella carrello dettaglio
				articleDao.updateCartArticle(userCartId, articleId);
			} else {
				articleDao.addArticleToCart(userCartId, articleId);
			}			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		request.getRequestDispatcher("/InitialiseHome").forward(request, response);
	}

}
