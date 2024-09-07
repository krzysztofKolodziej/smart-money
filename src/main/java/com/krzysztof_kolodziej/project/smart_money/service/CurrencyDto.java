package com.krzysztof_kolodziej.project.smart_money.service;

public record CurrencyDto(Rates[] rates) {
    public record Rates(
            String currency,
            String code,
            float mid
    ) {
    }
}
