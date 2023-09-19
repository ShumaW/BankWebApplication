package com.example.bankwebapp.entity.enums;

import lombok.Getter;

@Getter
public enum TransactionType {
    TRANSFER(1),
    PAYMENT(2),
    CASH(3),
    DEPOSIT(4);

    private final int value;

    TransactionType(int value) {
        this.value = value;
    }
}

