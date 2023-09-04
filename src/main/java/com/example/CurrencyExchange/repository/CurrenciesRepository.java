package com.example.CurrencyExchange.repository;

import com.example.CurrencyExchange.model.Currency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CurrenciesRepository extends JpaRepository<Currency, Integer> {


    Currency findByCode(String code);

}
