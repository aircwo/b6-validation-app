package com.example.b6_validation_app.validator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example.b6_validation_app.common.Frequency;
import com.example.b6_validation_app.common.RegularAmount;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

class WeeklyDivisibleAmountValidatorTest {

  private WeeklyDivisibleAmountValidator validator;
  private ConstraintValidatorContext context;

  @BeforeEach
  void setUp() {
    validator = new WeeklyDivisibleAmountValidator();
    context = mock(ConstraintValidatorContext.class);
  }

  @Test
  void shouldImplementConstraintValidator() {
    // given / when
    boolean implementsInterface = validator instanceof ConstraintValidator;

    // then
    assertTrue(implementsInterface);
  }

  @Test
  void shouldHaveIsValidMethod() throws NoSuchMethodException, SecurityException {
    // given / when
    java.lang.reflect.Method isValidMethod = validator.getClass()
      .getMethod("isValid", RegularAmount.class, ConstraintValidatorContext.class);

    // then
    assertNotNull(isValidMethod);
    assertEquals(boolean.class, isValidMethod.getReturnType());
  }

  @Test
  void shouldPassValidationWhenFrequencyIsNull() {
    // given
    RegularAmount regularAmount = new RegularAmount();
    regularAmount.setAmount("100.00");
    regularAmount.setFrequency(null);

    // when
    boolean isValid = validator.isValid(regularAmount, context);

    // then
    assertTrue(isValid);
  }

  @Test
  void shouldPassValidationWhenAmountIsNull() {
    // given
    RegularAmount regularAmount = new RegularAmount();
    regularAmount.setAmount(null);
    regularAmount.setFrequency(Frequency.FOUR_WEEK);

    // when
    boolean isValid = validator.isValid(regularAmount, context);

    // then
    assertTrue(isValid);
  }

  @Test
  void shouldPassValidationWhenFrequencyIsWeek() {
    // given
    RegularAmount regularAmount = new RegularAmount();
    regularAmount.setFrequency(Frequency.WEEK);

    // when
    boolean isValid = validator.isValid(regularAmount, context);

    // then
    assertTrue(isValid);
  }
}
