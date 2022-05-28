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
<% //Compte compteActif = (Compte) request.getSession().getAttribute("compteActif");
String usernameActif = (String) request.getSession().getAttribute("usernameActif");
if(usernameActif != null){%>
	<form action="Serv" method ="post" enctype="multipart/form-data">
	nom : <input type="text" name="nomCarte"> <br>
	image : <input type="file" name="imageCarte"> <br>
	<input type="submit" name="operation" value="creerCarte">
</form>
<%}else {%>
	Vous devez être connecté pour créer une carte.

<%} %>

<br>
<A HREF=index.html> Retour au menu.
</A>
</body>
</html>