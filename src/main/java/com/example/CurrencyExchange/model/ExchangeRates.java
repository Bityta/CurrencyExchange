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


    @ManyToOne
    @JoinColumn(name = "base_currency_id")
    private Currency baseCurrencyId;

    @ManyToOne
    @JoinColumn(name = "target_currency_id")
    private Currency targetCurrencyId;

    private double rate;

    public ExchangeRatesDTO convertToExchangeRatesDTO() {

        ExchangeRatesDTO exchangeRatesDTO = new ExchangeRatesDTO();
        exchangeRatesDTO.setBaseCurrencyId(this.getBaseCurrencyId().convertToCurrencyDTO());
        exchangeRatesDTO.setTargetCurrencyId(this.getTargetCurrencyId().convertToCurrencyDTO());
        exchangeRatesDTO.setRate(this.getRate());

        return exchangeRatesDTO;
    }
}
