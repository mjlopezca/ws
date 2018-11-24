<%@ page pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib uri="http://www.springframework.org/tags"	  prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt"		  prefix="c" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<spring:url value="/logout"	  var="goToLogoutURL"/>
<spring:url value="/"	  	  var="goToLoginURL"/>


<!DOCTYPE HTML>
<html>
<head>
	<title>Bienvenido a SmallNotes</title>
  	</script>
  	<style type="text/css">
  		header {color:white; background-color:#4499aa; padding:.5em;}
  	</style>
</head>
<body>
	<header>
		<h1>Bienvenido a SmallNotes</h1>
	</header>
	<div id="contenido">
		<security:authorize ifNotGranted="ROLE_USER">
			<p>No has iniciado sessión.</p>
			<p><a href="${goToLoginURL}">Iniciar sessión</a></p>
		</security:authorize>
		<security:authorize ifAllGranted="ROLE_USER">
			<p>Has iniciado sessión correctamente, pero esta aplicación no tiene niguna pantalla que no sea extrictamente necesaria para el ejemplo de OAuth2 y Rest</p>
			<p><a href="${goToLogoutURL}">Cerrar sessión</a></p>
		</security:authorize>
	</div>
</body>
</html>