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

<% if((boolean) request.getAttribute("estUserConnecte") && (boolean) request.getAttribute("estCarteDisponible")){
	if((int)request.getAttribute("argentDispo") >= Carte.prixPaquet){%>
		<%Collection<Carte> listeCartesObtenues = (Collection<Carte>) request.getAttribute("cartesObtenues");
		for(Carte c : listeCartesObtenues){ %>
			<%=c.getNom() %>
			<br>
		<%}%>
		
	<% }else {%>
		Vous n'avez pas assez de tokens, un paquet coute <%=Carte.prixPaquet %> tokens.
	<%} %>

<%} else if(! (boolean) request.getAttribute("estUserConnecte")){%>
	Vous devez être connecté pour acheter un paquet.
<%}else {%>
	Aucune carte n'est disponible. Créez en une !
<%}%>
	
	<br>
	<A HREF=index.html> Retour au menu.
	</A>
</body>
</html>