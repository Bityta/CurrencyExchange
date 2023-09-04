package com.example.CurrencyExchange.service;

import com.example.CurrencyExchange.model.Currencies;
import com.example.CurrencyExchange.repository.CurrenciesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class CurrenciesService {

    private final CurrenciesRepository currenciesRepository;

    @Autowired
    public CurrenciesService(CurrenciesRepository currenciesRepository) {
        this.currenciesRepository = currenciesRepository;
    }


    public List<Currencies> findAll(){
        return this.currenciesRepository.findAll();
    }
}
