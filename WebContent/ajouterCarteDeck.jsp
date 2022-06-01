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
<title>Ajouter carte dans deck</title>

<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="ajouterCarteDeck.css">
</head>
<body>
<script id="replace_with_navbar" src="navbar.js" ></script>
<div class="ajouterCarteDeck">
<form method = "post" action = "Serv">
	<% int first = 1;
	Collection<Carte> listeCartesPossedees = (Collection<Carte>) request.getAttribute("liste_c");
	for(Carte c : listeCartesPossedees){ 
		int id = c.getId();%>
		<input type = "radio" name = "carteId" value = "<%=id %>" <% if (first == 1) {%> checked <%}%>> 
		<%=c.getNom() %><br>
		<img src=<%= c.getImage() %> />
		<%first = 0; %><br>
	<%} if(first==0){ %>
	<br>
	<input type = "hidden" value ="ajouterLaCarte" name = "operation">
	<input type = "submit" value = "Ajouter la carte">
	<%} else {%>
	Vous n'avez pas de cartes.
	
	<%} %>
</form>
</div>
<br>
<A HREF=index.html> Retour au menu.
</A>

</body>
</html>
