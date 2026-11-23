package com.example.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.demo.exception.EmployeeException;
import com.example.demo.response.ErrorResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {
@ExceptionHandler(EmployeeException.class)
public ResponseEntity<ErrorResponse> handleEmployeeException(EmployeeException e)

{
	ErrorResponse errorResponse= new ErrorResponse();
	errorResponse.setMessage(e.toString());
	errorResponse.setStatus(HttpStatus.NOT_FOUND);
	return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.NOT_FOUND);
	
	
}
}
