package org.learn365.exception;

public class UserTrialDetailException extends RuntimeException {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private static Integer errorCode = 400;

	public UserTrialDetailException(String errorMsg, Throwable t) {
		super(errorMsg, t);
	}

	public UserTrialDetailException(String errorMsg) {
		super(errorMsg);
	}

	public static Integer getErrorcode() {
		return errorCode;
	}

}
