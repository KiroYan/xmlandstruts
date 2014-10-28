package com.epam.xmlstruts.util;

import javax.servlet.http.HttpServletRequest;

import com.epam.xmlstruts.model.Product;

public class ProductBuilder {
	private ProductBuilder() {
	}

	public static Product build(HttpServletRequest request) {
		Product product = new Product();
		product.setName(request.getParameter("name"));
		product.setProducer(request.getParameter("producer"));
		product.setModel(request.getParameter("model"));
		product.setColor(request.getParameter("color"));
		product.setDateOfIssue(request.getParameter("dateOfIssue"));
		product.setInStock(Boolean.valueOf(request.getParameter("inStock")));

		if (product.isInStock()) {
			product.setPrice(request.getParameter("price"));
		}

		return product;
	}
}
