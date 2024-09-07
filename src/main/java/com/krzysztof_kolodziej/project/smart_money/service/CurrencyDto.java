package com.krzysztof_kolodziej.project.smart_money.service;

public record CurrencyDto(
        String currency,
        String code,
        float mid
) {
}
