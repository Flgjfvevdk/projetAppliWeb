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
<title>Insert title here</title>
</head>
<body>
<% Compte compteActif = (Compte) request.getSession().getAttribute("compteActif");
if(compteActif != null){%>
	Jeton(s) disponibles : <%=compteActif.getArgent() %>
	<br>
	Vos cartes possédées : 
	<%for(Carte c : compteActif.getCartes()){ %>
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