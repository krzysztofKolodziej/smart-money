package com.krzysztof_kolodziej.project.smart_money.service;

import com.krzysztof_kolodziej.project.smart_money.model.Currency;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;

@AllArgsConstructor
public class CurrencyService {
    private WebClient webClient;
    private String exchangeRatesUrl;

    public List<Currency> getCurrency(String date) {
        CurrencyDto[] currencyDto = webClient.get().uri(exchangeRatesUrl, date)
                .retrieve().bodyToMono(CurrencyDto[].class).block();
       return Arrays.stream(currencyDto).map(currencyDto1 ->
                Currency.builder()
                        .currency(currencyDto1.rates()[0].currency())
                        .code(currencyDto1.rates()[0].code())
                        .mid(currencyDto1.rates()[0].mid())
                        .build())
                .toList();
    }


}
