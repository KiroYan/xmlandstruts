package com.epam.xmlstruts.forms;

import org.apache.struts.action.ActionForm;
import org.jdom2.Document;

public class DocumentForm extends ActionForm {
	private Document shop = new Document();

	public Document getShop() {
		return shop;
	}

	public void setShop(Document shop) {
		this.shop = shop;
	}
}

