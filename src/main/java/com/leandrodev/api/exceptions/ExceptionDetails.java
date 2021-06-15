package com.leandrodev.api.exceptions;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ExceptionDetails {

	private int statusCode;
	private String message;
}
