package com.example.b6_validation_app.validator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.example.b6_validation_app.common.RegularAmount;

import jakarta.validation.ConstraintValidator;

class WeeklyDivisibleAmountValidatorTest {

  @Test
  void shouldImplementConstraintValidator() {
    // given
    WeeklyDivisibleAmountValidator validator = new WeeklyDivisibleAmountValidator();

    // when
    boolean implementsInterface = validator instanceof ConstraintValidator;

    // then
    assertTrue(implementsInterface);
  }

  @Test
  void shouldHaveIsValidMethod() throws NoSuchMethodException, SecurityException {
    // given
    WeeklyDivisibleAmountValidator validator = new WeeklyDivisibleAmountValidator();

    // when
    java.lang.reflect.Method isValidMethod = validator.getClass().getMethod("isValid", RegularAmount.class, javax.validation.ConstraintValidatorContext.class);

    // then
    assertNotNull(isValidMethod);
    assertEquals(boolean.class, isValidMethod.getReturnType());
  }
}
