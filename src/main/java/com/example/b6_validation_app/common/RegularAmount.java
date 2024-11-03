package com.example.b6_validation_app.common;

import com.example.b6_validation_app.annotation.WeeklyDivisibleAmount;
import lombok.Data;

@Data
@WeeklyDivisibleAmount
public class RegularAmount {

  private String amount;
  private Frequency frequency;

  public Integer getAmountAsPence() {
    try {
      return amount != null ? Integer.parseInt(amount.replace(".", "")) : null;
    } catch (NumberFormatException e) {
      return null;
    }
  }
}
