<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Product</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-eOJMYsd53ii+scO/bJGFsiCZc+5NDVN2yr8+0RDqr0Ql0h+rP48ckxlpbzKgwra6" crossorigin="anonymous">
</head>
<body>
	<h1 class="text-center mb-5 pt-3"><c:out value="${product.name}"/></h1>
	<div class="d-flex justify-content-around">
		<div>
			<h4>Categories</h4>
			<ul>
				<c:forEach items="${product.categories}" var="category">
				<li>${category.name}</li>
				</c:forEach>
			</ul>
		</div>
		<div>
			<form action="/product/${product.id}/addcategory" method="post">
				<label for="category">Add Category</label>
				<select name="category" id="category">
					<c:forEach items="${categories}" var="category">
					<option value="${category.id}">${category.name}</option>
					</c:forEach>
				</select>
				<input type="submit" value="Add" class="btn btn-success mt-3"/>
			</form>
		</div>
	</div>
</body>
</html>