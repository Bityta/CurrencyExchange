package com.example.CurrencyExchange.model;


import com.example.CurrencyExchange.dto.CurrencyDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
    @Size(min = 3, max =3 , message = "Не соответствуйщая длина code")
    private String code;

    @NotNull
    @Size(min =1 , max = 40, message = "Не соответствуйщая длина fullName")
    private String fullName;

    @NotNull
    @Size(min = 1, max = 4, message = "Не соответствуйщая длина sign")
    private String sign;


    @OneToMany(mappedBy = "targetCurrencyId", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<ExchangeRates> target;

    @OneToMany(mappedBy = "baseCurrencyId", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<ExchangeRates> base;


    public CurrencyDTO convertToCurrencyDTO(){

        CurrencyDTO currencyDTO = new CurrencyDTO();
        currencyDTO.setCode(this.getCode());
        currencyDTO.setSign(this.getSign());
        currencyDTO.setFullName(this.getFullName());

        return currencyDTO;
    }



}
