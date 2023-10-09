package com.example.bankwebapp.service.impl;

import com.example.bankwebapp.entity.enums.Currencies;
import com.example.bankwebapp.service.interfases.CurrencyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.net.URL;
import java.util.Scanner;

@Service
@RequiredArgsConstructor
@Slf4j
public class CurrencyServiceImpl implements CurrencyService {

    private static final String EXCHANGE_RATE_API = "https://open.er-api.com/v6/latest/";

    @Override
    public BigDecimal getCurrencyRate(Currencies currenciesOfTransaction, Currencies creditAccountCurrency) {
        String currencyCodeToUpperCase = currenciesOfTransaction.toString().toUpperCase();
        String creditAccountCurrencyToUpperCase = creditAccountCurrency.toString().toUpperCase();
        return getRateOfCurrency(currencyCodeToUpperCase, creditAccountCurrencyToUpperCase);
    }

    private static BigDecimal getRateOfCurrency(String currencyTransactionCode, String creditAccountCurrency) {
        Scanner scanner;
        try {
            URL url = new URL(EXCHANGE_RATE_API + currencyTransactionCode);
            scanner = new Scanner((InputStream) url.getContent());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        StringBuilder result = new StringBuilder();
        while (scanner.hasNext()) {
            result.append(scanner.nextLine());
        }
        scanner.close();
        String resultString = result.toString();

        JSONObject jsonObject = new JSONObject(resultString);

        Object rates = jsonObject.get("rates");

        JSONObject finishRates = (JSONObject) rates;
        return new BigDecimal(String.valueOf(finishRates.get(creditAccountCurrency)));
    }
}

