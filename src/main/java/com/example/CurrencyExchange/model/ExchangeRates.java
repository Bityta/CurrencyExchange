package com.example.CurrencyExchange.model;

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


    private Currency baseCurrencyId;

    private Currency targetCurrencyId;

    private double rate;
}
