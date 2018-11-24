<%@ page pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib uri="http://www.springframework.org/tags"	  prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt"		  prefix="c" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<!DOCTYPE HTML>
<html>
<head>
	<title>Bienvenido a SmallNotes</title>
  	<script type="text/javascript" src="<spring:url value='/resources/js/jquery.min.js'/>"></script>
  	<script type="text/javascript" src="<spring:url value='/resources/js/jquery.validate.min.js'/>"></script> 
  	<script type="text/javascript" src="<spring:url value='/resources/js/messages_es.js'/>"></script>   	
  	 	
  	<script type="text/javascript">
	$(document).ready(function() {
		
<security:authorize ifNotGranted="ROLE_USER">
		$("#login").validate();
</security:authorize>
	});
  	</script>
  	<style type="text/css">
  		header {color:white; background-color:#4499aa; padding:.5em;}
  		#contenido h2 {color:#555599;padding:.1em}
  		footer {color:white; background-color:#44aadd; padding:.5em; margin-top:2em}
  	</style>
</head>
<body>
	<header>
		<h1>Bienvenido a SmallNotes</h1>
		<p>Ejemplo de Api Rest protegida por OAuth2</p>
	</header>
	
	<div id="contenido">
		<security:authorize ifNotGranted="ROLE_USER">
			<div id="acceso">
				<div class="iniciosesion">
					<h2>Inicie sesión</h2>
					<form id="login" name="login" action="j_spring_security_check" method="post" class="cmxform">
						<fieldset>
							<legend>E-mail/contraseña:</legend>
							<input id="j_username" name="j_username"  type="text" class="required"  placeholder="email" size="25"/>
							<input id="j_password"  name="j_password"  type="password" class="required" placeholder="contraseña" size="8"/>
							<p><input type="submit" value="Aceptar"/></p>
							
							<p>[Usuario de prueba: user1@smallnotes.es / 1234]</p>
						</fieldset>
					</form>
					<div id="messageBox">
						<c:if test="${not empty SPRING_SECURITY_LAST_EXCEPTION.message}"><em class="error" style="float:left">Credenciales no válidas</em></c:if>
					</div>
				</div>
			</div>
		</security:authorize>
	</div>
	
	<footer>
		<p>Desarrollador por:</p>
		<ul>
			<li>@author: Carlos García Pérez</li>
			<li>@see:    <a href="http://carlos-garcia.es">http://carlos-garcia.es</a></li>
		</ul>
	</footer>
	
</body>
</html>