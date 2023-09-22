package com.example.CurrencyExchange.controller;

import com.example.CurrencyExchange.dto.ExchangeRatesDTO;
import com.example.CurrencyExchange.model.ExchangeRates;
import com.example.CurrencyExchange.service.ExchangeRatesService;
import com.example.CurrencyExchange.util.Exception.CurrencyException.*;
import com.example.CurrencyExchange.util.Exception.ExceptionResponse;
import com.example.CurrencyExchange.util.Exception.ExchangeRatesException.Exceptions.*;
import org.postgresql.util.PSQLException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequestMapping("/exchangeRates")
public class ExchangeRatesController {

    private final ExchangeRatesService exchangeRatesService;

    public ExchangeRatesController(ExchangeRatesService exchangeRatesService) {
        this.exchangeRatesService = exchangeRatesService;
    }

    @GetMapping("/")
    public ModelAndView redirectFromHome() {
        return new ModelAndView("redirect:" + "/exchangeRates");
    }

    @GetMapping
    public ResponseEntity<List<ExchangeRates>> showAllCurrencies() {
        return new ResponseEntity<>(this.exchangeRatesService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{currencyCode}")
    public ExchangeRatesDTO showByBaseAndTargetCode(@PathVariable String currencyCode) {

        return this.exchangeRatesService.
                findByBaseCurrencyCodeAndTargetCurrencyCode(currencyCode);
    }

    @PostMapping
    public void createExchangeRates(@RequestBody ExchangeRatesDTO exchangeRatesDTO) {

        this.exchangeRatesService.save(exchangeRatesDTO);
    }

    @PatchMapping()
    public void updateByBaseAndTargetCode(@RequestBody ExchangeRatesDTO exchangeRatesDTO) {

        this.exchangeRatesService.update(exchangeRatesDTO);

    }

    @ExceptionHandler
    private ResponseEntity<ExceptionResponse> handleException(PSQLException e) {

        ExceptionResponse response = new ExceptionResponse("Database is not connection");

        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler
    private ResponseEntity<ExceptionResponse> handleException(ExchangeRatesNotFoundException e) {

        ExceptionResponse response = new ExceptionResponse("Exchange rate not found");

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    private ResponseEntity<ExceptionResponse> handleException(ExchangeRateMissingException e) {

        ExceptionResponse response = new ExceptionResponse("Currency code missing in the address");

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    private ResponseEntity<ExceptionResponse> handleException(CurrencyNotFoundException e) {

        ExceptionResponse response = new ExceptionResponse("Currency not found");

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    private ResponseEntity<ExceptionResponse> handleException(CurrencyMissingException e) {

        ExceptionResponse response = new ExceptionResponse("Currency code is missing in address");

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler
    private ResponseEntity<ExceptionResponse> handleException(CurrencyExistsException e) {

        ExceptionResponse response = new ExceptionResponse("Currency with this code already exists");

        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }

    @ExceptionHandler
    private ResponseEntity<ExceptionResponse> handleException(CurrencyRequiredFieldMissingException e) {

        ExceptionResponse response = new ExceptionResponse("Required form field is missing");

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    private ResponseEntity<ExceptionResponse> handleException(ExchangeRatesExistsException e) {

        ExceptionResponse response = new ExceptionResponse("Exchange Rates with these codes already exists");

        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }

}
