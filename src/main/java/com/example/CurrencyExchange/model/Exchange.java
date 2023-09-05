package com.example.CurrencyExchange.model;

import com.example.CurrencyExchange.service.ExchangeRatesService;

public class Exchange {

    public final ExchangeRatesService exchangeRatesService;

    public String b;

    public String t;

    public String rates;



    public Exchange(ExchangeRatesService exchangeRatesService) {
        this.exchangeRatesService = exchangeRatesService;
    }


}
