package com.cg.bms.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class AccountGlobalExceptionHandler {
	
	@ExceptionHandler(AccountException.class)
    public ResponseEntity<ErrorResponse> handleAccountServiceError(AccountException ex) {
        ErrorResponse errorResponse = new ErrorResponse(ex.getClass().getSimpleName(), ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGenericError(Exception ex) {
        ErrorResponse errorResponse = new ErrorResponse("InternalServerError", "An unexpected error occurred.");
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
