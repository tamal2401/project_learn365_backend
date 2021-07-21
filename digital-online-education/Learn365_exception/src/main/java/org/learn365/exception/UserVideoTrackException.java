package org.learn365.exception;

public class UserVideoTrackException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static Integer errorCode = 404;

	public UserVideoTrackException(String errorMsg, Throwable t) {
		super(errorMsg, t);
	}

	public UserVideoTrackException(String errorMsg) {
		super(errorMsg);
	}

	public static Integer getErrorcode() {
		return errorCode;
	}

}
