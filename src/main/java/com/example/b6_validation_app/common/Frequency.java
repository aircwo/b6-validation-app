package com.example.b6_validation_app.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Frequency Enum outlining the accepted frequencies within a RegularAmount object.
 * All args constructor removes bloat here with Frequency(weekMultiple) auto-generated.
 */
@Getter
@AllArgsConstructor
public enum Frequency {
  WEEK(1),
  TWO_WEEK(2),
  FOUR_WEEK(4),
  MONTH(4),
  QUARTER(13),
  YEAR(52);

  private final int weekMultiple;

  public boolean isWeeklyMultiple() {
    return weekMultiple > 0;
  }
}
