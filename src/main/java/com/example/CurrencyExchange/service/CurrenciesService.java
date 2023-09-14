package com.example.CurrencyExchange.service;

import com.example.CurrencyExchange.model.Currency;
import com.example.CurrencyExchange.repository.CurrenciesRepository;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)

public class CurrenciesService {

    private final CurrenciesRepository currenciesRepository;

    public CurrenciesService(CurrenciesRepository currenciesRepository) {
        this.currenciesRepository = currenciesRepository;
    }


    public List<Currency> findAll() {
        return this.currenciesRepository.findAll();
    }

    public Currency findByCode(String code) {
        return this.currenciesRepository.findByCode(code);
    }

    @Transactional
    public void save(Currency currency) {

        Currency currency1 = this.findByCode(currency.getCode());

        if (currency1 == null) {
            this.currenciesRepository.save(currency);
        }
        else{
            currency.setId(currency1.getId());
            this.currenciesRepository.save(currency);
        }
    }
}
