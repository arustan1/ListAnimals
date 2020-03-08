<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="style.css">
<title>Animal List</title>
</head>
<body>
	<h1>Animal List</h1>
	<a href="viewAllOwnersServlet">Go to Owner List</a>
	<br />
	<form method="post" action="animalsNavigationServlet">
		<table class="center">
			<c:forEach items="${requestScope.allAnimals}" var="currentanimal">
				<tr>
					<td><input type="radio" name="id" value="${currentanimal.id}"></td>
					<td>${currentanimal.name}</td>
					<td>Type: ${currentanimal.type}</td>
					<td>Owned by: ${currentanimal.owner}</td>
				</tr>
			</c:forEach>
		</table>
		<br /> <input type="submit" value="Edit" name="doThisToItem"
			class="button"> <input type="submit" value="Delete"
			name="doThisToItem" class="button"> <input type="submit"
			value="Add" name="doThisToItem" class="button">
	</form>

</body>
</html>