<%@ page import="org.springframework.security.core.AuthenticationException" %>
<%@ page import="org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter" %>
<%@ page import="org.springframework.security.oauth2.common.exceptions.UnapprovedClientAuthenticationException" %>
<%@ taglib prefix="authz" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1"/>
  <title>SmallNotes</title>
 	<style type="text/css">
		header {color:white; background-color:#4499aa; padding:.5em;}
		#contenido h2 {color:#555599;padding:.1em}
	</style>
  	
</head>
<body>
	<header>
		<h1>SmallNotes. Autorizaci�n de acceso de una aplicaci�n de terceros</h1>
	</header>
	
	<div id="contenido">
	    <c:if test="${!empty sessionScope.SPRING_SECURITY_LAST_EXCEPTION}">
	      <div class="error">
	        <h2>Error!</h2>
	        <p>Acceso no concedido. </p>
	      </div>
	    </c:if>
	    <c:remove scope="session" var="SPRING_SECURITY_LAST_EXCEPTION"/>
	
	    <authz:authorize ifAllGranted="ROLE_USER">
	      <h2>�Autoriza el acceso?</h2>
	      <p>La aplicaci�n <c:out value="${client.clientId}"/>" Solicita permisos para acceder a los siguientes recursos:</p>
		  
		  <ul>
		  	<c:forEach items="${client.resourceIds}" var="resource">
		  		<li><c:out value="${resource}"/></li>
		  	</c:forEach>
		  </ul>
		  <p>Con los siguientes permisos:</p>
		  <ul>
		  	<c:forEach items="${auth_request.scope}" var="scope">
		  		<li><c:out value="${scope}"/></li>
		  	</c:forEach>
		  </ul>
			
	      <form id="confirmationForm" action="<c:url value="/oauth/authorize"/>" method="post">
				<input name="user_oauth_approval" value="true" type="hidden"/>
	        	<input name="authorize" value="Si, Acepto!" type="submit"/>
	      </form>
	
	      <form id="denialForm" name="denialForm" action="<c:url value="/oauth/authorize"/> method="post">
				<input name="user_oauth_approval" value="true" type="hidden"/>
				<input name="deny" value="No Acepto" type="submit"/>
	      </form>
      </div>
    </authz:authorize>
</body>
</html>
