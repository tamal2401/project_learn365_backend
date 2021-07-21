package org.learn365.exception;

public class UserNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static Integer errorCode = 404;

	public UserNotFoundException(String errorMsg, Throwable t) {
		super(errorMsg, t);
	}

	public UserNotFoundException(String errorMsg) {
		super(errorMsg);
	}

	public static Integer getErrorcode() {
		return errorCode;
	}

}
