package it.objectmethod.dao;

import java.sql.SQLException;
import java.util.List;

import it.objectmethod.domain.Article;

public interface IArticleDao {
	public List<Article> getArticleList() throws SQLException;

	public void addArticleToCart(Integer cartId, Integer articleId) throws SQLException; // void, fa partire un if di
																							// controllo con item in
																							// cart, aggiunge o modifica
																							// la

	public boolean checkForItemInCart(Integer cartId, Integer articleId) throws SQLException; // ritorna ID dell'oggetto
																								// nel carrello nel DB,
																								// ritorna null se non
																								// esiste

	public void removeArticleFromCart(Integer cartDetailId) throws SQLException; // void, leva l'oggetto dal carrello
																					// nel DB, implica la
	// sua esistenza

	public void updateCartArticle(Integer cartId, Integer articleId) throws SQLException; // void, modifica la quantità
																							// dell'oggetto nel DB dato
																							// ID di oggetto e ID
																							// carrello, implica la sua
																							// esistenza

	public List<Article> getUserCart(Integer cartId); // ritorna la lista da stampare nella pagina carrello

	public Integer getTotalPrice(List<Article> list); // ritorna il totale del carrello nella pagina carrello
}