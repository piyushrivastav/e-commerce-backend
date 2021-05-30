package com.tom.tom.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Controller
@ControllerAdvice
public class ResourceExceptionHandler {
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<StandardError>  errorResponseEntity (Exception e){
		
		StandardError err = new StandardError(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage(), System.currentTimeMillis());
		ResponseEntity<com.tom.tom.exceptions.StandardError> body = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(err);
		return body;
	}
}