package com.example.bankwebapp.dto;

import lombok.Data;

@Data
public class AccountDto {

    String id;

    String clientId;

    String name;

    String type;

    String status;

    Double balance;

    String currencyCode;
}
