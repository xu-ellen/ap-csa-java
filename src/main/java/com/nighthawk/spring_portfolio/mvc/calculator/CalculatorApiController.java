package com.nighthawk.spring_portfolio.mvc.calculator;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

@RestController
@RequestMapping("/api/calculator")
public class CalculatorApiController {

  /**
   * GET isLeapYear endpoint
   * ObjectMapper throws exceptions on bad JSON
   * 
   * @throws JsonProcessingException
   * @throws JsonMappingException
   */
  @GetMapping("/{expression}")
  public ResponseEntity<String> getCalculation(@PathVariable("expression") String expression) throws JsonMappingException, JsonProcessingException {
    Calculator calculation = new Calculator(expression);
    String result = calculation.toString(); // this requires exception handling

    return new ResponseEntity<String>(result, HttpStatus.OK); // JSON response, see ExceptionHandlerAdvice for throws
  }
}