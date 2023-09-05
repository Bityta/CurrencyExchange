package com.example.CurrencyExchange.controller;

import com.example.CurrencyExchange.dto.ExchangeRatesDTO;
import com.example.CurrencyExchange.model.ExchangeRates;
import com.example.CurrencyExchange.service.ExchangeRatesService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("exchangeRates")
public class ExchangeRatesController {

    private final ExchangeRatesService exchangeRatesService;

    public ExchangeRatesController(ExchangeRatesService exchangeRatesService) {
        this.exchangeRatesService = exchangeRatesService;
    }


    @GetMapping
    public List<ExchangeRates> showAllCurrencies() {
        return this.exchangeRatesService.findAll();
    }

    @GetMapping("/{baseCurrencyCode} + {targetCurrencyCode} }")
    public Optional<ExchangeRates> showByBaseAndTargetCode(@PathVariable String baseCurrencyCode, @PathVariable String targetCurrencyCode) {

        //как то получаем id
        int id = 0;
        return this.exchangeRatesService.findById(id);
    }

    @PostMapping
    public void createExchangeRates(@RequestBody ExchangeRatesDTO exchangeRatesDTO) {
        this.exchangeRatesService.save(exchangeRatesDTO.convertToExchangeRates());
    }

    @PatchMapping("/{baseCurrencyCode} + {targetCurrencyCode} }")
    public void updateByBaseAndTargetCode(@RequestBody ExchangeRatesDTO exchangeRatesDTO,@PathVariable String baseCurrencyCode, @PathVariable String targetCurrencyCode) {

        //как то получаем индекс

        this.exchangeRatesService.save(exchangeRatesDTO.convertToExchangeRates());

    }


}
