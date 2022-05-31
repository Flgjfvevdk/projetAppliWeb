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
<title>Afficher les decks</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="afficherDeck.css">
</head>
<body>
<script id="replace_with_navbar" src="navbar.js" ></script>
<div class="listeDeck">
<form method = "post" action = "Serv">
	<% int first = 1;
	Collection<Deck> listeDecks = (Collection<Deck>) request.getAttribute("liste_d");
	for(Deck d : listeDecks){ 
		int id = d.getId();%>
		<h1><input type = "radio" name = "deckId" value = "<%=id %>" <% if (first == 1) {%> checked <%}%>> 
		<%=d.getNom() %></h1>
		<%first = 0; %><br>
	<%} if(first==0){ %>
	<input type = "hidden" value ="VoirDeck" name = "operation">
	<button type="submit" >Voir le deck</button>
	<%} else {%>
	Vous n'avez pas de deck.
	
	<%} %>
</form>
</div>
<br>
<A HREF=index.html> Retour au menu.
</A>

</body>
</html>
