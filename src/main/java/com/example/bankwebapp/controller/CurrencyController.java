package com.example.bankwebapp.controller;

import com.example.bankwebapp.entity.enums.Currencies;
import com.example.bankwebapp.service.interfases.CurrencyService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping("/auth/currencies")
@RequiredArgsConstructor
@Tag(name = "Currency Controller API")
public class CurrencyController {

    private final CurrencyService currencyService;

    @Operation(summary = "Get currency rate from European Central Bank")
    @GetMapping("/{currency_code}")
    public BigDecimal getCurrencyRate(@PathVariable("currency_code") Currencies currenciesOfTransaction, Currencies creditAccountCurrency){
        return currencyService.getCurrencyRate(currenciesOfTransaction, creditAccountCurrency);
    }
}
