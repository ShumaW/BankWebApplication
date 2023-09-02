package com.example.bankwebapp.entity;

import com.example.bankwebapp.entity.enums.AgreementStatus;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class Agreement {
    private int id;
    private int accountId;
    private int productId;
    private BigDecimal interestRate;
    private AgreementStatus status;
    private BigDecimal sum;
    private Timestamp createdAt;
    private Timestamp updatedAt;
}
