package com.example.CurrencyExchange.service;


import com.example.CurrencyExchange.model.ExchangeRates;
import com.example.CurrencyExchange.model.Currency;
import com.example.CurrencyExchange.repository.ExchangeRatesRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class ExchangeRatesService {

    private final ExchangeRatesRepository exchangeRatesRepository;

    private final CurrenciesService currenciesService;

    public ExchangeRatesService(ExchangeRatesRepository exchangeRatesRepository, CurrenciesService currenciesService) {
        this.exchangeRatesRepository = exchangeRatesRepository;
        this.currenciesService = currenciesService;
    }


    public List<ExchangeRates> findAll() {
        return this.exchangeRatesRepository.findAll();
    }

    public ExchangeRates findByBaseCurrencyIdAndTargetCurrencyId(Currency baseCurrencyId, Currency targetCurrencyId) {

        if (this.exchangeRatesRepository.findByBaseCurrencyIdAndTargetCurrencyId(baseCurrencyId, targetCurrencyId) != null) {
            return this.exchangeRatesRepository.findByBaseCurrencyIdAndTargetCurrencyId(baseCurrencyId, targetCurrencyId);
        }

        return this.exchangeRatesRepository.findByBaseCurrencyIdAndTargetCurrencyId(targetCurrencyId, baseCurrencyId);

    }


    public ExchangeRates findByBaseCurrencyCodeAndTargetCurrencyCode(String b, String t){


        return this.findByBaseCurrencyIdAndTargetCurrencyId(
                this.currenciesService.findByCode(b),
                this.currenciesService.findByCode(t)
        );


    }

    @Transactional
    public void save(ExchangeRates exchangeRates){

        this.currenciesService.save(exchangeRates.getBaseCurrencyId());
        this.currenciesService.save(exchangeRates.getTargetCurrencyId());


        if (exchangeRatesRepository.findByBaseCurrencyIdAndTargetCurrencyId(
                exchangeRates.getBaseCurrencyId(), exchangeRates.getTargetCurrencyId()) == null){
            this.exchangeRatesRepository.save(exchangeRates);
        }


    }
}
