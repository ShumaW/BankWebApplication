package com.example.bankwebapp.entity.enums;

import lombok.Getter;

@Getter
public enum ManagerStatus {
    ACTIVE(0),
    PENDING(1),
    BUSY(2),
    REMOVED(3);
    private final int value;

    ManagerStatus(int value) {
        this.value = value;
    }
}
