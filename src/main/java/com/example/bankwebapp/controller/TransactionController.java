package com.example.bankwebapp.controller;


import com.example.bankwebapp.dto.TransactionDto;
import com.example.bankwebapp.service.interfases.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth/transactions")
public class TransactionController {

    private final TransactionService transactionService;

    @GetMapping("/all/{account_id}")
    public List<TransactionDto> findAllTransactionsWhereAccountIdIs(@PathVariable("account_id") UUID accountId){
        return transactionService.findAllTransactionsWhereAccountIdIs(accountId);
    }
}
