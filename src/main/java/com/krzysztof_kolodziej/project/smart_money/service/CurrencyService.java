package com.krzysztof_kolodziej.project.smart_money.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@AllArgsConstructor
public class CurrencyService {
    private WebClient webClient;

    

}
