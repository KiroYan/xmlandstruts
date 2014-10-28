<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="html" uri="http://struts.apache.org/tags-html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head lang="en">
<meta charset="UTF-8">
<script type="text/javascript"
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<script type="text/javascript" src="resources/js/main.js"></script>
<link rel="stylesheet" href="resources/css/style.css" type="text/css"></link>
<title></title>
</head>
<body>
	<div id="container">
		<div id="header">
			<h2>
				<a href="shop.do?action=categories">Shop</a>
			</h2>
		</div>
		<div id="content">
			<h2>Add product</h2>
			<form id="form"
				action="shop.do?action=saveProduct&category=${categoryPosition}&subcategory=${subcategoryPosition}"
				method="post">
				<div>
					<label for="name">Name:</label> <input type="text" name="name"
						id="name" />
				</div>
				<div>
					<label for="producer">Producer:</label> <input type="text"
						name="producer" id="producer" />
				</div>
				<div>
					<label for="model">Model:</label> <input type="text" name="model"
						id="model" />
				</div>
				<div>
					<label for="dateOfIssue">Date:</label> <input type="text"
						name="dateOfIssue" id="dateOfIssue" />
				</div>
				<div>
					<label for="color">Color:</label> <input type="text" name="color"
						id="color" />
				</div>
				<div>
					<label for="inStock">In Stock:</label> <select name="inStock"
						id="inStock">
						<option value="true">true</option>
						<option value="false">false</option>
					</select>
				</div>
				<div>
					<label for="price">Price:</label> <input type="text" name="price"
						id="price" />
				</div>
				<div>
					<input type="submit" value="Submit" />
				</div>
			</form>
		</div>
	</div>
</body>
</html>