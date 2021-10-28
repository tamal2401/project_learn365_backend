package org.learn365.exception;

public class OtpException extends RuntimeException {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private static Integer errorCode = 404;

	public OtpException(String errorMsg, Throwable t) {
		super(errorMsg, t);
	}

	public OtpException(String errorMsg) {
		super(errorMsg);
	}

	public static Integer getErrorcode() {
		return errorCode;
	}

}
