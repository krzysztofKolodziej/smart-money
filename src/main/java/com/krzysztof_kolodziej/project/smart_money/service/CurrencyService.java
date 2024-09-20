package com.krzysztof_kolodziej.project.smart_money.service;

import com.krzysztof_kolodziej.project.smart_money.model.Currency;
import lombok.AllArgsConstructor;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;

import java.util.List;

@AllArgsConstructor
public class CurrencyService {
    private String exchangeRatesUrl;
    private WebClient webClient;
    private CurrencyMapper currencyMapper;
    private CurrencyValidate currencyValidate;

    public List<Currency> getCurrency(String date) {
        String validateUrl = currencyValidate.validateUrl(exchangeRatesUrl);
        String validateDate = currencyValidate.validateDate(date);

        CurrencyDto[] currencyDto = webClient
                .get()
                .uri(validateUrl, validateDate)
                .retrieve()
                .bodyToMono(CurrencyDto[].class)
                .onErrorResume(WebClientResponseException.NotFound.class, Mono::error)
                .block();
        return currencyMapper.getMapCurrencyList(currencyDto);
    }


}
