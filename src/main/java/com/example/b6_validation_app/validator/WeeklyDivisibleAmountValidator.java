package com.example.b6_validation_app.validator;

import com.example.b6_validation_app.common.RegularAmount;
import com.example.b6_validation_app.annotation.WeeklyDivisibleAmount;
import com.example.b6_validation_app.common.Frequency;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class WeeklyDivisibleAmountValidator implements ConstraintValidator<WeeklyDivisibleAmount, RegularAmount> {

  @Override
  public boolean isValid(RegularAmount regularAmount, ConstraintValidatorContext context) {
    Frequency frequency = regularAmount.getFrequency();

    if (frequency == null || regularAmount.getAmount() == null) {
      return true;
    }

    if (frequency == Frequency.WEEK) {
      return true;
    }

    return true;
  }
}
