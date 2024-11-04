package com.example.b6_validation_app.common;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.stream.Stream;

class FrequencyTest {

  @ParameterizedTest
  @MethodSource("frequencyAndExpectedMultipleTestData")
  void weekMultipleShouldMatchFrequencyMultiple(Frequency frequency, int expectedWeekMultiple) {
    // given / when
    int actualWeekMultiple = frequency.getWeekMultiple();

    // then
    assertEquals(expectedWeekMultiple, actualWeekMultiple);
  }

  @ParameterizedTest
  @EnumSource(Frequency.class)
  void frequenciesShouldHavePositiveWeekMultiple(Frequency frequency) {
    // given / when
    int weekMultiple = frequency.getWeekMultiple();

    // then
    assertTrue(weekMultiple > 0, String.format("%s should have positive week multiple", frequency));
  }

  @Test
  void testMonthAndFourWeekHaveSameMultiple() {
    // given
    Frequency month = Frequency.MONTH;
    Frequency fourWeek = Frequency.FOUR_WEEK;

    // when
    int monthMultiple = month.getWeekMultiple();
    int fourWeekMultiple = fourWeek.getWeekMultiple();

    // then
    assertEquals(monthMultiple, fourWeekMultiple);
  }

  private static Stream<Arguments> frequencyAndExpectedMultipleTestData() {
    return Stream.of(
      Arguments.of(Frequency.WEEK, 1),
      Arguments.of(Frequency.TWO_WEEK, 2),
      Arguments.of(Frequency.FOUR_WEEK, 4),
      Arguments.of(Frequency.MONTH, 4),
      Arguments.of(Frequency.QUARTER, 13),
      Arguments.of(Frequency.YEAR, 52)
    );
  }
}
