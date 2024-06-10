package com.cg.bms.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class AccountNumberValidator implements ConstraintValidator<AccountNumberValidation, Long> {

	private long min;
    private long max;
    
    @Override
    public void initialize(AccountNumberValidation constraintAnnotation) {
    	this.min = constraintAnnotation.min();
        this.max = constraintAnnotation.max();
    }

    @Override
    public boolean isValid(Long value, ConstraintValidatorContext context) {
    	if (value == null) {
            return false; 
        }
        return String.valueOf(value).length() >= min;
    }
}
