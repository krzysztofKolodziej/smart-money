package com.krzysztof_kolodziej.project.smart_money.controller;

import com.krzysztof_kolodziej.project.smart_money.model.Currency;
import com.krzysztof_kolodziej.project.smart_money.service.CurrencyService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class CurrencyController {
    private CurrencyService currencyService;

    @GetMapping("/currency/{date}")
    public ResponseEntity<List<Currency>> getCurrency(@PathVariable String date) {
        List<Currency> currency = currencyService.getCurrency(date);
        return ResponseEntity.status(HttpStatus.OK).body(currency);
    }

}
