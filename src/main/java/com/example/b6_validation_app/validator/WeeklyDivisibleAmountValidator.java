package com.example.b6_validation_app.validator;

import com.example.b6_validation_app.common.RegularAmount;
import com.example.b6_validation_app.annotation.WeeklyDivisibleAmount;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class WeeklyDivisibleAmountValidator implements ConstraintValidator<WeeklyDivisibleAmount, RegularAmount> {

  @Override
  public boolean isValid(RegularAmount regularAmount, ConstraintValidatorContext context) {
    return true;
  }
}
