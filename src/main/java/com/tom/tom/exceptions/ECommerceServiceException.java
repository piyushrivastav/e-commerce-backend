package com.tom.tom.exceptions;

public class ECommerceServiceException extends Exception {

	private static final long serialVersionUID =1L;


	public ECommerceServiceException(String msg) {
		super(msg);
	}

	public ECommerceServiceException(String msg, Throwable cause ) {
		super(msg, cause);
	}

}
