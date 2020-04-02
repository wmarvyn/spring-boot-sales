package com.wmarvyn.sales.resources.exception;

import java.io.Serializable;

public class FileMassage implements Serializable {
	private static final long serialVersionUID = 1L;

	private String fileName;
	private String massage;

	public FileMassage() {
	}

	public FileMassage(String fileName, String massage) {
		super();
		this.fileName = fileName;
		this.massage = massage;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getMassage() {
		return massage;
	}

	public void setMassage(String massage) {
		this.massage = massage;
	}

}
