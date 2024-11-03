package com.example.b6_validation_app.controller;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestController;

import com.example.b6_validation_app.common.RegularAmount;

import jakarta.validation.Valid;

@RestController
@Validated
public class BenefitsController {

  @GetMapping("/calculate")
  public String calculate(@Valid @ModelAttribute RegularAmount request) {
    return "Validation Passed";
  }

}
