package com.restful.app.exception;

import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@ControllerAdvice
@RestController
public class CustomResponseEntityExceptionHandler extends ResponseEntityExceptionHandler{
	
	private static final String notValidArgumentMessage = "Argument/s not valid"; //TODO: ver internacionalizacion

	@ExceptionHandler(Exception.class)
	public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest wr){
		
		CustomExceptionResponse exceptionResponse = new CustomExceptionResponse(new Date(), ex.getMessage(), wr.getDescription(false));
		
		return new ResponseEntity(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(CustomException.class)
	public final ResponseEntity<Object> handleUserNotFoundExceptions(CustomException ex, WebRequest wr){
		
		CustomExceptionResponse exceptionResponse = new CustomExceptionResponse(new Date(), ex.getMessage(), wr.getDescription(false));
		
		return new ResponseEntity(exceptionResponse, ex.getStatus());
	}
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(
			MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest wr) {
		
		CustomExceptionResponse exceptionResponse = new CustomExceptionResponse(new Date(), notValidArgumentMessage, ex.getBindingResult().toString());

		return new ResponseEntity(exceptionResponse, HttpStatus.BAD_REQUEST);
	}
	
}
 