package org.learn365.exception;

public class OtpVerifiedException extends RuntimeException {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private static Integer errorCode = 401;

	public OtpVerifiedException(String errorMsg, Throwable t) {
		super(errorMsg, t);
	}

	public OtpVerifiedException(String errorMsg) {
		super(errorMsg);
	}

	public static Integer getErrorcode() {
		return errorCode;
	}

}
