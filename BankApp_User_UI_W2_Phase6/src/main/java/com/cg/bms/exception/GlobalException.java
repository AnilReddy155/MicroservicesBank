package com.cg.bms.exception;

public class GlobalException extends Exception {

	private static final long serialVersionUID = 1L;

	private String viewName;

	public GlobalException(String message, String viewName) {
		super(message);
		this.viewName = viewName;
	}

	public String getViewName() {
		return viewName;
	}

	public void setViewName(String viewName) {
		this.viewName = viewName;
	}

}
