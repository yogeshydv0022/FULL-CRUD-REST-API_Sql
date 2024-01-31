package com.files.customException;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class UserErrorHandler {

	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<ErrorType> handleNotFound(UserNotFoundException unfe){
		
		return new ResponseEntity<ErrorType>(
				new ErrorType(
						new Date(System.currentTimeMillis()).toString(), 
						"404- NOT FOUND", 
						unfe.getMessage()), 
				HttpStatus.NOT_FOUND);
	}
}
