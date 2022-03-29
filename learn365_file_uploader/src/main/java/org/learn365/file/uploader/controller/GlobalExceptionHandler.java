package org.learn365.file.uploader.controller;

import org.learn365.file.uploader.model.ApplicationError;
import org.learn365.file.uploader.model.TestException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

import static org.learn365.file.uploader.constants.TestConstants.*;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    private static final Integer INTERNAL_SERVER_ERROR = 500;

    @ExceptionHandler
    public ResponseEntity genericException(Exception e) {
        ApplicationError.ApplicationErrorBuilder error = new ApplicationError.ApplicationErrorBuilder(
                INTERNAL_SERVER_ERROR, e.getMessage());
        error.setExceptionStack(e);
        return new ResponseEntity<>(error.build(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(TestException.class)
    public ResponseEntity<Object> genericException(TestException e) {
        ApplicationError.ApplicationErrorBuilder error = new ApplicationError.ApplicationErrorBuilder(
                HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage());
        error.setExceptionStack(e);
        return new ResponseEntity<>(error.build(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(MultipartException.class)
    public ModelAndView handleError1(MultipartException e, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute(MESSAGE, ERROR_MESSAGE_PREFIX + e.getMessage());
        return new ModelAndView(UPLOAD_STATUS_PAGE, redirectAttributes.getFlashAttributes());
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity handle(ConstraintViolationException exception) {
        // you will get all javax failed validation, can be more than one
        // so you can return the set of error messages or just the first message
        String errorMessage = new ArrayList<>(exception.getConstraintViolations()).get(0).getMessage();
        ApplicationError.ApplicationErrorBuilder error = new ApplicationError.ApplicationErrorBuilder(
                HttpStatus.BAD_REQUEST.value(), errorMessage);
        return new ResponseEntity<>(error.build(), HttpStatus.BAD_REQUEST);

    }

    @SuppressWarnings("rawtypes")
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<String> details = new ArrayList<>();
        for (ObjectError error : ex.getBindingResult().getAllErrors()) {
            details.add(error.getDefaultMessage());
        }

        ApplicationError.ApplicationErrorBuilder error = new ApplicationError.ApplicationErrorBuilder(
                HttpStatus.BAD_REQUEST.value(), BINDING_VIOLATION_ERROR_MESSAGE);
        error.setViolationMessage(details);
        return new ResponseEntity<>(error.build(), HttpStatus.BAD_REQUEST);
    }

}
