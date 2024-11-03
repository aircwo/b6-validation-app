package com.example.b6_validation_app.annotation;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Set;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.example.b6_validation_app.common.Frequency;
import com.example.b6_validation_app.common.RegularAmount;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

class WeeklyDivisibleAmountTest {

  private static Validator validator;

  @BeforeAll
  public static void setUpValidator() {
    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    validator = factory.getValidator();
  }

  @Test
  void shouldUseAnnotationErrorMessageOnValidationError() {
    // given
    RegularAmount invalidAmount = new RegularAmount(); // could use a builder test factory
    invalidAmount.setAmount("100.01");
    invalidAmount.setFrequency(Frequency.TWO_WEEK);

    // when
    Set<ConstraintViolation<RegularAmount>> violations = validator.validate(invalidAmount);

    // then
    assertEquals(1, violations.size());
    ConstraintViolation<RegularAmount> violation = violations.iterator().next();
    String expectedMessage = "Amount must be divisible into whole pence when split into weekly payments";
    assertEquals(expectedMessage, violation.getMessage());
  }
}