package com.example.bankwebapp.controller;


import com.example.bankwebapp.dto.TransactionDto;
import com.example.bankwebapp.service.interfases.TransactionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth/transactions")
@Tag(name = "Transaction Controller API")
public class TransactionController {

    private final TransactionService transactionService;

    @Operation(summary = "Get all transaction where account is ...")
    @GetMapping("/all/{account_id}")
    public List<TransactionDto> findAllTransactionsWhereAccountIdIs(@PathVariable("account_id") UUID accountId){
        return transactionService.findAllTransactionsWhereAccountIdIs(accountId);
    }

    @PostMapping("/create")
    public ResponseEntity<TransactionDto> createNewTransaction(@RequestBody TransactionDto transactionDto){
        return new ResponseEntity<>(transactionService.createNewTransaction(transactionDto), HttpStatus.CREATED);
    }
}
