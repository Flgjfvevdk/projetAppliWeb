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
<title>Voir un deck</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="afficherCarte.css">
</head>
<body>
<script id="replace_with_navbar" src="navbar.js" ></script>
	<%Deck d = (Deck) request.getAttribute("deck");
	for(Carte c : d.getCartes()){ %>
		<%=c.getNom() %><br>
		<img src=<%= c.getImage() %> /> <br>
		
	<%}%>
	<br>
	<form method = "post" action="Servt"></form>
	<A HREF=index.html> Retour au menu.
	</A>
</body>
</html>
