package com.wmarvyn.sales.services.exception;

public class Objectnotfoundexception extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	
	public Objectnotfoundexception(String msg) {
		super(msg);
	}
	
	public Objectnotfoundexception(String msg, Throwable cause) {
		super (msg, cause);
	}

}
