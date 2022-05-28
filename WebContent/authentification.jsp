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
<title>Authentification</title>
</head>
<body>
<% Compte compteActif = (Compte) request.getSession().getAttribute("compteActif");
if(compteActif != null){%>
	Vous êtes déjà connecté, et vous êtes : <%=compteActif.getNom() %>
	<form action="Serv" method ="post">
	<input type="submit" name="operation" value="seDeconnecter">
	</form>
<%}else {%>


<%=request.getAttribute("messageCreationCompte") %>
<form action="Serv" method ="post">
	Pseudonyme : <input type="text" name="pseudo"> <br>
	Mot de passe : <input type="text" name="mdp"> <br>
	<input type="submit" name="operation" value="creerCompte">
</form>
<br>
<br>
<%=request.getAttribute("messageConnectionCompte") %>
<form action="Serv" method ="post">
	Pseudonyme : <input type="text" name="pseudo"> <br>
	Mot de passe : <input type="text" name="mdp"> <br>
	<input type="submit" name="operation" value="seConnecter">
</form>
<%}%>

<br>
<A HREF=index.html> Retour au menu.
</A>
</body>
</html>