<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="html" uri="http://struts.apache.org/tags-html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://struts.apache.org/tags-nested" prefix="nested"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="resources/css/style.css" type="text/css"></link>
<title>Insert title here</title>
</head>
<body>
	<div id="container">
		<div id="header">
			<h2><a href="shop.do?action=categories">Shop</a></h2>
		</div>
		<div id="content">
		<h1>Subcategories</h1>
			<table>
				<nested:iterate id="subcategory" indexId="iteration" name="shop"
					property="document.rootElement.children[0].children[${categoryPosition}].children[1].children">
					<bean:define id="products" name="subcategory" property="children" />

					<tr>
						<td><a
							href="shop.do?action=viewProducts&category=${categoryPosition}&subcategory=${iteration}">
								<nested:write property="child(name).text" />
						</a>(${products.size() - 1})</td>
					</tr>
				</nested:iterate>
				<tr><td><a href="shop.do?action=viewCategories"><input type="button" value="Back" /></a></td></tr>
			</table>
		</div>
	</div>
</body>
</html>

