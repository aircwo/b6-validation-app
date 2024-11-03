package com.example.b6_validation_app.common;

import lombok.Data;

@Data
public class RegularAmount {

  private String amount;
  private Frequency frequency;

  public Integer getAmountAsPence() {
    try {
      return amount != null ? Integer.parseInt(amount) : null;
    } catch (NumberFormatException e) {
      return null;
    }
  }
}
