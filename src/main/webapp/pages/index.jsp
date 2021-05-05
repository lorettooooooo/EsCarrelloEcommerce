<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Home</title>
</head>
<body>
	<div class="centeredForm">
		<form action="/EsCarrelloEcommerce/Login">
			<input type="text" placeholder="Nome utente" name="username">
			<br> <input type="password" placeholder="Password"
				name="password"> <input type="submit" value="Login">
		</form>
		<p>${errorMessage}</p>
	</div>
	<%
	for (int i = 0; i < 10; i++) {
		out.println(i);
	}
	%>
</body>
</html>