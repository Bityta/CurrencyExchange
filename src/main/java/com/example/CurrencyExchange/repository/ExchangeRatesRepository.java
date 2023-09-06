package com.example.CurrencyExchange.repository;

import com.example.CurrencyExchange.model.ExchangeRates;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExchangeRatesRepository extends JpaRepository<ExchangeRates, Integer> {

    ExchangeRates findByBaseCurrencyIdAndTargetCurrencyId(int b,int t);
}
