package org.learn365.exception;

public class UserNotCreatedException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static Integer errorCode = 400;

	public UserNotCreatedException(String errorMsg, Throwable t) {
		super(errorMsg, t);
	}

	public UserNotCreatedException(String errorMsg) {
		super(errorMsg);
	}

	public static Integer getErrorcode() {
		return errorCode;
	}

}
