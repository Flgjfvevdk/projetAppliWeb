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
</head>
<body>
<form method = "post" action = "Serv">
	<% int first = 1;
	Collection<Deck> listeDecks = (Collection<Deck>) request.getAttribute("liste_d");
	for(Deck d : listeDecks){ 
		int id = d.getId();%>
		<input type = "radio" name = "deckId" value = "<%=id %>" <% if (first == 1) {%> checked <%}%>> 
		<%=d.getNom() %>
		<%first = 0; %><br>
	<%}%>
	<br>
	<input type = "hidden" value ="VoirDeck" name = "operation">
	<input type = "submit" value = "Voir le deck">
</form>
<A HREF=index.html> Retour au menu.
</A>

</body>
</html>
