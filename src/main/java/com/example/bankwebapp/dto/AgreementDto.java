package com.example.bankwebapp.dto;

import lombok.Data;

@Data
public class AgreementDto {

    String id;

    AccountDto accountDto;

    ProductDto productDto;

    String interestRate;

    String status;

    String sum;
}