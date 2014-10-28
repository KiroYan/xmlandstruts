<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="html" uri="http://struts.apache.org/tags-html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://struts.apache.org/tags-nested" prefix="nested"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="resources/css/style.css" type="text/css"></link>
<title>Insert title here</title>
</head>
<body>
	<div id="container">
		<div id="header">
			<h2><a href="shop.do?action=viewCategories">Shop</a></h2>
		</div>
		  <div id="content">
		  <h1>Products</h1>
		<table>
			<nested:iterate id="product" indexId="iteration" name="shop"
				property="document.rootElement.children[0].children[${categoryPosition}].children[1].children[${subcategoryPosition}].children">
				<c:choose>
					<c:when test="${iteration == 0}">
					</c:when>
					<c:otherwise>
						<tr>
							<td><a
								href="shop.do?action=editProduct&category=${categoryPosition}&subcategory=${subcategoryPosition}&product=${iteration}">
									<nested:write property="child(name).text" /></a></td>
							<td><nested:write property="child(producer).text" /></td>
							<td><nested:write property="child(model).text" /></td>
							<td><nested:write property="child(dateOfIssue).text" /></td>
							<td><nested:write property="child(color).text" /></td>
							<c:choose>
								<c:when test="${product.getChild('notInStock') == null}">
									<td><nested:write property="child(price).text" />$</td>
								</c:when>
								<c:otherwise>
									<td>not in stock</td>
								</c:otherwise>
							</c:choose>
						</tr>
					</c:otherwise>
				</c:choose>
			</nested:iterate>
			<tr><td><a
				href="shop.do?action=addProduct&category=${categoryPosition}&subcategory=${subcategoryPosition}"><input
					type="button" value="Add" /></a><a href="shop.do?action=viewSubcategories&category=${categoryPosition}"><input type="button" value="Back" /></a></td></tr>
		</table>
	</div>
	</div>
</body>
</html>

