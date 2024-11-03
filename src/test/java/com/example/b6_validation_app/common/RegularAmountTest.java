package com.example.b6_validation_app.common;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class RegularAmountTest {

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
}