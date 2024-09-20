package com.krzysztof_kolodziej.project.smart_money.service;

import com.krzysztof_kolodziej.project.smart_money.model.Currency;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.reactive.function.client.WebClient;

import java.io.IOException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CurrencyServiceTest {
    private MockWebServer mockWebServer;
    private CurrencyMapper currencyMapper;
    private CurrencyValidate currencyValidate;
    private CurrencyService currencyService;


    @BeforeEach
    void setUp() throws IOException {
        mockWebServer = new MockWebServer();
        mockWebServer.start();

        WebClient webClient = WebClient.builder()
                .baseUrl(mockWebServer.url("/").toString())
                .build();

        currencyMapper = mock(CurrencyMapper.class);
        currencyValidate = mock(CurrencyValidate.class);
        currencyService = new CurrencyService(mockWebServer.url("/api").toString(), webClient, currencyMapper, currencyValidate);
    }

    @AfterEach
    void tearDown() throws IOException {
        mockWebServer.shutdown();
    }

    @Test
    void testGetCurrencySuccess() throws InterruptedException {
        //given
        String date = "2024-09-10";

        mockWebServer.enqueue(new MockResponse()
                .setBody("[{\"currency\": \"Dolar amarykanski\",\"code\": \"USD\", \"mid\": 4.20}]")
                .addHeader("Content-Type", "application/json"));

        when(currencyValidate.validateUrl(anyString())).thenReturn("/api");
        when(currencyValidate.validateDate(date)).thenReturn(date);
        when(currencyMapper.getMapCurrencyList(any(CurrencyDto[].class))).thenReturn(getCurrency());

        //when
        List<Currency> result = currencyService.getCurrency(date);
        Currency currency = result.get(0);

        //then
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Dolar amerykanski", currency.getCurrency());
        assertEquals("USD", currency.getCode());
        assertEquals(4.23F, currency.getMid());

        verify(currencyValidate).validateUrl(anyString());
        verify(currencyValidate).validateDate(date);
        verify(currencyMapper).getMapCurrencyList(any(CurrencyDto[].class));
    }



    private static List<Currency> getCurrency() {
        return List.of( Currency.builder()
                .currency("Dolar amerykanski")
                .code("USD")
                .mid(4.23F)
                .build());
    }

    private static List<CurrencyDto> getCurrencyDto() {
        CurrencyDto[] currencyDtoArray = new CurrencyDto[]{
                new CurrencyDto(new CurrencyDto.Rates[]{
                        new CurrencyDto.Rates("Dolar amerykanski", "USD", 4.1234F),
                        new CurrencyDto.Rates("Funt szwajcarski", "CHF", 4.5333F),
                        new CurrencyDto.Rates("Euro", "EUR", 4.6000F)
                })
        };
        return Arrays.stream(currencyDtoArray).toList();
    }


}