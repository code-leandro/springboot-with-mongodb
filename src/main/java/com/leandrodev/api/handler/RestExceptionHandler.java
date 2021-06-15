package com.leandrodev.api.handler;

import com.leandrodev.api.exceptions.ExceptionDetails;
import com.leandrodev.api.exceptions.NotFoundException;
import com.leandrodev.api.exceptions.ExceptionDetails;
import com.leandrodev.api.exceptions.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class RestExceptionHandler {

	@ExceptionHandler(NotFoundException.class)
	public ResponseEntity<ExceptionDetails> handlerNotFoundException(NotFoundException notFoundException) {
		return new ResponseEntity<>(
			ExceptionDetails.builder()
			.statusCode(HttpStatus.NOT_FOUND.value())
			.message("NOT FOUND")
			.build(), HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ExceptionDetails> methodArgumentException(MethodArgumentNotValidException ex) {
		
		List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
		String fieldsMessages = fieldErrors.stream().map(FieldError::getDefaultMessage).collect(Collectors.joining(""));
		
		return new ResponseEntity<>(
				ExceptionDetails.builder()
				.statusCode(HttpStatus.BAD_REQUEST.value())
				.message(fieldsMessages)
				.build(), HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<ExceptionDetails> httpMessageNotReadableException(HttpMessageNotReadableException ex) {
		
		return new ResponseEntity<>(
				ExceptionDetails.builder()
				.statusCode(HttpStatus.BAD_REQUEST.value())
				.message("Your JSON has a wrong")
				.build(), HttpStatus.BAD_REQUEST);
	}
}
