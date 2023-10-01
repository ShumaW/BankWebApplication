package com.example.bankwebapp.dto;

import lombok.Value;

@Value
public class ProductDto {

    String id;

    String name;

    String status;

    String currencyCode;

    String interestRate;

    String limit;

    String managerId;
}