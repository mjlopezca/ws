<%@ page pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib uri="http://www.springframework.org/tags"	  prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt"		  prefix="c" %>
<%@ taglib prefix="authz" uri="http://www.springframework.org/security/tags" %>

<!DOCTYPE HTML>
<html>
<head>
	<title>Bienvenido a SmallNotesExternalWebApp</title>
  	<style type="text/css">
  		body {background-color:#eaeaea}
  	</style>
</head>
<body>
	<h1>Bienvenido a SmallNotesExternalWebApp</h1>
	
	<div id="contenido">
		<authz:authorize ifNotGranted="IS_AUTHENTICATED_FULLY">
			<div id="acceso">
				<div class="iniciosesion">
					<h2>Inicie sesión</h2>
					<form id="login" name="login" action="login.do" method="post" class="cmxform">
						<div class="field">
							<label for="j_username">Email</label> 
							<input id="j_username" name="j_username"  type="text"/>
						</div>
						<div class="field">
							<label for="j_password">Contraseña</label>
							<input id="j_password"  name="j_password"  type="password"/>
							<p><input type="submit" value="Aceptar"/></p>
						</div>
						
						<p>[Usuario de prueba: user1 / pwd]</p>
					</form>
					<div id="messageBox">
						<c:if test="${not empty SPRING_SECURITY_LAST_EXCEPTION.message}"><em class="error" style="float:left">Credenciales no válidas</em></c:if>
					</div>
				</div>
			</div>
		</authz:authorize>
	</div>
</body>
</html>