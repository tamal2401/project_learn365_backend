package org.learn365.course.config;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintViolationException;

import org.learn365.exception.CoursePortFolioNotFoundException;
import org.learn365.exception.UserCourseInprogressException;
import org.learn365.exception.UserCoursePortfolioException;
import org.learn365.exception.UserVideoTrackException;
import org.learn365.modal.user.exception.model.ApplicationError;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	private static final Integer INTERNAL_SERVER_ERROR = 500;

	@ExceptionHandler
	public ApplicationError genericException(Exception e) {
		ApplicationError.ApplicationErrorBuilder error = new ApplicationError.ApplicationErrorBuilder(
				INTERNAL_SERVER_ERROR, e.getMessage());
		error.setExceptionStack(e);
		return error.build();
	}

	@ExceptionHandler
	public ApplicationError coursePortFolioNotFoundException(CoursePortFolioNotFoundException e) {
		ApplicationError.ApplicationErrorBuilder error = new ApplicationError.ApplicationErrorBuilder(
				CoursePortFolioNotFoundException.getErrorcode(), e.getMessage());
		error.setExceptionStack(e);
		return error.build();
	}

	@ExceptionHandler
	public ApplicationError IllegalArgException(IllegalArgumentException e) {
		ApplicationError.ApplicationErrorBuilder error = new ApplicationError.ApplicationErrorBuilder(
				HttpStatus.BAD_REQUEST.value(), e.getMessage());
		error.setExceptionStack(e);
		return error.build();
	}

	@ExceptionHandler
	public ApplicationError userCoursePortfolioException(UserCoursePortfolioException e) {
		ApplicationError.ApplicationErrorBuilder error = new ApplicationError.ApplicationErrorBuilder(
				UserCoursePortfolioException.getErrorcode(), e.getMessage());
		error.setExceptionStack(e);
		return error.build();
	}

	@ExceptionHandler
	public ApplicationError userCourseInprogressException(UserCourseInprogressException e) {
		ApplicationError.ApplicationErrorBuilder error = new ApplicationError.ApplicationErrorBuilder(
				UserCourseInprogressException.getErrorcode(), e.getMessage());
		error.setExceptionStack(e);
		return error.build();
	}

	@ExceptionHandler
	public ApplicationError userVideoTrackException(UserVideoTrackException e) {
		ApplicationError.ApplicationErrorBuilder error = new ApplicationError.ApplicationErrorBuilder(
				UserVideoTrackException.getErrorcode(), e.getMessage());
		error.setExceptionStack(e);
		return error.build();
	}

	@ExceptionHandler
	public ApplicationError handle(ConstraintViolationException exception) {
		// you will get all javax failed validation, can be more than one
		// so you can return the set of error messages or just the first message
		String errorMessage = new ArrayList<>(exception.getConstraintViolations()).get(0).getMessage();
		ApplicationError.ApplicationErrorBuilder error = new ApplicationError.ApplicationErrorBuilder(
				Integer.valueOf(HttpStatus.BAD_REQUEST.value()), errorMessage);
		return error.build();
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		List<String> details = new ArrayList<>();
		for (ObjectError error : ex.getBindingResult().getAllErrors()) {
			details.add(error.getDefaultMessage());
		}

		ApplicationError.ApplicationErrorBuilder error = new ApplicationError.ApplicationErrorBuilder(
				Integer.valueOf(HttpStatus.BAD_REQUEST.value()), "Binding violation Mesage");
		error.setViolationMessage(details);
		return new ResponseEntity<Object>(error, HttpStatus.BAD_REQUEST);
	}

}
