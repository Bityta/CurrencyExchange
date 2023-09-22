package com.example.CurrencyExchange.dto;

import com.example.CurrencyExchange.model.Currency;
import com.example.CurrencyExchange.model.ExchangeRates;
import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@ToString
@NoArgsConstructor
public class ExchangeRatesDTO {

    private CurrencyDTO baseCurrencyId;

    private CurrencyDTO targetCurrencyId;

    private double rate;


    public ExchangeRates convertToExchangeRates() {
        ExchangeRates exchangeRates = new ExchangeRates();
        exchangeRates.setBaseCurrencyId(this.getBaseCurrencyId().convertToCurrency());
        exchangeRates.setTargetCurrencyId(this.getTargetCurrencyId().convertToCurrency());
        exchangeRates.setRate(this.getRate());

        return exchangeRates;
    }

    public ExchangeRates convertToExchangeRates(int id) {

        ExchangeRates exchangeRates = new ExchangeRates();
        exchangeRates.setBaseCurrencyId(this.getBaseCurrencyId().convertToCurrency());
        exchangeRates.setTargetCurrencyId(this.getTargetCurrencyId().convertToCurrency());
        exchangeRates.setRate(this.getRate());
        exchangeRates.setId(id);

        return exchangeRates;
    }

    public ExchangeRates convertToExchangeRates(Currency baseCurrency, Currency targetCurrency) {

        ExchangeRates exchangeRates = new ExchangeRates();
        exchangeRates.setBaseCurrencyId(baseCurrency);
        exchangeRates.setTargetCurrencyId(targetCurrency);
        exchangeRates.setRate(this.getRate());

        return exchangeRates;
    }

}
