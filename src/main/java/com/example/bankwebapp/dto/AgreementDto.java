package com.example.bankwebapp.dto;

import lombok.Setter;
import lombok.Value;

@Setter
@Value
public class AgreementDto {

    String id;

    String accountId;

    String productId;

    String interestRate;

    String status;

    String sum;
}