package com.krzysztof_kolodziej.project.smart_money.service;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Optional;

@Component
public class CurrencyValidate {

    String validateUrl(String url) {
        return Optional.ofNullable(url).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Url not found"));
    }

    String validateDate(String date) {
        LocalDate validDate;
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        try {
            validDate = LocalDate.parse(date, dateTimeFormatter);
        } catch (DateTimeParseException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Invalid date type");
        }
        return validDate.toString();
    }

}
