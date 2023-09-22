package com.example.CurrencyExchange.controller;


import com.example.CurrencyExchange.dto.CurrencyDTO;
import com.example.CurrencyExchange.model.Currency;
import com.example.CurrencyExchange.service.CurrenciesService;
import com.example.CurrencyExchange.util.Exception.*;
import com.example.CurrencyExchange.util.Exception.CurrencyException.*;
import org.postgresql.util.PSQLException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;


@RestController
@RequestMapping("/currencies")
public class CurrenciesController {


    private final CurrenciesService currenciesService;

    public CurrenciesController(CurrenciesService currenciesService) {
        this.currenciesService = currenciesService;
    }

    @GetMapping
    public List<Currency> showAllCurrencies() {
        return this.currenciesService.findAll();
    }

    @GetMapping("/{code}")
    public Currency showCurrencyByCode(@PathVariable String code) {
        return this.currenciesService.findByCode(code);
    }

    @GetMapping("/")
    public ModelAndView redirectFromHome() {
        return new ModelAndView("redirect:" + "/currencies");
    }

    @PostMapping
    public void createCurrency(@RequestBody CurrencyDTO currencyDTO) {
        this.currenciesService.save(currencyDTO);
    }

    @PatchMapping
    public void updateCurrency(@RequestBody CurrencyDTO currencyDTO) {
        this.currenciesService.update(currencyDTO);
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
    private ResponseEntity<ExceptionResponse> handleException(PSQLException e) {

        ExceptionResponse response = new ExceptionResponse("Database is not connection");

        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
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


}
