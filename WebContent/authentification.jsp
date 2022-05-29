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
<title>Authentification</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="authentification.css">
</head>
<body>
<script id="replace_with_navbar" src="navbar.js"></script>
<% //Compte compteActif = (Compte) request.getSession().getAttribute("compteActif");
String usernameActif = (String) request.getSession().getAttribute("usernameActif");
if(usernameActif != null){%>
	Vous êtes déjà connecté en tant que : <%=usernameActif %>
	<form action="Serv" method ="post">
	<input type="submit" name="operation" value="seDeconnecter">
	</form>
<%}else {%>



<div class="creation">
	<h1><%=request.getAttribute("messageCreationCompte") %></h1>
	<form action="Serv" method ="post">
		<div>
		<label for="pseudo">Pseudonyme</label>
		<input type="text" id="pseudo" name="pseudo" placeholder="youpi31" required> <br>
		</div>
		<div>
		<label for="mdp">Mot de passe</label>
		<input type="text" id="mdp" name="mdp" placeholder="azerty123" required> <br>
		</div>
		<div>
		<button type="submit" name="operation" value="creerCompte">Créer le compte</button>
		</div>
	</form>
</div>
<br>
<div class="connection">
	<h2><%=request.getAttribute("messageConnectionCompte") %></h2>
	<form action="Serv" method ="post">
		<div>
		<label for="pseudo">Pseudonyme</label>
		<input type="text" id="pseudo" name="pseudo" placeholder="youpi31" required> <br>
		</div>
		<div>
		<label for="mdp">Mot de passe</label>
		<input type="text" id="mdp" name="mdp" placeholder="azerty123" required> <br>
		</div>
		<div>
		<button type="submit" name="operation" value="seConnecter">Se connecter</button>
		</div>
	</form>
</div>
<%}%>

<br>
<A HREF=index.html> Retour au menu.
</A>
</body>
</html>