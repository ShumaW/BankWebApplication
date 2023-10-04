package com.example.bankwebapp.service.interfases;

import com.example.bankwebapp.dto.TransactionDto;

import java.util.List;
import java.util.UUID;

public interface TransactionService {
    List<TransactionDto> findAllTransactionsWhereAccountIdIs(UUID accountId);
}
