package com.harlansoft.restfulwebservices.exception;

import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.harlansoft.restfulwebservices.resources.users.exception.UserNotFoundException;

// overrides default exception handling provided by spring for responses
// i want this to be applied to every rest controller/resource
@ControllerAdvice
public class CustomResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(UserNotFoundException.class)
	public final ResponseEntity<Object> handleUserNotFoundException
		(Exception ex, WebRequest request) {
		
		// whenever there is an exception, i want to create my specific bean
		ExceptionResponse exResponse = createExceptionResponse(ex, request);
		return new ResponseEntity<Object>(exResponse, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(Exception.class)
	public final ResponseEntity<Object> handleAllExceptions
		(Exception ex, WebRequest request) {
		
		// whenever there is an exception, i want to create my specific bean
		ExceptionResponse exResponse = createExceptionResponse(ex, request);
		
		return new ResponseEntity<Object>(exResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	/*
	 * This handles all errors coming from javax.Validation when invalid input is provided
	 */
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(
			MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

		ExceptionResponse exResponse = createFailedValidationResponse(ex);
		return new ResponseEntity<Object>(exResponse, HttpStatus.BAD_REQUEST);

	}
	
	private ExceptionResponse createFailedValidationResponse(MethodArgumentNotValidException ex) {
		return ExceptionResponse.builder()
				.timestamp(new Date())
				.message("Validation failed")
				.details(ex.getBindingResult().toString())
				.build();
	}
	
	private ExceptionResponse createExceptionResponse(Exception ex, WebRequest request) {
//		return new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false));
		return ExceptionResponse.builder()
				.timestamp(new Date())
				.message(ex.getMessage())
				.details(request.getDescription(false))
				.build();
		
	}
	
}
