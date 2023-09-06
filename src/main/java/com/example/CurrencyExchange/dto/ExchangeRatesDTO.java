package com.example.CurrencyExchange.dto;

import com.example.CurrencyExchange.model.ExchangeRates;
import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ExchangeRatesDTO {

    private int baseCurrencyId;

    private int targetCurrencyId;

    private double rate;

    public ExchangeRates convertToExchangeRates(){

        ExchangeRates exchangeRates = new ExchangeRates();
        exchangeRates.setBaseCurrencyId(this.getBaseCurrencyId());
        exchangeRates.setTargetCurrencyId(this.getTargetCurrencyId());
        exchangeRates.setRate(this.getRate());

        return exchangeRates;
    }

    public ExchangeRates convertToExchangeRates(int id){

        ExchangeRates exchangeRates = new ExchangeRates();
        exchangeRates.setBaseCurrencyId(this.getBaseCurrencyId());
        exchangeRates.setTargetCurrencyId(this.getTargetCurrencyId());
        exchangeRates.setRate(this.getRate());
        exchangeRates.setId(id);

        return exchangeRates;
    }

}
