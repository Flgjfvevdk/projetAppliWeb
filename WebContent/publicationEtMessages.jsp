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
<title>Publication et messages</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="publicationEtMessages.css">
</head>
<body>
<div class="nav">
<script id="replace_with_navbar" src="navbar.js" ></script>
</div>
<%Publication publication = (Publication) request.getAttribute("publication");
%>
<div class="publi">
Publicateur : <%=publication.getNomCarte() %>
<br> <form><img src=<%= publication.getCarte().getImage() %> /> </form>
</div>
<div class="commentaire">
<br> Par : <%=publication.getNomCreateur() %>
<br> Nombre de upvote : <%=publication.getVotes() %>
<br>

_________________
<br></div>
<div class="publi">Commentaires<br></div>
<div class="commentaire">
	<%
	Collection<Message> listMessages = (Collection<Message>) request.getAttribute("listeMess");
	if(listMessages != null){
		
	
	for(Message m : listMessages){ %>
		_________________
		<br>
		<%=m.getTexte() %> 
		<br>
		écrit par : <%=m.getCreateur().getNom() %>
		<br>
	<%}%>
	_________________
	<br>
	<% }else {%>
		Aucun message
	<% } %>
	<%String usernameActif = (String) request.getSession().getAttribute("usernameActif");
	if(usernameActif != null){ %>
		<form action="Serv" method ="post">
			<div>
			<input type="hidden" name="cible" value=<%=publication.getId()%>>
			<label for="com">Commentaire</label>
			<input type="text" id="com" name="commentaire" placeholder="Trop bien" required> <br>
			</div>
			<button type="submit" name="operation" value="commenterPublication">Soumettre le commentaire</button>
		</form>
	<%} %>
</div>
<br>
<A HREF=index.html> Retour au menu.
</A>
</body>
</html>