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
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="afficherListePublication.css">
</head>
<body>
<div class="nav">
<script id="replace_with_navbar" src="navbar.js"></script>
</div>
<div class="publi">
<h1>Publications</h1>
	<%
	Collection<Publication> listePublications = (Collection<Publication>) request.getAttribute("listePublicat");
		
	for(Publication p : listePublications){ %>
	<form action="Serv" method ="post">
		<%=p.getCarte().getNom() %> <br>
		<img src=<%= p.getCarte().getImage() %> /> 
		<br>
		votes positifs : <%=p.getVotes() %> 
		<br>
		Créée par : <%=p.getNomCreateur() %>
		<br>
		<input type="hidden" name="cible" value=<%=p.getId()%>>
		<button type="submit" name="operation" value="afficherDetailsPublication">Détails publications</button>
		<%String usernameActif = (String) request.getSession().getAttribute("usernameActif");
		if(usernameActif != null && !p.userAlreadyUpvote(usernameActif)){
			%>
		
		<button type="submit" name="operation" value="upVote">Up voter</button>
		<%} %>
	</form>
		<br><br>
	<%}%>
</div>
	<br>
	<A HREF=index.html> Retour au menu.
	</A>
</body>
</html>