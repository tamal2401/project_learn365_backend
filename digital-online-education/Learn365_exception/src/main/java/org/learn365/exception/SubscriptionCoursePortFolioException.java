package org.learn365.exception;

public class SubscriptionCoursePortFolioException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static Integer errorCode = 400;

	public SubscriptionCoursePortFolioException(String errorMsg, Throwable t) {
		super(errorMsg, t);
	}

	public SubscriptionCoursePortFolioException(String errorMsg) {
		super(errorMsg);
	}

	public static Integer getErrorcode() {
		return errorCode;
	}

}
