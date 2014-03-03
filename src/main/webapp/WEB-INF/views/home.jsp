<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<body>
	<h1>Message : ${message}</h1>	
	
	<h2>Contacts:</h2>
	<ul>
		<c:forEach var="contact" items="${contacts}">
		   <li>
		   	<c:out value="${contact.firstname}"/> 
		   	<c:out value="${contact.lastname}"/> :
		   	<c:out value="${contact.email}"/>
		   	(<c:out value="${contact.telephone}"/>)
		   </li>
		</c:forEach>
	</ul>
</body>
</html>