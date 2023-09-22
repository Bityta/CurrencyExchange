package com.example.CurrencyExchange.service;

import com.example.CurrencyExchange.dto.CurrencyDTO;
import com.example.CurrencyExchange.model.Currency;
import com.example.CurrencyExchange.repository.CurrenciesRepository;
import com.example.CurrencyExchange.util.Exception.CurrencyException.CurrencyExistsException;
import com.example.CurrencyExchange.util.Exception.CurrencyException.CurrencyMissingException;
import com.example.CurrencyExchange.util.Exception.CurrencyException.CurrencyNotFoundException;
import com.example.CurrencyExchange.util.Exception.CurrencyException.CurrencyRequiredFieldMissingException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class CurrenciesService {

    public final int DEFAULT_LENGTH_CODE = 3;
    private final CurrenciesRepository currenciesRepository;

    public CurrenciesService(CurrenciesRepository currenciesRepository) {
        this.currenciesRepository = currenciesRepository;
    }


    public List<Currency> findAll() {
        return this.currenciesRepository.findAll();
    }

    public Currency findByCode(String code) {

        if (code.length() != DEFAULT_LENGTH_CODE) {
            throw new CurrencyMissingException();
        }

        Optional<Currency> findCurrency = this.currenciesRepository.findByCode(code.toUpperCase());

        return findCurrency.orElseThrow(CurrencyNotFoundException::new);
    }

    @Transactional
    public void save(CurrencyDTO currencyDTO) {

        if (currencyDTO.getCode().length() != DEFAULT_LENGTH_CODE) {
            throw new CurrencyMissingException();
        }

        if (!this.isFieldFilled(currencyDTO)) {
            throw new CurrencyRequiredFieldMissingException();
        }

        Optional<Currency> currency = this.currenciesRepository.findByCode(currencyDTO.getCode().toUpperCase());


        if (currency.isEmpty()) {
            currencyDTO.setCode(currencyDTO.getCode().toUpperCase());
            this.currenciesRepository.save(currencyDTO.convertToCurrency());

        } else {
            throw new CurrencyExistsException();
        }
    }

    @Transactional
    public void update(CurrencyDTO currencyDTO) {

        if (currencyDTO.getCode().length() != DEFAULT_LENGTH_CODE) {
            throw new CurrencyMissingException();
        }

        if (!this.isFieldFilled(currencyDTO)) {
            throw new CurrencyRequiredFieldMissingException();
        }

        Optional<Currency> currency = this.currenciesRepository.findByCode(currencyDTO.getCode().toUpperCase());


        if (currency.isEmpty()) {
            throw new CurrencyNotFoundException();

        } else {
            Currency newCurrency = currencyDTO.convertToCurrency();
            newCurrency.setId(currency.get().getId());
            newCurrency.setCode(newCurrency.getCode().toUpperCase());
            this.currenciesRepository.save(newCurrency);

        }
    }
    @Transactional
    public void updateSave(CurrencyDTO currencyDTO) {

        if (currencyDTO.getCode().length() != DEFAULT_LENGTH_CODE) {
            throw new CurrencyMissingException();
        }

        if (!this.isFieldFilled(currencyDTO)) {
            throw new CurrencyRequiredFieldMissingException();
        }

        Optional<Currency> currency = this.currenciesRepository.findByCode(currencyDTO.getCode().toUpperCase());


        if (currency.isEmpty()) {
            currencyDTO.setCode(currencyDTO.getCode().toUpperCase());
            this.currenciesRepository.save(currencyDTO.convertToCurrency());

        } else {
            Currency newCurrency = currencyDTO.convertToCurrency();
            newCurrency.setId(currency.get().getId());
            newCurrency.setCode(newCurrency.getCode().toUpperCase());
            this.currenciesRepository.save(newCurrency);

        }
    }

    private boolean isFieldFilled(CurrencyDTO currencyDTO) {

        return currencyDTO.getCode() != null && currencyDTO.getSign() != null &&
                currencyDTO.getFullName() != null;
    }
}
