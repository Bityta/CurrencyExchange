package com.example.CurrencyExchange.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Currency {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private int id;

    @NotNull
    @Size(min = 3, max =3 , message = "Не соответствуйщая длина code")
    //валидация на юникод
    private String code;

    @NotNull
    @Size(min =1 , max = 40, message = "Не соответствуйщая длина  fullname")
    private String fullName;

    @NotNull
    @Size(min = 1, max = 4, message = "Не соответствуйщая длина sign")
    private String sign;



}
