package com.example.CurrencyExchange.controller;


import com.example.CurrencyExchange.model.Exchange;
import com.example.CurrencyExchange.repository.CurrenciesRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExchangeController {

    //маленький шрифт потом сделать
    private final CurrenciesRepository currenciesRepository;

    public ExchangeController(CurrenciesRepository currenciesRepository) {
        this.currenciesRepository = currenciesRepository;
    }


    @GetMapping
    public Exchange exchangeRates(@RequestAttribute String BASE_CURRENCY_CODE,
                                  @RequestAttribute String TARGET_CURRENCY_CODE,
                                  @RequestAttribute int AMOUNT){


        //поиск id курса через exchangeRatesService
        // rates * amount

//        Exchange exchange = new Exchange(id, amount);
//        return exchange;

        return null;
    }
}
