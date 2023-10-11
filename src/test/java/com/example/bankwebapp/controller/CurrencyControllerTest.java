package com.example.bankwebapp.controller;

import com.example.bankwebapp.entity.enums.Currencies;
import com.example.bankwebapp.service.interfases.CurrencyService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@WebMvcTest({CurrencyController.class})
class CurrencyControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    CurrencyController currencyController;

    @MockBean
    private CurrencyService currencyService;

    @Test
    void getCurrencyRateTest() throws Exception {
        // given
        Currencies currencyOfTransaction = Currencies.USD;
        Currencies currencyOfCreditAccount = Currencies.EUR;
        BigDecimal rate = new BigDecimal("1.057057");

        //when
        when(currencyService.getCurrencyRate(currencyOfTransaction,currencyOfCreditAccount)).thenReturn(rate);
        //then
        mockMvc.perform(get("/auth/currencies/" + currencyOfTransaction))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();
    }
}