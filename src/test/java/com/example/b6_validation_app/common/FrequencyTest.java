package com.example.b6_validation_app.common;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.EnumSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.stream.Stream;

class FrequencyTest {

  @ParameterizedTest
  @MethodSource("frequencyAndExpectedMultipleTestData")
  void weekMultipleShouldMatchFrequencyMultiple(Frequency frequency, int expectedWeekMultiple) {
    // given / when
    int actualWeekMultiple = frequency.getWeekMultiple();
    boolean isWeeklyMultiple = frequency.isWeeklyMultiple();

    // then
    assertEquals(expectedWeekMultiple, actualWeekMultiple);
    assertTrue(isWeeklyMultiple);
  }

  private static Stream<Arguments> frequencyAndExpectedMultipleTestData() {
    return Stream.of(
      Arguments.of(Frequency.WEEK, 1),
      Arguments.of(Frequency.TWO_WEEK, 2)
    );
  }

  @ParameterizedTest
  @EnumSource(Frequency.class)
  void frequenciesShouldHavePositiveWeekMultiple(Frequency frequency) {
    // given / when
    int weekMultiple = frequency.getWeekMultiple();

    // then
    assertTrue(weekMultiple > 0, String.format("%s should have positive week multiple", frequency));
  }

  @ParameterizedTest
  @EnumSource(Frequency.class)
  void frequenciesAreWeeklyMultiples(Frequency frequency) {
    // given / when
    boolean isWeeklyMultiple = frequency.isWeeklyMultiple();

    // then
    assertTrue(isWeeklyMultiple, String.format("%s should be a weekly multiple", frequency));
  }
}
