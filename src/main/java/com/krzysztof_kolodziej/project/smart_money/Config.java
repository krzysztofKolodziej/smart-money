package com.krzysztof_kolodziej.project.smart_money;

import com.krzysztof_kolodziej.project.smart_money.service.CurrencyService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class Config {
    @Value("${nbpApi.exchangeRates.url}")
    private String exchangeRatesUrl;

    @Bean
    public WebClient webClient() {
        return WebClient.builder().build();
    }

    @Bean
    public CurrencyService currencyService(WebClient webClient) {
        return new CurrencyService(webClient, exchangeRatesUrl);
    }

}
