<!DOCTYPE html>
<html>
<head>
<title>Add Contact</title>
<link type="text/css" rel="stylesheet" href="css/style.css">
<link type="text/css" rel="stylesheet" href="css/add-contact-style.css">
</head>
<body>
	<div id="wrapper">
		<div id="header">
			<h2>Add Contact</h2>
		</div>
	</div>
	<div id="container">
		<form action="/MyContact/ContactControllerServlet">
			<table>
				<tr>
					<td> <label>First name: </label></td>
					<td> <input type="text" name="firstName"> </td>
				</tr>
				<tr>
					<td> <label>Last name: </label></td>
					<td> <input type="text" name="lastName"> </td>
				</tr>
				<tr>
					<td> <label>Contact Number: </label></td>
					<td> <input type="text" name="contactNumber"> </td>
				</tr>
				<tr>
					<td> <label>Email Id: </label></td>
					<td> <input type="text" name="emailId"> </td>
				</tr>
			</table>
			<hr/>
			<input type="hidden" name="hidden" value="save">
			<input type="submit" value="save">
		</form>
	</div>
	<div style="clear: both;"></div>
	<p>
	<a href="ContactControllerServlet">Back to list</a>
	</p>
</body>
</html>