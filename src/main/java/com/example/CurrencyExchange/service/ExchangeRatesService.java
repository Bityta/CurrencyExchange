package com.example.CurrencyExchange.service;


import com.example.CurrencyExchange.dto.ExchangeRatesDTO;
import com.example.CurrencyExchange.model.Exchange;
import com.example.CurrencyExchange.model.ExchangeRates;
import com.example.CurrencyExchange.model.Currency;
import com.example.CurrencyExchange.repository.ExchangeRatesRepository;
import com.example.CurrencyExchange.util.Exception.ExchangeRatesException.Exceptions.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class ExchangeRatesService {

    private final ExchangeRatesRepository exchangeRatesRepository;

    private final CurrenciesService currenciesService;

    public ExchangeRatesService(ExchangeRatesRepository exchangeRatesRepository, CurrenciesService currenciesService) {
        this.exchangeRatesRepository = exchangeRatesRepository;
        this.currenciesService = currenciesService;
    }


    public List<ExchangeRates> findAll() {
        return this.exchangeRatesRepository.findAll();
    }


    public ExchangeRatesDTO findByBaseCurrencyCodeAndTargetCurrencyCode(String code) {


        // Length base\target Currency Code = 3   =>   Length Currency Code = 6

        // Example : /USDEUR
        // baseCurrencyCode = USD
        // targetCurrencyCode = EUR

        if (code.length() != currenciesService.DEFAULT_LENGTH_CODE * 2) {
            throw new ExchangeRateMissingException();
        }

        String baseCurrencyCode = code.substring(3, 6);
        String targetCurrencyCode = code.substring(0, 3);

        ExchangeRates exchangeRates = this.findByBaseCurrencyIdAndTargetCurrencyId(
                this.currenciesService.findByCode(baseCurrencyCode),
                this.currenciesService.findByCode(targetCurrencyCode)
        );

        if (exchangeRates == null) {
            throw new ExchangeRatesNotFoundException();
        }

        return exchangeRates.convertToExchangeRatesDTO();


    }

    @Transactional
    public void save(ExchangeRatesDTO exchangeRatesDTO) {

        this.currenciesService.updateSave(exchangeRatesDTO.getBaseCurrencyId());
        this.currenciesService.updateSave(exchangeRatesDTO.getTargetCurrencyId());

        Currency baseCurrency = this.currenciesService.findByCode(
                exchangeRatesDTO.getBaseCurrencyId().getCode().toUpperCase());

        Currency targetCurrency = this.currenciesService.findByCode(
                exchangeRatesDTO.getTargetCurrencyId().getCode().toUpperCase());


        ExchangeRates exchangeRates = this.findByBaseCurrencyIdAndTargetCurrencyId(
                baseCurrency, targetCurrency);


        if (exchangeRates == null) {
            this.exchangeRatesRepository.save(exchangeRatesDTO.convertToExchangeRates(
                    baseCurrency, targetCurrency));
        } else {
            throw new ExchangeRatesExistsException();
        }


    }

    @Transactional
    public void update(ExchangeRatesDTO exchangeRatesDTO) {

        this.currenciesService.update(exchangeRatesDTO.getBaseCurrencyId());
        this.currenciesService.update(exchangeRatesDTO.getTargetCurrencyId());

        Currency baseCurrency = this.currenciesService.findByCode(
                exchangeRatesDTO.getBaseCurrencyId().getCode().toUpperCase());

        Currency targetCurrency = this.currenciesService.findByCode(
                exchangeRatesDTO.getTargetCurrencyId().getCode().toUpperCase());


        ExchangeRates exchangeRates = this.findByBaseCurrencyIdAndTargetCurrencyId(
                baseCurrency, targetCurrency);


        if (exchangeRates != null) {

            ExchangeRates newExchangeRates = exchangeRatesDTO.convertToExchangeRates(
                    baseCurrency, targetCurrency);

            newExchangeRates.setId(exchangeRates.getId());
            this.exchangeRatesRepository.save(newExchangeRates);

        } else {
            throw new ExchangeRatesNotFoundException();
        }


    }

    @Transactional
    public void save(ExchangeRatesDTO exchangeRatesDTO, int id) {

        ExchangeRates exchangeRates = exchangeRatesDTO.convertToExchangeRates(id);

        this.currenciesService.save(exchangeRates.getBaseCurrencyId().convertToCurrencyDTO());
        this.currenciesService.save(exchangeRates.getTargetCurrencyId().convertToCurrencyDTO());
        this.exchangeRatesRepository.save(exchangeRates);

    }

    public ExchangeRates findByBaseCurrencyIdAndTargetCurrencyId(Currency baseCurrencyId, Currency targetCurrencyId) {


        //Example: response - USDEUR
        //Find by Code = USD & EUR
        ExchangeRates targetBaseCurrency = this.exchangeRatesRepository.
                findByBaseCurrencyIdAndTargetCurrencyId(baseCurrencyId, targetCurrencyId);

        if (targetBaseCurrency != null) {
            return targetBaseCurrency;
        }

        //Find by Code = EUR & USD
        ExchangeRates baseTargetCurrency = this.exchangeRatesRepository.
                findByBaseCurrencyIdAndTargetCurrencyId(targetCurrencyId, baseCurrencyId);

        if (baseTargetCurrency != null) {
            return baseTargetCurrency;
        }

        return null;

    }

    public Exchange getExchange(String baseCurrencyCode, String targetCurrencyCode, double amount) {

        Currency baseCurrency = this.currenciesService.findByCode(baseCurrencyCode);
        Currency targetCurrency = this.currenciesService.findByCode(targetCurrencyCode);
        ExchangeRates exchangeRates = this.findByBaseCurrencyIdAndTargetCurrencyId(
                baseCurrency, targetCurrency);

        if (exchangeRates == null) {
            throw new ExchangeRatesNotFoundException();
        }

        double rate = exchangeRates.getRate();

        Exchange exchange = new Exchange(baseCurrency, targetCurrency, amount, rate);

        exchange.calculateConvertedAmount();

        return exchange;
    }
}
