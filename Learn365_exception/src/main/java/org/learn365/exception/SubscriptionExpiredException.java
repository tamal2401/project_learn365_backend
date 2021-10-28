package org.learn365.exception;

public class SubscriptionExpiredException extends RuntimeException {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private static Integer errorCode = 400;

	public SubscriptionExpiredException(String errorMsg, Throwable t) {
		super(errorMsg, t);
	}

	public SubscriptionExpiredException(String errorMsg) {
		super(errorMsg);
	}

	public static Integer getErrorcode() {
		return errorCode;
	}

}
