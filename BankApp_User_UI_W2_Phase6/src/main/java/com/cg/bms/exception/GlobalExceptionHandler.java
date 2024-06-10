package com.cg.bms.exception;

import javax.security.auth.login.AccountException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.HttpClientErrorException;

import com.cg.bms.model.ErrorResponse;

@ControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler(AccountException.class)
	public ResponseEntity<ErrorResponse> handleAccountServiceError(AccountException ex) {
		System.out.println("AccountException" + ex);
		ErrorResponse errorResponse = new ErrorResponse(ex.getClass().getSimpleName(), ex.getMessage());
		return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(HttpClientErrorException.class)
	public String handleHttpErrors(HttpClientErrorException ex, Model model) {

		ErrorResponse responseBodyAs = ex.getResponseBodyAs(ErrorResponse.class);
		model.addAttribute("error", responseBodyAs.getMessage());
		System.out.println(model.getAttribute("viewName"));
		return "withdraw";
	}

	@ExceptionHandler(GlobalException.class)
	public String handleGlobalException(GlobalException ex, Model model) {

		model.addAttribute("error", ex.getMessage());

		return ex.getViewName();
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorResponse> handleValidationExceptions(MethodArgumentNotValidException ex) {
		System.out.println("MethodArgumentNotValidException" + ex);
		ErrorResponse responseBodyAs = new ErrorResponse("ValidationError",
				ex.getFieldError().getField() + " : " + ex.getFieldError().getDefaultMessage());

		return new ResponseEntity<>(responseBodyAs, ex.getStatusCode());
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponse> handleGenericError(Exception ex) {
		System.out.println("Exception" + ex);
		ErrorResponse errorResponse = new ErrorResponse("InternalServerError", "An unexpected error occurred.");
		return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
