package com.example.CurrencyExchange.model;

import com.example.CurrencyExchange.dto.ExchangeRatesDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ExchangeRates {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    private int baseCurrencyId;

    private int targetCurrencyId;

    private double rate;

    public ExchangeRatesDTO convertToExchangeRatesDTO(){

        ExchangeRatesDTO exchangeRatesDTO = new ExchangeRatesDTO();
        exchangeRatesDTO.setBaseCurrencyId(this.getBaseCurrencyId());
        exchangeRatesDTO.setTargetCurrencyId(this.getTargetCurrencyId());
        exchangeRatesDTO.setRate(this.getRate());

        return exchangeRatesDTO;
    }
}
