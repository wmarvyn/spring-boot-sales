package com.wmarvyn.sales.resources.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.wmarvyn.sales.services.exception.Objectnotfoundexception;

@ControllerAdvice
public class ResourceExceptionHandler {

	@ExceptionHandler(Objectnotfoundexception.class)
	public ResponseEntity<StandardError> objectNotFound(Objectnotfoundexception e, HttpServletRequest request ){
		StandardError err = new StandardError(HttpStatus.NOT_FOUND.value(),e.getMessage(),System.currentTimeMillis());
	
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
	}
}
