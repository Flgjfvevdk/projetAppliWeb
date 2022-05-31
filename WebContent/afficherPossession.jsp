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
<title>Afficher possession</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="afficherPossession.css">
</head>
<body>
<div class="nav">
<script id="replace_with_navbar" src="navbar.js"></script>
</div>
<div class="poss">
<h1>Mes possessions</h1>
<% //Compte compteActif = (Compte) request.getSession().getAttribute("compteActif");
String usernameActif = (String) request.getSession().getAttribute("usernameActif");
if(usernameActif != null){%>
	Jeton(s) disponibles : <%=(int) request.getAttribute("argents") %>
	<br>
	Vos cartes possédées : <br>
	<%
	Collection<Carte> cartesPossedees = (Collection<Carte>) request.getAttribute("cartePossedee");
	//Collection<Carte> cartesPossedees = ((Compte) request.getSession().getAttribute("compteActif")).getCartes();
	for(Carte c : cartesPossedees){ %>
		<%=c.getNom()%><br>
		<img src=<%= c.getImage() %> /> 
		<br>
	<%}%>
	
<%}else {%>
	
	Vous devez être connecté pour consulter vos possessions.
	<script src="alerteConnection.js"></script>
<%} %>
</div>
<br>
<A HREF=index.html> Retour au menu.
</A>
</body>
</html>