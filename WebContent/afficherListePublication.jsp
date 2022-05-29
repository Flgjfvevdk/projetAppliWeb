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
<title>Afficher publications</title>
</head>
<body>
	<%Collection<Publication> listePublications = (Collection<Publication>) request.getAttribute("listePublicat");
	for(Publication p : listePublications){ %>
	<form action="Serv" method ="post">
		<%=p.getCarte().getNom() %> 
		<br>
		votes positifs : <%=p.getVotes() %> 
		<br>
		Créée par : <%=p.getNomCreateur() %>
		<br>
		<%String usernameActif = (String) request.getSession().getAttribute("usernameActif");
		if(usernameActif != null && !p.userAlreadyUpvote(usernameActif)){
			%>
		
		<input type="hidden" name="cible" value=<%=p.getId()%>>
		<input type="submit" name="operation" value="upVote">
		<%} %>
	</form>
		<br><br>
	<%}%>
	<br>
	<A HREF=index.html> Retour au menu.
	</A>
</body>
</html>