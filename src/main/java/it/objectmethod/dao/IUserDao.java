package it.objectmethod.dao;

import it.objectmethod.domain.User;


public interface IUserDao {
		User getUser(String username, String password);
		Integer getUserCart(Integer id);
		void setUserCart(Integer id);
}
