package it.objectmethod.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import it.objectmethod.dao.IUserDao;
import it.objectmethod.daoimpl.UserDaoImpl;
import it.objectmethod.domain.User;

@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String forwardedPage = null;
		IUserDao userDao = new UserDaoImpl();
		HttpSession session = request.getSession();
		try {
			User user = userDao.getUser(username, password);
			if (user != null) {
				forwardedPage = "/InitialiseHome";
				session.setAttribute("loggedUser", user);				
			}else{
				forwardedPage = "/pages/index.jsp";
				request.setAttribute("errorMessage", "Login o password errati");
			}
		} catch (Exception e) {
			System.out.println("entra in sbatti ");
			e.printStackTrace();
		}
		
		request.getRequestDispatcher(forwardedPage).forward(request, response);

	}

}
