package com.example.CurrencyExchange.controller;


import com.example.CurrencyExchange.model.Currency;
import com.example.CurrencyExchange.service.CurrenciesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/currencies")
public class CurrenciesController {


    private final CurrenciesService currenciesService;

    @Autowired
    public CurrenciesController(CurrenciesService currenciesService) {
        this.currenciesService = currenciesService;
    }


    @GetMapping
    public List<Currency> showAllCurrencies() {

        return currenciesService.findAll();
    }

    @GetMapping("/{code}")
    public Currency showCurrencyByCode(@PathVariable String code){
        return currenciesService.findByCode(code);
    }

    @PostMapping
    public void createCurrency(@RequestBody Currency currency){
        System.out.println(currency);
        currenciesService.save(currency);
    }


}
