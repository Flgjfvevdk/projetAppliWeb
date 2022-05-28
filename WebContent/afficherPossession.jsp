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
<title>Insert title here</title>
</head>
<body>
<% //Compte compteActif = (Compte) request.getSession().getAttribute("compteActif");
String usernameActif = (String) request.getSession().getAttribute("usernameActif");
if(usernameActif != null){%>
	Jeton(s) disponibles : <%=(int) request.getAttribute("argents") %>
	<br>
	Vos cartes possédées : 
	<%
	Collection<Carte> cartesPossedees = (Collection<Carte>) request.getAttribute("cartePossedee");
	//Collection<Carte> cartesPossedees = ((Compte) request.getSession().getAttribute("compteActif")).getCartes();
	for(Carte c : cartesPossedees){ %>
		<%=c.getNom()%>
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