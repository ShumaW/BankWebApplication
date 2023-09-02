package com.example.bankwebapp.entity;

import com.example.bankwebapp.entity.enums.TransactionType;

import java.util.UUID;

public class Transaction {
    private UUID id;
    private Account debitAccountId;
    private Account creditAccountId;
    private TransactionType type;
}
