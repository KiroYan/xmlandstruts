package com.epam.xmlstruts.util;

import java.io.File;

import javax.xml.transform.Source;
import javax.xml.transform.Templates;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;

public class TransformerCreator {
	private static Templates template;

	private static void createTemplate(Source xslDoc) {
		try {
			template = TransformerFactory.newInstance().newTemplates(xslDoc);
		} catch (TransformerConfigurationException e) {
			throw new RuntimeException(e);
		} catch (TransformerFactoryConfigurationError e) {
			throw new RuntimeException(e);
		}
	}
	
	public static Transformer newTransformer(Source xslDoc) {
		if (template == null) {
			createTemplate(xslDoc);
		}
		Transformer transformer = null;
		try {
			transformer = template.newTransformer();
		} catch (TransformerConfigurationException e) {
			throw new RuntimeException();
		}
		return transformer;	
	}
}
