package com.example.bankwebapp.entity.enums;

import lombok.Getter;

@Getter
public enum Permission {

    USER_READ("user:read"),
    USER_WRITE("user:write");

    private final String userPermission;

    Permission(String userPermission) {
        this.userPermission = userPermission;
    }


}
