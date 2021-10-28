package org.learn365.exception;

public class RoleNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static Integer errorCode = 404;

	public RoleNotFoundException(String errorMsg, Throwable t) {
		super(errorMsg, t);
	}

	public RoleNotFoundException(String errorMsg) {
		super(errorMsg);
	}

	public static Integer getErrorcode() {
		return errorCode;
	}

}
