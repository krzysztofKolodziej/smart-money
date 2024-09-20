package com.krzysztof_kolodziej.project.smart_money.service;

import com.krzysztof_kolodziej.project.smart_money.model.Currency;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

@ExtendWith(MockitoExtension.class)
class CurrencyMapperTest {
    @InjectMocks
    CurrencyMapper currencyMapper;

    @Test
    void shouldMapCurrencyDtoToCurrency() {
        //given
        CurrencyDto[] currencyDtoTest = getCurrencyDto();

        //when
        List<Currency> mapCurrencyList = currencyMapper.getMapCurrencyList(currencyDtoTest);

        //then
        assertThat(mapCurrencyList.get(0).getCurrency()).isEqualTo("Dolar amerykanski");
        assertThat(mapCurrencyList.get(0).getCode()).isEqualTo("USD");
        assertThat(mapCurrencyList.get(0).getMid()).isEqualTo(4.1234F);
    }

    private static CurrencyDto[] getCurrencyDto() {
        return new CurrencyDto[]{
                new CurrencyDto(new CurrencyDto.Rates[]{
                        new CurrencyDto.Rates("Dolar amerykanski", "USD", 4.1234F),
                        new CurrencyDto.Rates("Funt szwajcarski", "CHF", 4.5333F),
                        new CurrencyDto.Rates("Euro", "EUR", 4.6000F)
                })
        };
    }


}