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
<title>Ouverture paquet</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="ouverturePaquetCartes.css">
</head>
<body>
<script id="replace_with_navbar" src="navbar.js"></script>
<div class"tirage">
<% if((boolean) request.getAttribute("estUserConnecte") && (boolean) request.getAttribute("estCarteDisponible")){
	if((int)request.getAttribute("argentDispo") >= Carte.prixPaquet){%>
	<h1>Tirage</h1>
		<%Collection<Carte> listeCartesObtenues = (Collection<Carte>) request.getAttribute("cartesObtenues");
		
		for(Carte c : listeCartesObtenues){ %>
		
			<%=c.getNom() %>
			<br>
			<img src=<%= c.getImage() %> /> <br>
		<%}%>
		
	<% }else {%>
		Vous n'avez pas assez de tokens, un paquet coute <%=Carte.prixPaquet %> tokens.
	<%} %>
</div>
<%} else if(! (boolean) request.getAttribute("estUserConnecte")){%>
	Vous devez être connecté pour acheter un paquet.
	<script src="alerteConnection.js"></script>
<%}else {%>
	Aucune carte n'est disponible. Créez en une !
<%}%>
	
	<br>
	<A HREF=index.html> Retour au menu.
	</A>
</body>
</html>