package com.example.bankwebapp.controller;

import com.example.bankwebapp.dto.TransactionDto;
import com.example.bankwebapp.service.interfaсes.TransactionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
    @PreAuthorize("hasAuthority('user:read')")
    public List<TransactionDto> findAllTransactionsWhereAccountIdIs(@PathVariable("account_id") UUID accountId){
        return transactionService.findAllTransactionsWhereAccountIdIs(accountId);
    }

    @Operation(summary = "Create new transaction")
    @PostMapping("/create")
    @PreAuthorize("hasAuthority('user:read')")
    public ResponseEntity<Void> createNewTransaction(@RequestBody TransactionDto transactionDto){
        transactionService.createNewTransaction(transactionDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
