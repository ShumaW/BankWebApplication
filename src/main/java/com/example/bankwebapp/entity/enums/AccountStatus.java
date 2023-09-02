package com.example.bankwebapp.entity.enums;

public enum AccountStatus {
    ACTIVE(0),
    PENDING(1),
    REMOVED(2),
    BLOCKED(3);
    private final int value;

    AccountStatus(int value) {
        this.value = value;
    }
}
