package com.example.CurrencyExchange.controller;


import com.example.CurrencyExchange.model.Currencies;
import com.example.CurrencyExchange.service.CurrenciesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public List<Currencies> showAllCurrencies() {
        System.out.println(currenciesService.findAll());

        return currenciesService.findAll();
    }


}
