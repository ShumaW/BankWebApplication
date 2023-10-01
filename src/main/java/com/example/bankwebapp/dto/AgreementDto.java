package com.example.bankwebapp.dto;

import lombok.Setter;
import lombok.Value;

@Setter
@Value
public class AgreementDto {

    String id;

    AccountDto account;

    ProductDto product;

    String interestRate;

    String status;

    String sum;
}