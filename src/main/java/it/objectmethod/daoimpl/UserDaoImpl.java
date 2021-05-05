package it.objectmethod.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import it.objectmethod.config.ConnectionFactory;
import it.objectmethod.dao.IUserDao;
import it.objectmethod.domain.User;

public class UserDaoImpl implements IUserDao {

	@Override
	public User getUser(String username, String password) { // controlla se esiste l'user e lo
																				// ritorna, altirmenti ritorna user
																				// vuoto
		User user = null;
		Connection connection = ConnectionFactory.getConnection();
		PreparedStatement userSearchStatement;
		try {
			userSearchStatement = connection.prepareStatement("SELECT * FROM utente u WHERE nome_utente = ? AND BINARY password = ?");
			userSearchStatement.setString(1, username);
			userSearchStatement.setString(2, password);
			ResultSet resultSet = userSearchStatement.executeQuery();
			while (resultSet.next()) {
				user = new User();
				Integer userId = resultSet.getInt("id_utente");
				user.setId(userId);
				user.setUsername(username);
				userSearchStatement.close();
				connection.close();
			} 
			
			resultSet.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		return user;
	}

	@Override
	public Integer getUserCart(Integer userId) {
		Connection connection = ConnectionFactory.getConnection();
		PreparedStatement cartSearchStatement;
		Integer userCart = null;
		try {
			cartSearchStatement = connection.prepareStatement("SELECT c.id_carrello FROM carrello c WHERE id_utente = ?");
			cartSearchStatement.setInt(1, userId);
			ResultSet resultSet = cartSearchStatement.executeQuery();
			while (resultSet.next()) {
			userCart = resultSet.getInt("id_carrello");}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return userCart;
	}

	@Override
	public void setUserCart(Integer id) {
		// TODO Auto-generated method stub
		
	}

}
