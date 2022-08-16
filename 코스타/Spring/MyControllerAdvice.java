package com.example.demo.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class MyControllerAdvice {
	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> exceptionHandle(Exception e){
		return new ResponseEntity<>("※" + e.getMessage() +"※", HttpStatus.OK);
	}

	@ExceptionHandler({MethodArgumentNotValidException.class})
	public ResponseEntity<String> validException(
			MethodArgumentNotValidException ex) {
		String msg =ex.getBindingResult().getAllErrors().get(0).getDefaultMessage();

		return new ResponseEntity<>(msg, HttpStatus.OK); 
	}
}
