<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Home</title>
</head>
<body>
	<p>Ciao, ${loggedUser.getUsername()}</p>
	<a href="/EsCarrelloEcommerce/CartServlet" ><input type = "button" value = "vai al mio carrello"></a>
	<div>
		<h1>LISTA DI OGGETTI:</h1>
		<table>
			<tr>
				<td>Nome Articolo</td>
				<td>Prezzo</td>
				<td>Disponibilità</td>
				<td>Pulsante aggiungi</td>
			</tr>
			<c:forEach items="${purchasableArticles}" var="article">
				<tr>
					<td>${article.name}</td>
					<td>${article.price}</td>
					<td>${article.avaiability}</td>
					<td><a href="/EsCarrelloEcommerce/AddToCartServlet?articleId=${article.id}"><input type = "button" value = "Aggiungi al carrello"></a></td>
				</tr>
			</c:forEach>
		</table>
	</div>
	

</body>
</html>