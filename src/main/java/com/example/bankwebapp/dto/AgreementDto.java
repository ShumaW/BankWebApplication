package com.example.bankwebapp.dto;

import lombok.Data;

@Data
public class AgreementDto {

    String id;

    AccountDto account;

    ProductDto product;

    String interestRate;

    String status;

    String sum;
}
