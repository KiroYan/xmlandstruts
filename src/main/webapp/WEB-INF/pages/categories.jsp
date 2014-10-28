<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="html" uri="http://struts.apache.org/tags-html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://struts.apache.org/tags-nested" prefix="nested"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf8">
<link rel="stylesheet" href="resources/css/style.css" type="text/css"></link>
<title>Shop</title>
</head>
<body>
	<div id="container">
		<div id="header">
			<h2><a href="shop.do?action=categories">Shop</a></h2>
		</div>
		<div id="content">
			<h1>Categories</h1>
			<table>
				<nested:iterate id="category" indexId="iteration" name="shop"
					property="document.rootElement.children[0].children">
					<tr>
						<td><a
							href="shop.do?action=viewSubcategories&category=${iteration}"><nested:write
									property="children[0].text" /></a>(${numberOfProducts[iteration]})</td>
					</tr>
				</nested:iterate>
			</table>
		</div>
	</div>
</body>
</html>

