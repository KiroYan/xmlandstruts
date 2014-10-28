package com.epam.xmlstruts.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;




import org.apache.commons.lang3.StringUtils;

import com.epam.xmlstruts.model.Product;

public class ProductValidator {
	private static final String DATE_FORMAT = "dd-MM-yyyy";
	private static final String MODEL_REGEXP = "[a-zA-Z]{2}[0-9]{3}";
	private static final Pattern MODEL_PATTERN = Pattern.compile(MODEL_REGEXP);
	private static final SimpleDateFormat DATE_FORMATTER = new SimpleDateFormat(
			DATE_FORMAT);
	private String dateError = "";
	private String modelError = "";
	private boolean valid = true;
	
	public ProductValidator(){}

	public  boolean validate(Product product) {
		if (product != null) {
			checkRequired(product);
			validateDate(product.getDateOfIssue());
			validateModel(product.getModel());
		}

		return valid;
	}

	private void validateModel(String model) {
		Matcher matcher = MODEL_PATTERN.matcher(model);
		if (!matcher.matches()) {
			valid = false;
		}
	}

	private void validateDate(String date) {
		try {
			DATE_FORMATTER.parse(date);
		} catch (ParseException e) {
			valid = false;
		}
	}

	private boolean checkRequired(Product product) {
		for (String field : product.getFields()) {
			if (StringUtils.isBlank(field)) {
				valid = false;
				return false;
			}
		}
		return true;
	}

	public String getDateError() {
		return dateError;
	}

	public void setDateError(String dateError) {
		this.dateError = dateError;
	}

	public String getModelError() {
		return modelError;
	}

	public void setModelError(String modelError) {
		this.modelError = modelError;
	}

	public boolean isValid() {
		return valid;
	}

	public void setValid(boolean valid) {
		this.valid = valid;
	}
}
