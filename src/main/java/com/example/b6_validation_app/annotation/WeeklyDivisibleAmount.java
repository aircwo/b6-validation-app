package com.example.b6_validation_app.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.example.b6_validation_app.validator.WeeklyDivisibleAmountValidator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Constraint(validatedBy = WeeklyDivisibleAmountValidator.class)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface WeeklyDivisibleAmount {
    String message() default "Amount must be divisible into whole pence when split into weekly payments";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}