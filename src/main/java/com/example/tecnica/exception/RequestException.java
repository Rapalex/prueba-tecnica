package com.example.tecnica.exception;

public class RequestException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	private String code;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public RequestException(String code, String message) {
		super(message);
		this.code = code;
	}
}
