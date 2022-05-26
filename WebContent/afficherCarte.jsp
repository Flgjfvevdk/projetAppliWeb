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
<title>Afficher les cartes</title>
</head>
<body>
	<%Collection<Carte> listeCartes = (Collection<Carte>) request.getAttribute("liste_c");
	for(Carte c : listeCartes){ %>
		<%=c.getNom() %>
		<br>
	<%}%>
</body>
</html>