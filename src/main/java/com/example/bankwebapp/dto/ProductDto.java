package com.example.bankwebapp.dto;

import lombok.Data;

@Data
public class ProductDto {

    String id;

    String name;

    String status;

    String currencyCode;

    String interestRate;

    String limit;

    String managerId;
}