package com.cg.bms.validation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Documented
@Constraint(validatedBy = AccountNumberValidator.class)
@Target({ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface AccountNumberValidation {
	String message() default "Account number must be 10 digits long.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
    long min() default Long.MIN_VALUE;
    long max() default Long.MAX_VALUE;
}
