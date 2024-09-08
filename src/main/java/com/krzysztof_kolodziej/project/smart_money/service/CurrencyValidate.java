package com.krzysztof_kolodziej.project.smart_money.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Component
public class CurrencyValidate {

    void validateUrl(String url) {
        Optional.ofNullable(url).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Url not found"));
    }
}
