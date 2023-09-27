package com.example.bankwebapp.entity.enums;

import lombok.Getter;

@Getter
public enum AccountType {
    DEPOSIT(1),
    CREDIT(2),
    CURRENT(3);

    private final int value;

    AccountType(int value) {
        this.value = value;
    }
}