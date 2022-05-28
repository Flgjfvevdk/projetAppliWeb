<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Authentification</title>
</head>
<body>
<%=request.getAttribute("messageCreationCompte") %>
<form action="Serv" method ="post">
	Pseudonyme : <input type="text" name="pseudo"> <br>
	Mot de passe : <input type="text" name="mdp"> <br>
	<input type="submit" name="operation" value="creerCompte">
</form>
<br>
<br>
<%=request.getAttribute("messageConnectionCompte") %>
<form action="Serv" method ="post">
	Pseudonyme : <input type="text" name="pseudo"> <br>
	Mot de passe : <input type="text" name="mdp"> <br>
	<input type="submit" name="operation" value="seConnecter">
</form>

</body>
</html>