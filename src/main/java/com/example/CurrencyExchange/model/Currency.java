package com.example.CurrencyExchange.model;


import com.example.CurrencyExchange.dto.CurrencyDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Currency {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    private String code;

    @NotNull
    private String fullName;

    @NotNull
    private String sign;


    @OneToMany(mappedBy = "targetCurrencyId", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<ExchangeRates> target;

    @OneToMany(mappedBy = "baseCurrencyId", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<ExchangeRates> base;


    public CurrencyDTO convertToCurrencyDTO() {

        CurrencyDTO currencyDTO = new CurrencyDTO();
        currencyDTO.setCode(this.getCode());
        currencyDTO.setSign(this.getSign());
        currencyDTO.setFullName(this.getFullName());

        return currencyDTO;
    }


}
