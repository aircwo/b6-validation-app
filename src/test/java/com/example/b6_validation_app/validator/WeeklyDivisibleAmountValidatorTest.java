package com.example.b6_validation_app.validator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

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

  @ParameterizedTest
  @MethodSource("weekAndMonthTestCase")
  void shouldPassValidationWhenFrequencyIsMonth(Frequency frequency) {
    // given
    RegularAmount regularAmount = new RegularAmount();
    regularAmount.setFrequency(frequency);

    // when
    boolean isValid = validator.isValid(regularAmount, context);

    // then
    assertTrue(isValid);
  }

  @ParameterizedTest(name = "When amount is {0} and frequency is {1}, validation should pass")
  @MethodSource("nullCheckTestCases")
  void shouldPassValidationForNullValues(String amount, Frequency frequency) {
    // given
    RegularAmount regularAmount = new RegularAmount();
    regularAmount.setAmount(amount);
    regularAmount.setFrequency(frequency);

    // when
    boolean isValid = validator.isValid(regularAmount, context);

    // then
    assertTrue(isValid, String.format("Validation should pass when amount is '%s' and frequency is '%s'", amount, frequency));
  }

  private static Stream<Arguments> nullCheckTestCases() {
    return Stream.of(
      Arguments.of("100.00", null),
      Arguments.of("0.00", null),
      Arguments.of("123.45", null),
      Arguments.of(null, Frequency.WEEK),
      Arguments.of(null, Frequency.TWO_WEEK),
      Arguments.of(null, Frequency.MONTH),
      Arguments.of(null, null)
    );
  }

  private static Stream<Arguments> weekAndMonthTestCase() {
    return Stream.of(
      Arguments.of(Frequency.WEEK),
      Arguments.of(Frequency.MONTH)
    );
  }
}
