<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
	<meta charset="ISO-8859-1">
	<link rel="stylesheet" href="style.css">
		<title>Edit Owner</title>
	</head>
<body>
<h1>Edit an Owner</h1>
<a href = "viewAllOwnersServlet">Go Back to Owner List</a><br />
<form action ="editOwnerServlet" method="post">
Owner:<br />
<input type ="text" name ="ownerName" value="${ownerToEdit.ownerName}"><br />
<input type ="hidden" name ="id" value="${ownerToEdit.id}">
<input type = "submit" value= "Save Edited Owner" class="button">
</form>
</body>
</html>