package com.example.CurrencyExchange.service;

import com.example.CurrencyExchange.model.Currency;
import com.example.CurrencyExchange.repository.CurrenciesRepository;
import org.springframework.beans.factory.annotation.Autowired;
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


    public List<Currency> findAll(){
        return this.currenciesRepository.findAll();
    }

    public Currency findByCode(String code){
        return this.currenciesRepository.findByCode(code);
    }

    @Transactional
    public void save(Currency currency){
        this.currenciesRepository.save(currency);
    }
}
