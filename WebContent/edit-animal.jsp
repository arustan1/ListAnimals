<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
	<meta charset="ISO-8859-1">
	<link rel="stylesheet" href="style.css">
		<title>Edit Pet</title>
	</head>
<body>
<h1>Edit a Pet</h1>
<a href = "viewAllAnimalsServlet">Go Back to Pet List</a><br />
<form action ="editAnimalServlet" method="post">
Owner:<br />
<input type ="text" name ="owner" value="${animalToEdit.owner}"><br />
Pet Name:<br />
<input type ="text" name ="name" value="${animalToEdit.name}"><br />
Pet Type:<br />
<input type ="text" name ="type" value="${animalToEdit.type}"><br />

<input type ="hidden" name ="id" value="${animalToEdit.id}">
<input type = "submit" value= "Save Edited Animal" class="button">
</form>
</body>
</html>