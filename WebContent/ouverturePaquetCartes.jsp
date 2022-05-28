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

<% //Compte compteActif = (Compte) request.getSession().getAttribute("compteActif");
String usernameActif = (String) request.getSession().getAttribute("usernameActif");
if(usernameActif != null){%>
	<%Collection<Carte> listeCartesObtenues = (Collection<Carte>) request.getAttribute("cartesObtenues");
	for(Carte c : listeCartesObtenues){ %>
		<%=c.getNom() %>
		<br>
	<%}%>
	
<%}else {%>
	Vous devez être connecté pour consulter vos possessions.

<%} %>
	
	<br>
	<A HREF=index.html> Retour au menu.
	</A>
</body>
</html>