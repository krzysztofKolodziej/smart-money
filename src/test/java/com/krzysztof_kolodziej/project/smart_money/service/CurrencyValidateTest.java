package com.krzysztof_kolodziej.project.smart_money.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;

import static org.assertj.core.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class CurrencyValidateTest {
    @InjectMocks
    CurrencyValidate currencyValidate;

    @Test
    void shouldThrowExceptionWhenUrlIsNull() {
        assertThatExceptionOfType(ResponseStatusException.class)
                .isThrownBy(() -> currencyValidate.validateUrl(null))
                .withMessage("404 NOT_FOUND \"Url not found\"");
    }

    @Test
    void shouldReturnUrlWhenUrlIsNotNull() {
        //given
        String url = "/api";

        //when
        String string = currencyValidate.validateUrl(url);

        //then
        assertThat(string).isEqualTo("/api");
    }

    @Test
    void shouldThrowExceptionWhenDateIsNull() {
        assertThatExceptionOfType(ResponseStatusException.class)
                .isThrownBy(() -> currencyValidate.validateDate(null))
                .withMessage("404 NOT_FOUND \"Invalid date type\"");
    }

    @Test
    void shouldThrowExceptionWhenDateIsInvalid() {
        assertThatExceptionOfType(ResponseStatusException.class)
                .isThrownBy(() -> currencyValidate.validateDate("22.07.2022"))
                .withMessage("404 NOT_FOUND \"Invalid date type\"");
    }

    @Test
    void shouldReturnDateWhenDataIsCorrect() {
        //given
        String date = "2022-07-22";

        //when
        String string = currencyValidate.validateDate(date);

        //then
        assertThat(string).isEqualTo("2022-07-22");
    }

}
