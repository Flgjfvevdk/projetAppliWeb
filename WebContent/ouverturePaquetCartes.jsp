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
<title>Ouverture paquet</title>
</head>
<body>
<%Collection<Carte> listeCartesObtenues = (Collection<Carte>) request.getAttribute("cartesObtenues");
	for(Carte c : listeCartesObtenues){ %>
		<%=c.getNom() %>
		<br>
	<%}%>
	
	
	<br>
	<A HREF=index.html> Retour au menu.
	</A>
</body>
</html>