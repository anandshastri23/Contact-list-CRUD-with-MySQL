<%@ page import="java.util.*,com.anand.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>

	<title>Contacts</title>
	
	<link type="text/css" rel="stylesheet" href="css/style.css">
	
	
</head>


<body>
	
	<div id="wrapper">
		<div id="header">
			<h2>My Contacts</h2>
		</div>
	</div>
	<br/>
	<input type="button" value="+ add contact"
			onclick="window.location.href='AddContact.jsp';return false;"
			class="add-contact-button">
	
	<div  id="container">
		<div id="content">
			<table>
				<tr>
					<th>First Name</th>
					<th>Last Name</th>
					<th>Contact Number</th>
					<th>Email Id</th>
				</tr>
			
			<c:forEach var="tempContact" items="${contacts }">
				<tr>
					<td> ${tempContact.firstName} </td>
					<td> ${tempContact.lastName} </td>
					<td> ${tempContact.contactNumber}</td>
					<td> ${tempContact.emailId}</td>
				</tr>
			</c:forEach>
		
			</table>
		</div>
	</div>


</body>

</html>

