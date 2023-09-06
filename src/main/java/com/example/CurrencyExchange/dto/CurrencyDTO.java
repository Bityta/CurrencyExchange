package com.example.CurrencyExchange.dto;

import com.example.CurrencyExchange.model.Currency;
import lombok.*;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class CurrencyDTO {

    private String code;

    private String fullName;

    private String sign;

    public Currency convertToCurrency() {

        Currency currency = new Currency();
        currency.setCode(this.getCode());
        currency.setSign(this.getSign());
        currency.setFullName(this.getFullName());
        return currency;
    }
}
