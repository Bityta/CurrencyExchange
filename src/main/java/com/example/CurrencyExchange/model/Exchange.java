package com.example.CurrencyExchange.model;

import com.example.CurrencyExchange.dto.CurrencyDTO;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Exchange {

    private CurrencyDTO baseCurrencyId;

    private CurrencyDTO targetCurrencyId;

    private double rate;

    private double amount;

    public Exchange(Currency baseCurrencyId, Currency targetCurrencyId, double rate, double amount) {
        this.baseCurrencyId = baseCurrencyId.convertToCurrencyDTO();
        this.targetCurrencyId = targetCurrencyId.convertToCurrencyDTO();
        this.rate = rate;
        this.amount = amount;
    }

    private double convertedAmount;


    public void calculateConvertedAmount() {
        this.convertedAmount = this.rate * this.amount;
    }


}
