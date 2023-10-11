package com.example.bankwebapp.service.impl;

import com.example.bankwebapp.entity.Account;
import com.example.bankwebapp.entity.enums.Currencies;
import com.example.bankwebapp.util.CreatorEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class CurrencyServiceImplTest {

    @InjectMocks
    CurrencyServiceImpl currencyService;

    @Test
    void getCurrencyRate() {
        //given
        Account creditAccount = CreatorEntity.getAccountTwo();
        Currencies transactionCurrency = Currencies.EUR;
        Currencies creditAccountCurrencyExpected = Currencies.USD;
        Currencies creditAccountCurrency = creditAccount.getCurrencyCode();
        BigDecimal expected = currencyService.getCurrencyRate(transactionCurrency,creditAccountCurrencyExpected);
        //when

        //then
        BigDecimal currencyRate = currencyService.getCurrencyRate(transactionCurrency, creditAccountCurrency);
        assertEquals(expected,currencyRate);
    }
}