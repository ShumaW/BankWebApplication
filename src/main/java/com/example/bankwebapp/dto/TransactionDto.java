package com.example.bankwebapp.dto;

import lombok.Data;

@Data
public class TransactionDto {

    String id;

    String debitAccountId;

    String creditAccountId;

    String type;

    String amount;

    String description;
}