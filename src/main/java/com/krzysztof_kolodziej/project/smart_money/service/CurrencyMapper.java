package com.krzysztof_kolodziej.project.smart_money.service;

import com.krzysztof_kolodziej.project.smart_money.model.Currency;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class CurrencyMapper {

    List<Currency> getMapCurrencyList(CurrencyDto[] currencyDto) {
        return Arrays.stream(currencyDto)
                .flatMap(currencyRates -> Arrays.stream(currencyRates.rates())
                        .map(rate -> Currency.builder()
                                .currency(rate.currency())
                                .code(rate.code())
                                .mid(rate.mid())
                                .build()))
                .toList();
    }
}
