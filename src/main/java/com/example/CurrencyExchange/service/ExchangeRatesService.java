package com.example.CurrencyExchange.service;


import com.example.CurrencyExchange.model.ExchangeRates;
import com.example.CurrencyExchange.repository.ExchangeRatesRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class ExchangeRatesService {

    private final ExchangeRatesRepository exchangeRatesRepository;

    public ExchangeRatesService(ExchangeRatesRepository exchangeRatesRepository) {
        this.exchangeRatesRepository = exchangeRatesRepository;
    }

    public List<ExchangeRates> findAll() {
        return this.exchangeRatesRepository.findAll();
    }

    public Optional<ExchangeRates> findById(int id) {
        return Optional.ofNullable(this.exchangeRatesRepository.findById(id).orElse(null));
    }

    @Transactional
    public void save(ExchangeRates exchangeRates){
        this.exchangeRatesRepository.save(exchangeRates);
    }
}
