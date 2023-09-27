package com.example.bankwebapp.entity.enums;

import lombok.Getter;

@Getter
public enum Currencies {
    UAH(1),
    EUR(2),
    USD(3),
    GBR(4),
    PLN(5);

    private final int value;

    Currencies(int value) {
        this.value = value;
    }
}