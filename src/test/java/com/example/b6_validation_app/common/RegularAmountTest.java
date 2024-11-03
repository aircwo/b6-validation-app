package com.example.b6_validation_app.common;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class RegularAmountTest {

  // Could use a test data factory to generate objects accordingly but due to time constraint, this will do.
  @Test
  void shouldContainStringAmountAndFrequency() {
    // given
    RegularAmount regularAmount = new RegularAmount();

    // when
    regularAmount.setAmount("1234");
    regularAmount.setFrequency(Frequency.WEEK);

    // then
    assertEquals("1234", regularAmount.getAmount());
    assertEquals(Frequency.WEEK, regularAmount.getFrequency());
  }

  @ParameterizedTest
  @MethodSource("stringVariationTestData")
  void shouldAllowForAmountToContainDifferentTypesOfStrings(String amount, String expectedOutput) {
    // given
    RegularAmount regularAmount = new RegularAmount();

    // when
    regularAmount.setAmount(amount);

    // then
    assertEquals(expectedOutput, regularAmount.getAmount());
  }

  @ParameterizedTest(name = "input: {0} should convert to {1} pence")
  @MethodSource("amountAsPenceTestData")
  void shouldConvertAmountToPence(String amount, Integer expectedPence) {
    // given
    RegularAmount regularAmount = new RegularAmount();

    // when
    regularAmount.setAmount(amount);

    // then
    assertEquals(expectedPence, regularAmount.getAmountAsPence());
  }

  // Can be any stirng value in theory, we anticipate a valid amount before our validation should be hit.
  private static Stream<Arguments> stringVariationTestData() {
    return Stream.of(
      Arguments.of("12.34", "12.34"),
      Arguments.of("0.00", "0.00"),
      Arguments.of("999.99", "999.99"),
      Arguments.of("100", "100"),
      Arguments.of("25", "25"),
      Arguments.of("0", "0"),
      // Leading/trailing zeros
      Arguments.of("01.23", "01.23"),
      Arguments.of("1.20", "1.20"),
      Arguments.of("01.20", "01.20"),
      // Edge cases
      Arguments.of("", ""),
      Arguments.of(null, null),
      Arguments.of(".", "."),
      Arguments.of("..", ".."),
      Arguments.of("12.34.56", "12.34.56"),
      // Invalid formats
      Arguments.of("abc", "abc"),
      Arguments.of("12.3.4", "12.3.4"),
      Arguments.of("12.34£", "12.34£"),
      Arguments.of(" 12.34 ", " 12.34 "),
      // Extreme values
      Arguments.of("999999.99", "999999.99"),
      Arguments.of("-12.34", "-12.34"),
      Arguments.of("+12.34", "+12.34"),
      // Number format variations
      Arguments.of(".34", ".34"),
      Arguments.of("12.", "12."),
      Arguments.of("1234", "1234")
    );
  }

  private static Stream<Arguments> amountAsPenceTestData() {
    return Stream.of(
      Arguments.of("1234", 1234),
      Arguments.of("0", 0),
      Arguments.of("12.34", 1234),
      Arguments.of("0.01", 1),
      Arguments.of("1.00", 100),
      Arguments.of("99.99", 9999),
      Arguments.of(null, null),
      Arguments.of("", null),
      Arguments.of(".", null),
      Arguments.of("..", null),
      Arguments.of("abc", null),
      Arguments.of("£12.34", null),
      Arguments.of("12.34£", null)
    );
  }
}