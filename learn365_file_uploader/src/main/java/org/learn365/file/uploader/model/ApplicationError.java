package org.learn365.file.uploader.model;

import java.util.List;

public class ApplicationError {

	private Integer code;
	private String message;
	private List<String> messages;
	private Throwable exception;

	public ApplicationError(ApplicationErrorBuilder builder) {
		this.code = builder.code;
		this.message = builder.message;
		this.exception = builder.exception;
		this.messages = builder.messages;
	}

	public static class ApplicationErrorBuilder {
		Integer code;
		String message;
		Throwable exception;
		List<String> messages;

		public ApplicationErrorBuilder(Integer code, String message) {
			this.code = code;
			this.message = message;
		}

		public ApplicationErrorBuilder setExceptionStack(Throwable throwable) {
			this.exception = throwable;
			return this;
		}

		public ApplicationErrorBuilder setViolationMessage(List<String> violationMsg) {
			this.messages = violationMsg;
			return this;
		}

		public ApplicationError build() {
			return new ApplicationError(this);
		}

	}

	public Integer getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}

	public Throwable getException() {
		return exception;
	}

	public List<String> getMessages() {
		return messages;
	}

}
