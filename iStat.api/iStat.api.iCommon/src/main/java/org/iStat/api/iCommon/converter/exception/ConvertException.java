package org.iStat.api.iCommon.converter.exception;

public class ConvertException extends Exception {

	private static final long serialVersionUID = -5290069008657753128L;

	public ConvertException(String message) {
		super(message);
	}

	public ConvertException(String message, Throwable cause) {
		super(message, cause);
	}

}
