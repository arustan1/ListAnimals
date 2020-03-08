<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>

<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="style.css">
<title>Owner List</title>
</head>
<body>
	<h1>Owner List</h1>
	<a href="viewAllAnimalsServlet">Go to Pet List</a>
	<br />
	<form method="post" action="ownerNavigationServlet">
		<table class="center">
			<c:forEach items="${requestScope.allOwners}" var="currentowner">
				<tr>
					<td><input type="radio" name="id" value="${currentowner.owner}"></td>
					<td>${currentowner.ownerName}</td>
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