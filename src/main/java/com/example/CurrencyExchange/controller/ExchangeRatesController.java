package com.example.CurrencyExchange.controller;

import com.example.CurrencyExchange.dto.ExchangeRatesDTO;
import com.example.CurrencyExchange.model.ExchangeRates;
import com.example.CurrencyExchange.service.ExchangeRatesService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/exchangeRates")
public class ExchangeRatesController {

    private final ExchangeRatesService exchangeRatesService;

    public ExchangeRatesController(ExchangeRatesService exchangeRatesService) {
        this.exchangeRatesService = exchangeRatesService;
    }


    @GetMapping
    public List<ExchangeRates> showAllCurrencies() {
        return this.exchangeRatesService.findAll();
    }

    @GetMapping("/{currencyCode}")
    public Optional<ExchangeRatesDTO> showByBaseAndTargetCode(@PathVariable String currencyCode) {

        String baseCurrencyCode = currencyCode.substring(3,6);
        String targetCurrencyCode = currencyCode.substring(0,3);


        return Optional.ofNullable(this.exchangeRatesService.findByBaseCurrencyCodeAndTargetCurrencyCode
                (baseCurrencyCode, targetCurrencyCode).convertToExchangeRatesDTO());
    }

    @PostMapping
    public void createExchangeRates(@RequestBody ExchangeRatesDTO exchangeRatesDTO) {

        //конверт убрать в сервис
        this.exchangeRatesService.save(exchangeRatesDTO.convertToExchangeRates());
    }

    @PatchMapping("/{currencyCode}")
    public void updateByBaseAndTargetCode(@RequestBody ExchangeRatesDTO exchangeRatesDTO,
                                          @PathVariable String currencyCode) {


        //дто вынести в сервис

        String baseCurrencyCode = currencyCode.substring(3,6);
        String targetCurrencyCode = currencyCode.substring(0,3);

        this.exchangeRatesService.save(exchangeRatesDTO.convertToExchangeRates(
                this.exchangeRatesService.findByBaseCurrencyCodeAndTargetCurrencyCode
                (baseCurrencyCode, targetCurrencyCode).getId()));

    }


}
