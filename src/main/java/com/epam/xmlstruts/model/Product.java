package com.epam.xmlstruts.model;

import java.util.ArrayList;
import java.util.List;

public class Product {
	private String name;
	private String producer;
	private String model;
	private String dateOfIssue;
	private String color;
	private boolean inStock;
	private String price;
	
	public Product(){}
	
	public Product(Product product){
		this.name = product.getName();
		this.producer = product.getProducer();
		this.model = product.getModel();
		this.dateOfIssue = product.getDateOfIssue();
		this.color = product.getColor();
		this.inStock = product.isInStock();
		this.price = product.getPrice();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getProducer() {
		return producer;
	}

	public void setProducer(String producer) {
		this.producer = producer;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getDateOfIssue() {
		return dateOfIssue;
	}

	public void setDateOfIssue(String dateOfIssue) {
		this.dateOfIssue = dateOfIssue;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public boolean isInStock() {
		return inStock;
	}

	public void setInStock(boolean inStock) {
		this.inStock = inStock;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}
	
	public String test(Product product) {
		return product.getName();
	}

	public List<String> getFields() {
		List<String> textFields = new ArrayList<String>();
		textFields.add(this.name);
		textFields.add(this.dateOfIssue);
		if (inStock) {
		textFields.add(this.price);
		}
		textFields.add(this.model);
		textFields.add(this.producer);
		textFields.add(this.color);

		return textFields;
	}
}
