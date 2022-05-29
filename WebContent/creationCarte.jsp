<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="appliWebProj.*"
    import="java.util.Collection"
    errorPage=""
    isThreadSafe="true"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Création d'une carte</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="creationCarte.css">
</head>
<body>
<script id="replace_with_navbar" src="navbar.js"></script>
<% //Compte compteActif = (Compte) request.getSession().getAttribute("compteActif");
String usernameActif = (String) request.getSession().getAttribute("usernameActif");
if(usernameActif != null){%>
<div class="creation">
	<h1>Créez votre carte !</h1>
	<form action="Serv" method ="post" enctype="multipart/form-data">
		<div>
		<label for="nom">Nom de la carte</label>
		<input type="text" id="nom" name="nomCarte" placeholder="8 de pic" required> <br>
		</div>
		<div>
		<label for="image">Importez l'image de la carte</label>
		<input type="file" id="image" name="imageCarte" required> <br>
		</div>
		<button type="submit" name="operation" value="creerCarte">Soumettre la carte</button>
	</form>
</div>
<%}else {%>
	Vous devez être connecté pour créer une carte.
	<script src="alerteConnection.js"></script>

<%} %>

<br>
<A HREF=index.html> Retour au menu.
</A>
</body>
</html>