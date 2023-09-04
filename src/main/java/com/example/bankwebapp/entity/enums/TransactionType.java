package com.example.bankwebapp.entity.enums;

import lombok.Getter;

@Getter
public enum TransactionType {
    NEW(1),
    PENDING(2),
    APPROVED(3),
    COMPLETED(4);

    private final int value;

    TransactionType(int value) {
        this.value = value;
    }
}
