package org.learn365.exception;

public class UserCourseInprogressException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static Integer errorCode = 404;

	public UserCourseInprogressException(String errorMsg, Throwable t) {
		super(errorMsg, t);
	}

	public UserCourseInprogressException(String errorMsg) {
		super(errorMsg);
	}

	public static Integer getErrorcode() {
		return errorCode;
	}

}
