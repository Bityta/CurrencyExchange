package com.example.CurrencyExchange.controller;


import com.example.CurrencyExchange.model.Exchange;
import com.example.CurrencyExchange.service.ExchangeRatesService;
import com.example.CurrencyExchange.util.Exception.CurrencyException.CurrencyMissingException;
import com.example.CurrencyExchange.util.Exception.CurrencyException.CurrencyNotFoundException;
import com.example.CurrencyExchange.util.Exception.ExceptionResponse;
import com.example.CurrencyExchange.util.Exception.ExchangeRatesException.Exceptions.ExchangeRatesNotFoundException;
import org.postgresql.util.PSQLException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/exchange")
public class ExchangeController {
    private final ExchangeRatesService exchangeRatesService;

    public ExchangeController(ExchangeRatesService exchangeRatesService) {
        this.exchangeRatesService = exchangeRatesService;
    }


    @GetMapping
    public Exchange exchangeRates(@RequestParam(value = "b", required = false) String baseCurrencyCode,
                                  @RequestParam(value = "t", required = false) String targetCurrencyCode,
                                  @RequestParam(value = "amount", required = false) double amount) {


        return this.exchangeRatesService.getExchange(baseCurrencyCode, targetCurrencyCode, amount);
    }

    @GetMapping("/")
    public ModelAndView redirectFromHome() {
        return new ModelAndView("redirect:" + " /exchange");
    }


    @ExceptionHandler
    private ResponseEntity<ExceptionResponse> handleException(ExchangeRatesNotFoundException e) {

        ExceptionResponse response = new ExceptionResponse("Exchange rate not found");

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    private ResponseEntity<ExceptionResponse> handleException(CurrencyMissingException e) {

        ExceptionResponse response = new ExceptionResponse("Currency code is missing in address");

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    private ResponseEntity<ExceptionResponse> handleException(CurrencyNotFoundException e) {

        ExceptionResponse response = new ExceptionResponse("Currency not found");

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    private ResponseEntity<ExceptionResponse> handleException(PSQLException e) {

        ExceptionResponse response = new ExceptionResponse("Database is not connection");

        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler
    private ResponseEntity<ExceptionResponse> handleException(IllegalStateException e) {

        ExceptionResponse response = new ExceptionResponse("Invalid request");

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
