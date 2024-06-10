package com.cg.bms.exception;

import javax.security.auth.login.AccountException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.HttpClientErrorException;

import com.cg.bms.dto.ErrorResponse;

import jakarta.validation.ConstraintViolationException;

@ControllerAdvice
public class ConsumerGlobalExcheptionHandler {
	@ExceptionHandler(AccountException.class)
	public ResponseEntity<ErrorResponse> handleAccountServiceError(AccountException ex) {
		ErrorResponse errorResponse = new ErrorResponse(ex.getClass().getSimpleName(), ex.getMessage());
		return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(HttpClientErrorException.class)
	public ResponseEntity<ErrorResponse> handleHttpErrors(HttpClientErrorException ex) {
		ErrorResponse responseBodyAs = ex.getResponseBodyAs(ErrorResponse.class);

		return new ResponseEntity<>(responseBodyAs, ex.getStatusCode());
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorResponse> handleValidationExceptions(MethodArgumentNotValidException ex) {
		ErrorResponse responseBodyAs = new ErrorResponse("ValidationError",
				ex.getFieldError().getField() + " : " + ex.getFieldError().getDefaultMessage());

		return new ResponseEntity<>(responseBodyAs, ex.getStatusCode());
	}
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<ErrorResponse> handleConstraintViolationException(ConstraintViolationException ex) {
		ErrorResponse responseBodyAs = new ErrorResponse("ValidationError",
				ex.getMessage());

		return new ResponseEntity<>(responseBodyAs, HttpStatus.BAD_REQUEST);
	}
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponse> handleGenericError(Exception ex) {
		ErrorResponse errorResponse = new ErrorResponse("InternalServerError", "An unexpected error occurred.");
		return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
