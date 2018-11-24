<%@ page pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib uri="http://www.springframework.org/tags"	  prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt"		  prefix="c" %>
<spring:url value="/logout" var="goToLogoutURL"/>
<html>
<head>
	<title>Cliente Web de Prueba de SmallNotes</title>
</head>
<body> 
	<h1>Consulta el API Rest de SmallNotes. (Protegido por OAuth2)</h1>
	<p><a href="${pageContext.request.contextPath}/home">Inicio</a></p>
	<h2>Requesting SmallNotes API using server calls</h2>
	<ol>
		<li><a href="${pageContext.request.contextPath}/oauth/api/notes">${pageContext.request.contextPath}/oauth/api/notes</a></li>
		<li><a href="${pageContext.request.contextPath}/oauth/api/notes/1">${pageContext.request.contextPath}/oauth/api/notes/1</a></li>
		<li>
			 <form:form action="${pageContext.request.contextPath}/oauth/api/notes/2" method="delete" methodParam="_method">
					<input type="submit" name="delete" value="Eliminar note con id 2" httpVerb="delete"/>
			 </form:form>
		</li>
	</ol>
	<div id="output_screen">
		<h2>Datos de salida:</h2>
		
		<c:if test="${not empty notes}">
			<h3>Salida de la llamada: ${pageContext.request.contextPath}/oauth/api/notes</h3>
			<ul>
				<c:forEach items="${notes}" var="note">
					<li>titulo: ${note.title}</li>
				</c:forEach>
			</ul>
		</c:if>
		
		<c:if test="${not empty note}">
			<h3>Salida de la llamada: ${pageContext.request.contextPath}/oauth/api/notes/1</h3>
			<li>titulo: ${note.title}</li>
		</c:if>
		
	</div>
	
</body>  
</html>

