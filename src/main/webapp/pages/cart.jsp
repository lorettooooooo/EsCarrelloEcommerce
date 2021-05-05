<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Carrello</title>
</head>
<body>
<a href="/EsCarrelloEcommerce/InitialiseHome" ><input type = "button" value = "Torna alla home"></a>
<div>
		<H4>Il mio carrello:</H4>
		<table>
			<tr>
				<td>Nome Articolo</td>
				<td>Prezzo</td>
				<td>Quantità</td>
				<td>Pulsante rimuovi</td>
			</tr>
			<c:forEach items="${cart}" var="cartArticle">
	 			<tr>
					<td>${cartArticle.name}</td>
					<td>${cartArticle.price}</td>
					<td>${cartArticle.quantity}</td> 
					<td><a href="/EsCarrelloEcommerce/RemoveFromCartServlet?cartDetailId=${cartArticle.cartArticleId}"><input type = "button" value = "Rimuovi dal carrello"></a></td>
				</tr>
			</c:forEach>
			<tr>
					<td></td>
					<td>totale: ${cartPrice}</td>
					<td></td>
					<td></td> 
				</tr>
		</table>
	</div> 
</body>
</html>