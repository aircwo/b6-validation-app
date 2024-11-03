package com.example.b6_validation_app.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Frequency {
  WEEK(1),
  TWO_WEEK(2);

  private final int weekMultiple;

  public boolean isWeeklyMultiple() {
    return weekMultiple > 0;
  }
}
