package com.example.CurrencyExchange.service;


import com.example.CurrencyExchange.model.ExchangeRates;
import com.example.CurrencyExchange.repository.CurrenciesRepository;
import com.example.CurrencyExchange.repository.ExchangeRatesRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class ExchangeRatesService {

    private final ExchangeRatesRepository exchangeRatesRepository;
    private final CurrenciesRepository currenciesRepository;

    public ExchangeRatesService(ExchangeRatesRepository exchangeRatesRepository, CurrenciesRepository currenciesRepository) {
        this.exchangeRatesRepository = exchangeRatesRepository;
        this.currenciesRepository = currenciesRepository;
    }


    public List<ExchangeRates> findAll() {
        return this.exchangeRatesRepository.findAll();
    }

    public ExchangeRates findByBaseCurrencyIdAndTargetCurrencyId(int b, int t){
        return this.exchangeRatesRepository.findByBaseCurrencyIdAndTargetCurrencyId(b,t);
    }

    public ExchangeRates findByBaseCurrencyCodeAndTargetCurrencyCode(String b, String t){
        return this.findByBaseCurrencyIdAndTargetCurrencyId(
                this.currenciesRepository.findByCode(b).getId(),
                this.currenciesRepository.findByCode(t).getId()
        );


    }

    @Transactional
    public void save(ExchangeRates exchangeRates){
        this.exchangeRatesRepository.save(exchangeRates);
    }
}
