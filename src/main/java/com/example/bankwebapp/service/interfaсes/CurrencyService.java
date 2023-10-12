package com.example.bankwebapp.service.interfaсes;

import com.example.bankwebapp.entity.enums.Currencies;

import java.math.BigDecimal;

public interface CurrencyService {
    BigDecimal getCurrencyRate(Currencies currenciesOfTransaction, Currencies creditAccountCurrency);
}
