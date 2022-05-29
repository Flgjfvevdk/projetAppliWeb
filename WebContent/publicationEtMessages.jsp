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
</head>
<body>
<%Publication publication = (Publication) request.getAttribute("publication");
%>
Publication de la carte : <%=publication.getNomCarte() %>
<br> Par : <%=publication.getNomCreateur() %>
<br> Nombre de upvote : <%=publication.getVotes() %>
<br>
<br>
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
	<%}}else {%>
		Aucun message
	<% } %>
	<%String usernameActif = (String) request.getSession().getAttribute("usernameActif");
	if(usernameActif != null){ %>
		<form action="Serv" method ="post">
			<input type="hidden" name="cible" value=<%=publication.getId()%>>
			<input type="text" name="commentaire"> <br>
			<input type="submit" name="operation" value="commenterPublication">
		</form>
	<%} %>
	<br>
	<A HREF=index.html> Retour au menu.
	</A>
</body>
</html>