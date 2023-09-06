package com.example.CurrencyExchange.controller;

import com.example.CurrencyExchange.dto.ExchangeRatesDTO;
import com.example.CurrencyExchange.model.ExchangeRates;
import com.example.CurrencyExchange.service.CurrenciesService;
import com.example.CurrencyExchange.service.ExchangeRatesService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("exchangeRates")
public class ExchangeRatesController {

    private final ExchangeRatesService exchangeRatesService;
    private final CurrenciesService currenciesService;

    public ExchangeRatesController(ExchangeRatesService exchangeRatesService, CurrenciesService currenciesService) {
        this.exchangeRatesService = exchangeRatesService;
        this.currenciesService = currenciesService;
    }


    @GetMapping
    public List<ExchangeRates> showAllCurrencies() {
        return this.exchangeRatesService.findAll();
    }

    @GetMapping("/{baseCurrencyCode} + {targetCurrencyCode} }")
    public Optional<ExchangeRates> showByBaseAndTargetCode(@PathVariable String baseCurrencyCode, @PathVariable String targetCurrencyCode) {

        return Optional.ofNullable(this.exchangeRatesService.findByBaseCurrencyIdAndTargetCurrencyId(
                this.currenciesService.findByCode(baseCurrencyCode).getId(),
                this.currenciesService.findByCode(targetCurrencyCode).getId()
        ));
    }

    @PostMapping
    public void createExchangeRates(@RequestBody ExchangeRatesDTO exchangeRatesDTO) {
        this.exchangeRatesService.save(exchangeRatesDTO.convertToExchangeRates());
    }

    @PatchMapping("/{baseCurrencyCode} + {targetCurrencyCode} }")
    public void updateByBaseAndTargetCode(@RequestBody ExchangeRatesDTO exchangeRatesDTO,
                                          @PathVariable String baseCurrencyCode,
                                          @PathVariable String targetCurrencyCode) {




        this.exchangeRatesService.save(exchangeRatesDTO.convertToExchangeRates(
                this.exchangeRatesService.findByBaseCurrencyCodeAndTargetCurrencyCode
                (baseCurrencyCode, targetCurrencyCode).getId()));

    }


}
