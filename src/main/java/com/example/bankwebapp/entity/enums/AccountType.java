package com.example.bankwebapp.entity.enums;

public enum AccountType {
    DEPOSIT(1),
    CREDIT(2),
    CURRENT(3);

    private final int value;

    AccountType(int value) {
        this.value = value;
    }
}
