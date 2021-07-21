package org.learn365.exception;

public class UserCoursePortfolioException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static Integer errorCode = 400;

	public UserCoursePortfolioException(String errorMsg, Throwable t) {
		super(errorMsg, t);
	}

	public UserCoursePortfolioException(String errorMsg) {
		super(errorMsg);
	}

	public static Integer getErrorcode() {
		return errorCode;
	}

}
