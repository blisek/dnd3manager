package com.blisek.dnd3manager.dnd3.converters;

public class ParseException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 253678295867904439L;

	public ParseException() {
	}

	public ParseException(String message) {
		super(message);
	}

	public ParseException(Throwable cause) {
		super(cause);
	}

	public ParseException(String message, Throwable cause) {
		super(message, cause);
	}

	public ParseException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
