package com.epam.xmlstruts.util;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.jdom2.Document;
import org.jdom2.input.DOMBuilder;
import org.xml.sax.SAXException;

import com.epam.xmlstruts.constants.Constants;

public final class XmlToJdomConvertor {
	private static DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	private static DocumentBuilder docBuilder = createDocBuilder();
	private static DOMBuilder domBuilder = new DOMBuilder();
	
	private XmlToJdomConvertor() {
		 
	}
	
	public static Document convert(ServletContext context) {	
		
		org.w3c.dom.Document w3cDocument = createW3Cdocument(context);
		Document document = domBuilder.build(w3cDocument);
		return document;
	}

	private static DocumentBuilder createDocBuilder() {
		DocumentBuilder dombuilder = null;
		try {
			dombuilder = factory.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			throw new RuntimeException(e);
		}

		return dombuilder;
	}

	private static org.w3c.dom.Document createW3Cdocument(ServletContext context) {
		org.w3c.dom.Document w3cDocument = null;
		
		try {
			ReentrantReadWriteLockHolder.getLock().readLock().lock();
			w3cDocument = docBuilder.parse(context.getRealPath(Constants.XML_FILE_PATH));
		} catch (SAXException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			throw new RuntimeException(e);
		} finally {
			ReentrantReadWriteLockHolder.getLock().readLock().unlock();
		}
		return w3cDocument;
	}
}
