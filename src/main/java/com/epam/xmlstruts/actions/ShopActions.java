package com.epam.xmlstruts.actions;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.FileTime;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.apache.commons.io.FileUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionRedirect;
import org.apache.struts.actions.DispatchAction;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.filter.Filters;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;
import org.jdom2.xpath.XPathExpression;
import org.jdom2.xpath.XPathFactory;

import com.epam.xmlstruts.constants.Constants;
import com.epam.xmlstruts.forms.DocumentForm;
import com.epam.xmlstruts.model.Product;
import com.epam.xmlstruts.util.ProductBuilder;
import com.epam.xmlstruts.util.ProductValidator;
import com.epam.xmlstruts.util.ReentrantReadWriteLockHolder;
import com.epam.xmlstruts.util.TransformerCreator;
import com.epam.xmlstruts.util.XmlToJdomConvertor;

public class ShopActions extends DispatchAction {
	private File xmlDoc;
	private Source xmlDocSource;
	private Source xslDoc;
	private ServletContext context;

	public ActionForward init(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		context = getServlet().getServletContext();
		
		xmlDoc = new File(context.getRealPath(Constants.XML_FILE_PATH));
		xmlDocSource = new StreamSource(xmlDoc);
		xslDoc = new StreamSource(context.getRealPath(Constants.XSL_FILE_PATH));
		
		Document shop = XmlToJdomConvertor.convert(context);
	    ((DocumentForm) form).setShop(shop);
		
	    return mapping.findForward(Constants.CATEGORY_ACTION);
	}

	public ActionForward viewCategories(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		try {
			ReentrantReadWriteLockHolder.getLock().readLock().lock();
			
			Document shop = ((DocumentForm) form).getShop();
			List<Integer> numberOfProductsInCategories = setNumberOfProductsPerCategoty(shop);
			
			request.setAttribute(Constants.NUMBER_OF_PRODUCTS, numberOfProductsInCategories);
			request.setAttribute(Constants.SHOP, shop);
			
		} finally {
			ReentrantReadWriteLockHolder.getLock().readLock().unlock();
		}
		
		return mapping.findForward(Constants.CATEGORIES);
	}

	public ActionForward viewSubcategories(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		try {
			ReentrantReadWriteLockHolder.getLock().readLock().lock();
			
			Document shop = ((DocumentForm) form).getShop();
			
			request.setAttribute(Constants.SHOP, shop);
			request.setAttribute(Constants.CATEGORY_POSITIOM,request.getParameter(Constants.CATEGORY));
			
		} finally {
			ReentrantReadWriteLockHolder.getLock().readLock().unlock();
		}

		return mapping.findForward(Constants.SUBCATEGORIES);
	}

	public ActionForward viewProducts(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		try {
			ReentrantReadWriteLockHolder.getLock().readLock().lock();
			
			Document shop = ((DocumentForm) form).getShop();
			
			request.setAttribute(Constants.SHOP, shop);
			request.setAttribute(Constants.CATEGORY_POSITIOM,request.getParameter(Constants.CATEGORY));
			request.setAttribute(Constants.SUBCATEGORY_POSITIOM,request.getParameter(Constants.SUBCATEGORY));
			
		} finally {
			ReentrantReadWriteLockHolder.getLock().readLock().unlock();
		}

		return mapping.findForward(Constants.PRODUCTS);
	}

	public ActionForward addProduct(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		try {
			ReentrantReadWriteLockHolder.getLock().readLock().lock();
			
			Document shop = ((DocumentForm) form).getShop();
			
			request.setAttribute(Constants.CATEGORY_POSITIOM,request.getParameter(Constants.CATEGORY));
			request.setAttribute(Constants.SUBCATEGORY_POSITIOM,request.getParameter(Constants.SUBCATEGORY));
			
		} finally {
			ReentrantReadWriteLockHolder.getLock().readLock().unlock();
		}

		return mapping.findForward(Constants.ADD_PRODUCT);
	}

	public ActionForward saveProduct(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		try {	
			ReentrantReadWriteLockHolder.getLock().writeLock().lock();
			
			Product product = ProductBuilder.build(request);
			Document shop = ((DocumentForm) form).getShop();
			
			StringWriter resultTempPlace = new StringWriter();
			StreamResult result = new StreamResult(resultTempPlace);
			Transformer transformer = getTransformerWithSetParameters(request);
			
			transform(transformer, result);
		
			FileUtils.write(xmlDoc, resultTempPlace.toString());
			shop = XmlToJdomConvertor.convert(context);
			((DocumentForm) form).setShop(shop);
			
			ActionRedirect redirect = new ActionRedirect(mapping.findForward(Constants.PRODUCT_REDIRECT));
			redirect.addParameter(Constants.CATEGORY, request.getParameter(Constants.CATEGORY));
			redirect.addParameter(Constants.SUBCATEGORY, request.getParameter(Constants.SUBCATEGORY));
			
			return redirect;
			} catch (IOException e) {
				throw new RuntimeException(e);
			} finally {
				ReentrantReadWriteLockHolder.getLock().writeLock().unlock();
			}
				
			
			 
		
	}
	
	public ActionForward editProduct(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		try {
			ReentrantReadWriteLockHolder.getLock().readLock().lock();
			
			Document shop = ((DocumentForm) form).getShop();
			
			request.setAttribute(Constants.CATEGORY_POSITIOM,request.getParameter(Constants.CATEGORY));
			request.setAttribute(Constants.SUBCATEGORY_POSITIOM,request.getParameter(Constants.SUBCATEGORY));
			request.setAttribute(Constants.PRODUCT_POSITIOM,request.getParameter(Constants.PRODUCT));
			
		} finally {
			ReentrantReadWriteLockHolder.getLock().readLock().unlock();
		}
		
		return mapping.findForward(Constants.EDIT_PRODUCT);
	}
	
	public ActionForward saveChangesToFile(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		try {
			ReentrantReadWriteLockHolder.getLock().writeLock().lock();
			
			DocumentForm docForm = (DocumentForm) form;
			Document shop = docForm.getShop();
			
			String priceValue = request.getParameter(Constants.PRICE);
			boolean isProductInStock = request.getParameter("inStock").equals("true");
			
			Element product = getRequestedProduct(request,shop);
			processPriceOrNotInStockTag(product, isProductInStock, priceValue);
			UpdateShopInXml(shop);
			
			shop = XmlToJdomConvertor.convert(context);
			docForm.setShop(shop);
			
			ActionRedirect redirect = new ActionRedirect(mapping.findForward(Constants.PRODUCT_REDIRECT));
			redirect.addParameter(Constants.CATEGORY, request.getParameter(Constants.CATEGORY));
			redirect.addParameter(Constants.SUBCATEGORY, request.getParameter(Constants.SUBCATEGORY));
			
			return redirect;
		} finally {
			ReentrantReadWriteLockHolder.getLock().writeLock().unlock();
		}
		
		
	}

	protected void transform(Transformer transformer, StreamResult sr) {
		try {
			transformer.transform(xmlDocSource, sr);
		} catch (TransformerException e) {
			throw new RuntimeException(e);
		}
	}
	
	private List<Integer> setNumberOfProductsPerCategoty(Document shop) {
		Element root = shop.getRootElement();
		List<Element> categories = root.getChildren().get(0).getChildren();
		List<Integer> numberOfProductsInCategories = new ArrayList<Integer>();
		XPathFactory xpfac = XPathFactory.instance();
		
		for (int i = 1; i <= categories.size(); i++) {
			XPathExpression<Element> xp = xpfac.compile("/shop/categories/category[" + i
						+ "]/subcategories/subcategory/product", Filters.element());
				numberOfProductsInCategories.add(xp.evaluate(shop).size());
			}
		
		return numberOfProductsInCategories;
	}
	
	private Transformer getTransformerWithSetParameters(HttpServletRequest request) {
		Transformer transformer = TransformerCreator.newTransformer(xslDoc);
		
		String categoryPosition = String.valueOf(Integer.parseInt(request.getParameter(Constants.CATEGORY)) + 1);
		String subcategoryPosition = String.valueOf(Integer.parseInt(request.getParameter(Constants.SUBCATEGORY)) + 1);
		
		transformer.setParameter(Constants.CATEGORY, categoryPosition);
		transformer.setParameter(Constants.SUBCATEGORY, subcategoryPosition);
		transformer.setParameter(Constants.NAME, request.getParameter(Constants.NAME));
		transformer.setParameter(Constants.PRODUCER,request.getParameter(Constants.PRODUCER));
		transformer.setParameter(Constants.MODEL, request.getParameter(Constants.MODEL));
		transformer.setParameter(Constants.DATE_OF_ISSUE,request.getParameter(Constants.DATE_OF_ISSUE));
		transformer.setParameter(Constants.COLOR, request.getParameter(Constants.COLOR));
		if (request.getParameter("inStock").equals("true")) {
			transformer.setParameter(Constants.PRICE, request.getParameter(Constants.PRICE));
		}
		
		return transformer;
	}
	
	private Element getRequestedProduct(HttpServletRequest request, Document shop) {
		String categoryPosition = String.valueOf(Integer.parseInt(request.getParameter(Constants.CATEGORY)) + 1);
		String subcategoryPosition = String.valueOf(Integer.parseInt(request.getParameter(Constants.SUBCATEGORY)) + 1);
		String productPosition = request.getParameter(Constants.PRODUCT);
		
		XPathFactory xpfac = XPathFactory.instance();
		Element product = xpfac.compile(
				"/shop/categories/category[" + categoryPosition
						+ "]/subcategories/subcategory[" + subcategoryPosition
						+ "]/product[" + productPosition + "]",
				Filters.element()).evaluateFirst(shop);
		
		return product;
	}
	
	private void processPriceOrNotInStockTag(Element product, boolean isProductInStock, String priceValue) {
		if (isProductInStock == true) {
			if (product.getChild(Constants.PRICE) == null) {
				addPriceTag(product, priceValue);
			}
		} else {
			if (product.getChild(Constants.IN_STOCK) == null) {
				addNotInStockTag(product);
			}
		}
	}
	
	private void addNotInStockTag(Element product) {
		product.removeChild(Constants.PRICE);
		Element notInStock = new Element(Constants.IN_STOCK);
		product.addContent(notInStock);
	}
	
	private void addPriceTag(Element product, String priceValue) {
		product.removeChild(Constants.IN_STOCK);
		Element price = new Element(Constants.PRICE);
		price.setText(priceValue);
		product.addContent(price);
	}
	
	private void UpdateShopInXml(Document shop) {
		XMLOutputter xmlOutput = new XMLOutputter();
		xmlOutput.setFormat(Format.getPrettyFormat());
		try {
			xmlOutput.output(shop, new FileWriter(context.getRealPath(Constants.XML_FILE_PATH)));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
/*	private FileTime determineTimeOfLastModification(Path path) {
		FileTime lastModificationTime = null;
		try { 
			lastModificationTime = Files.getLastModifiedTime(path,LinkOption.NOFOLLOW_LINKS); 
		} catch (IOException e) {
			throw new RuntimeException(e); 
		}
		
		return lastModificationTime;
	}*/
	
}
