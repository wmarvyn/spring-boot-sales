package com.wmarvyn.sales.resources.exception;

import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandardError {
	private static final long serialVersionUID = 1L;

	private List<FileMassage> errors = new ArrayList<>();
	
	public ValidationError(Integer status, String msg, Long timeStamp) {
		super(status, msg, timeStamp);
		// TODO Auto-generated constructor stub
	}

	public List<FileMassage> getErros() {
		return errors;
	}

	public void addError(String fieldName, String massage) {
		errors.add(new FileMassage(fieldName,massage));
	}
	
}
