package com.example.bankwebapp.entity.enums;

import lombok.Getter;

@Getter
public enum Status {
    ACTIVE(0),
    PENDING(1),
    REMOVED(2),
    BLOCKED(3);

    private final int value;

    Status(int value) {
        this.value = value;
    }
}