<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Category</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-eOJMYsd53ii+scO/bJGFsiCZc+5NDVN2yr8+0RDqr0Ql0h+rP48ckxlpbzKgwra6" crossorigin="anonymous">
</head>
<body>
	<h1 class="text-center mb-5 pt-3"><c:out value="${category.name}"/></h1>
	<div class="d-flex justify-content-around">
		<div>
			<h4>Products</h4>
			<ul>
				<c:forEach items="${category.products}" var="product">
				<li>${product.name} - $ ${product.price}</li>
				</c:forEach>
			</ul>
		</div>
		<div>
			<form action="/category/${category.id}/addproduct" method="post">
				<label for="product">Add Product</label>
				<select name="product" id="product">
					<c:forEach items="${products}" var="product">
					<option value="${product.id}">${product.name}</option>
					</c:forEach>
				</select>
				<input type="submit" value="Add" class="btn btn-success mt-3"/>
			</form>
		</div>
	</div>
</body>
</html>