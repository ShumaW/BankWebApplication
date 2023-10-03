package com.example.bankwebapp.dto;

import lombok.*;

@Setter
@Value
@Builder
@Data
@AllArgsConstructor
public class AccountDto {

    String id;

    String clientId;

    String name;

    String type;

    String status;

    String balance;

    String currencyCode;

}