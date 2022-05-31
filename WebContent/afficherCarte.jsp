<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="appliWebProj.*"
    import="java.util.Collection"
    import="java.lang.*"
    errorPage=""
    isThreadSafe="true"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Afficher les cartes</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="afficherCarte.css">
</head>
<body>
<script id="replace_with_navbar" src="navbar.js" ></script>
<div class="listeCartes">
<h1>Liste des cartes</h1>
	<%Collection<Carte> listeCartes = (Collection<Carte>) request.getAttribute("liste_c");
	for(Carte c : listeCartes){ %>
		<h2><%=c.getNom() %><br></h2>
		<img src=<%= c.getImage() %> /> <br>
    		
	<%}%>
</div>
	<br>
	<A HREF=index.html> Retour au menu.
	</A>
</body>
</html>