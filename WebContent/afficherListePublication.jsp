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
		<%=p.getCarte().getNom() %> votes positifs : <%=p.getVotes() %> 
		<br>
	<%}%>
	<br>
	<A HREF=index.html> Retour au menu.
	</A>
</body>
</html>