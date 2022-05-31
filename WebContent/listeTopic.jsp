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
<title>Topic</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="afficherListePublication.css">
</head>
<body>
<script id="replace_with_navbar" src="navbar.js"></script>
<div class="ajoutTopic">
<%if(request.getSession().getAttribute("usernameActif") != null){ %>
<form>
Creez un nouveau topic : <input type="text" name="titre">
<button type="submit" name="operation" value="ajouterUnTopic">Ajouter un topic</button>
</form>
<%} else {%>
	Vous devez être connecté pour créer un topic.
<%} %>
</div>
<br>
<div class="publi">
	<%
	Collection<Topic> listeTopic = (Collection<Topic>) request.getAttribute("listeTopic");
	
	for(Topic t : listeTopic){ %>
	<form action="Serv" method ="post">
		<%=t.getTitre() %>
		<br>
		Créé par : <%=t.getNomCreateur() %> 
		<br>
		<input type="hidden" name="cible" value=<%=t.getId()%>>
		<button type="submit" name="operation" value="entrerDansTopic">Entrer dans le topic</button>

	</form>
		<br><br>
	<%}%>
</div>
<br>
<A HREF=index.html> Retour au menu.
</A>

</body>
</html>