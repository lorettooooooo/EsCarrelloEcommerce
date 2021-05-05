package it.objectmethod.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import it.objectmethod.config.ConnectionFactory;
import it.objectmethod.dao.IArticleDao;
import it.objectmethod.domain.Article;

public class ArticleDaoImpl implements IArticleDao {

	@Override
	public List<Article> getArticleList() throws SQLException {
		List<Article> articleList = new ArrayList<Article>();
		Article article = null;
		Connection connection = ConnectionFactory.getConnection();
		PreparedStatement userSearchStatement = connection
				.prepareStatement("SELECT * FROM articolo a WHERE disponibilita >0");
		ResultSet resultSet = userSearchStatement.executeQuery();
		while (resultSet.next()) {
			article = new Article();
			Integer id = resultSet.getInt("id_articolo");
			Integer avaiability = resultSet.getInt("disponibilita");
			String code = resultSet.getString("codice_articolo");
			String name = resultSet.getString("nome_articolo");
			Integer price = resultSet.getInt("prezzo_unitario");
			article.setId(id);
			article.setAvaiability(avaiability);
			article.setCode(code);
			article.setName(name);
			article.setPrice(price);
			articleList.add(article);
		}
		resultSet.close();
		userSearchStatement.close();
		connection.close();
		return articleList;
	}

	@Override
	public void addArticleToCart(Integer cartId, Integer articleId) throws SQLException {
		Connection connection = ConnectionFactory.getConnection();
		PreparedStatement statement = connection.prepareStatement(
				"INSERT INTO carrello_dettaglio (id_carrello, quantita, id_articolo) VALUES (?, 1, ?)");
		statement.setInt(1, cartId);
		statement.setInt(2, articleId);
		statement.execute();
		statement.close();
		connection.close();

	}

	@Override
	public boolean checkForItemInCart(Integer cartId, Integer articleId) throws SQLException {

		Connection connection = ConnectionFactory.getConnection();
		PreparedStatement statement = connection.prepareStatement(
				"SELECT * FROM carrello_dettaglio cd WHERE cd.id_carrello = ? AND cd.id_articolo = ?");
		boolean cartDetailId = false;
		statement.setInt(1, cartId);
		statement.setInt(2, articleId);
		ResultSet resultSet = statement.executeQuery();
		while (resultSet.next()) {
			cartDetailId = true;
		}
		statement.close();
		resultSet.close();
		connection.close();
		return cartDetailId;
	}

	@Override
	public void removeArticleFromCart(Integer cartDetailId) throws SQLException {
		Connection connection = ConnectionFactory.getConnection();
		PreparedStatement statement = connection
				.prepareStatement("DELETE FROM carrello_dettaglio cd WHERE cd.id_carrello_dettaglio = ?");
		statement.setInt(1, cartDetailId);
		statement.executeUpdate();
		statement.close();
		connection.close();
	}

	@Override
	public void updateCartArticle(Integer cartId, Integer articleId) throws SQLException {
		Connection connection = ConnectionFactory.getConnection();
		PreparedStatement getCurrValueStatement = connection.prepareStatement(
				"SELECT quantita FROM carrello_dettaglio " + "WHERE id_carrello = ? AND id_articolo = ?");
		getCurrValueStatement.setInt(1, cartId);
		getCurrValueStatement.setInt(2, articleId);
		ResultSet resultSet = getCurrValueStatement.executeQuery();
		Integer quantity = null;
		while (resultSet.next()) {
			quantity = resultSet.getInt("quantita");
			quantity = quantity + 1;
		}
		PreparedStatement statement = connection.prepareStatement(
				"UPDATE carrello_dettaglio " + "SET quantita = ? " + "WHERE id_carrello = ? AND id_articolo = ?");
		statement.setInt(1, quantity);
		statement.setInt(2, cartId);
		statement.setInt(3, articleId);
		statement.executeUpdate();
		statement.close();
		resultSet.close();
		connection.close();
	}

	@Override
	public List<Article> getUserCart(Integer cartId) {
		List<Article> userArticleList = new ArrayList<Article>();
		Article article = null;
		Connection connection = ConnectionFactory.getConnection();
		PreparedStatement userCartSearchStatement;
		try {
			userCartSearchStatement = connection.prepareStatement(
					"SELECT c.id_carrello_dettaglio, c.quantita, a.nome_articolo, a.id_articolo, a.prezzo_unitario FROM carrello_dettaglio c INNER JOIN articolo a ON c.id_articolo = a.id_articolo WHERE c.id_carrello =?");
			userCartSearchStatement.setInt(1, cartId);
			ResultSet resultSet = userCartSearchStatement.executeQuery();
			while (resultSet.next()) {
				article = new Article();

				Integer cartArticleId = resultSet.getInt("c.id_carrello_dettaglio");
				Integer id = resultSet.getInt("a.id_articolo");
				Integer quantity = resultSet.getInt("c.quantita");
				String name = resultSet.getString("a.nome_articolo");
				Integer price = resultSet.getInt("a.prezzo_unitario");
				article.setId(id);
				article.setQuantity(quantity);
				article.setCartArticleId(cartArticleId);
				article.setName(name);
				article.setPrice(price);
				userArticleList.add(article);
			}
			resultSet.close();
			userCartSearchStatement.close();
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return userArticleList;
	}

	@Override
	public Integer getTotalPrice(List<Article> list) {
		Integer tot = 0;
		for (int i = 0; i < list.size(); i++) {
			Integer articlePrice = list.get(i).getPrice();
			Integer articleQuantity = list.get(i).getquantity();
			tot = tot + (articlePrice * articleQuantity);
		}
		return tot;
	}

}
