package com.leandrodev.api.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class BadRequestException extends RuntimeException {

	private static final long serialVersionUID = 8688352946649482476L;

	public BadRequestException(String message) {
		super(message);
	}
}
