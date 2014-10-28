<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="html" uri="http://struts.apache.org/tags-html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://struts.apache.org/tags-nested" prefix="nested"%>
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
			<h2><a href="shop.do?action=categories">Shop</a></h2>
		</div>
		<div id="content">
			
				<h2>Edit product</h2>
				
				<nested:form styleId="form" action="/shop?action=saveChangesToFile&category=${categoryPosition}&subcategory=${subcategoryPosition}&product=${productPosition}" method="post">
				<nested:nest property="shop.document.rootElement.children[0].children[${categoryPosition}].children[1].children[${subcategoryPosition}].children[${productPosition}]">
					<div>
						<label for="name">Name:</label> <nested:text
								property="child(name).text" styleId="name" />
					</div>
					 <div>
						<label for="producer">Producer:</label> <nested:text
								property="child(producer).text" styleId="producer" />
					</div>
					<div>
						<label for="model">Model:</label> <nested:text
								property="child(model).text" styleId="model" />
					</div>
					<div>
						<label for="dateOfIssue">Date:</label> <nested:text
								property="child(dateOfIssue).text" styleId="dateOfIssue" />
					</div>				
					
					<div>
						<label for="color">Color:</label> <nested:text
								property="child(color).text" styleId="color" />
					</div>
					<nested:empty
						property="child(price)">

						<div>
							<label for="inStock">In Stock:</label> <select
								name="inStock" id="inStock">
									<option value="false">false</option>
									<option value="true">true</option>
							</select>
						</div>
						<div>
							<label for="price">Price:</label><input type="text" name="price" id="price" />
						</div>

					</nested:empty>
					<nested:present
						property="child(price).text">

						<div><label for="inStock">In Stock:</label> <select onchange="blockIfFalse(this.value);"
								name="inStock" id="inStock">
									<option value="true">true</option>
									<option value="false">false</option>
							</select></div>
						<div>
							<label for="price">Price:</label><nested:text
									property="child(price).text" styleId="price" />
						</div>
					</nested:present>
					<div>
						<input type="submit" value="Save" /><a
							href="shop.do?action=viewProducts&category=${categoryPosition}&subcategory=${subcategoryPosition}"><input
								type="button" value="Back" /></a>
					</div>
					</nested:nest>
				</nested:form>
			</table>
			
			
			
			
			
			
			
			
			
			
			
			
<%-- 			<nested:form action="/shop?action=saveChangesToFile&category=${categoryPosition}&subcategory=${subcategoryPosition}&product=${productPosition}" method="post">
				<nested:nest property="shop.document.rootElement.children[0].children[${categoryPosition}].children[1].children[${subcategoryPosition}].children[${productPosition}]">
					<tr>
						<td>Name: <nested:text
								property="child(name).text" />
					</tr>
					 <tr>
						<td>Producer: <nested:text
								property="child(producer).text" /></td>
					</tr>
					<tr>
						<td>Model: <nested:text
								property="child(model).text" /></td>
					</tr>
					<tr>
						<td>Date: <nested:text
								property="child(dateOfIssue).text" />
					</tr>
					
					<tr>
						<td>Color: <nested:text
								property="child(color).text" /></td>
					</tr>
					<nested:empty
						property="child(price)">

						<tr>
							<td>In Stock: <select
								name="inStock" id="inStock">
									<option value="false">false</option>
									<option value="true">true</option>
							</select></td>
						</tr>
						<tr>
							<td>Price:<input type="text" name="price" id="price" /></td>
						</tr>

					</nested:empty>
					<nested:present
						property="child(price).text">

						<tr>
							<td>In Stock: <select onchange="blockIfFalse(this.value);"
								name="inStock" id="inStock">
									<option value="true">true</option>
									<option value="false">false</option>
							</select></td>
						</tr>
						<tr>
							<td>Price:<nested:text
									property="child(price).text" /></td>
						</tr>
					</nested:present>
					<tr>
						<td><input type="submit" value="Save" /><a
							href="shop.do?action=viewProducts&category=${categoryPosition}&subcategory=${subcategoryPosition}"><input
								type="button" value="Back" /></a></td></td>
					</tr>
					</nested:nest>
				</nested:form>
			</table> --%>
		</div>
	</div>
</body>
</html>