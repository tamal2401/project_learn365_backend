package org.learn365.exception;

public class UserSubscriptionException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static Integer errorCode = 400;

	public UserSubscriptionException(String errorMsg, Throwable t) {
		super(errorMsg, t);
	}

	public UserSubscriptionException(String errorMsg) {
		super(errorMsg);
	}

	public static Integer getErrorcode() {
		return errorCode;
	}

}
