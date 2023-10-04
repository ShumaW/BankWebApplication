package com.example.bankwebapp.dto;

import lombok.Value;

@Value
public class TransactionDto {

    String id;

    String debitAccountId;

    String creditAccountId;

    String type;

    String amount;

    String currencyCode;

    String description;



}