package com.krzysztof_kolodziej.project.smart_money.service;

import com.krzysztof_kolodziej.project.smart_money.model.Currency;
import lombok.AllArgsConstructor;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@AllArgsConstructor
public class CurrencyService {
    private String exchangeRatesUrl;
    private WebClient webClient;
    private CurrencyMapper currencyMapper;

    public List<Currency> getCurrency(String date) {
        CurrencyDto[] currencyDto = webClient.get()
                .uri(exchangeRatesUrl, date)
                .retrieve()
                .bodyToMono(CurrencyDto[].class)
                .block();
        return currencyMapper.getMapCurrencyList(currencyDto);
    }


}
