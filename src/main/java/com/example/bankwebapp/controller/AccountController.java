package com.example.bankwebapp.controller;

import com.example.bankwebapp.dto.AccountDto;
import com.example.bankwebapp.entity.Account;
import com.example.bankwebapp.entity.enums.Status;
import com.example.bankwebapp.service.interfases.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/auth/accounts")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @GetMapping("/{id}")
    public AccountDto getAccountById(@PathVariable("id") UUID id) {
        return accountService.getAccountById(id);
    }

    @GetMapping("/")
    public List<Account> getAllAccounts() {
        return accountService.getAllAccounts();
    }

    @PutMapping("/update")
    public Account updateAccountById(@RequestBody AccountDto accountDto) {
        return accountService.update(accountDto);
    }

    @GetMapping("/status/{status}")
    public List<AccountDto> getAllAccountsWhereStatusIs(@PathVariable("status") Status status){
        return accountService.getAllAccountsWhereStatusIs(status);
    }
}