package com.example.bankwebapp.controller;

import com.example.bankwebapp.dto.AccountDto;
import com.example.bankwebapp.entity.enums.Status;
import com.example.bankwebapp.service.interfaсes.AccountService;
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
@RequestMapping("/auth/accounts")
@RequiredArgsConstructor
@Tag(name = "Account Controller API")
public class AccountController {

    private final AccountService accountService;

    @Operation(summary = "Get account by id")
    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('user:read')")
    public AccountDto getAccountById(@PathVariable("id") UUID id) {
        return accountService.getAccountById(id);
    }

    @Operation(summary = "Get all accounts")
    @GetMapping("/")
    @PreAuthorize("hasAuthority('user:write')")
    public List<AccountDto> getAllAccounts() {
        return accountService.getAllAccounts();
    }

    @Operation(summary = "Update account")
    @PutMapping("/update")
    @PreAuthorize("hasAuthority('user:write')")
    public ResponseEntity<AccountDto> updateAccountById(@RequestBody AccountDto accountDto) {
        return new ResponseEntity<>(accountService.update(accountDto),HttpStatus.OK);
    }

    @Operation(summary = "Get all accounts where status is...")
    @GetMapping("/status/{status}")
    @PreAuthorize("hasAuthority('user:read')")
    public List<AccountDto> getAllAccountsWhereStatusIs(@PathVariable("status") Status status){
        return accountService.getAllAccountsWhereStatusIs(status);
    }

    @Operation(summary = "Create account")
    @PostMapping("/create")
    @PreAuthorize("hasAuthority('user:write')")
    public ResponseEntity<AccountDto> createAccount(@RequestBody AccountDto accountDto) {
        return new ResponseEntity<>(accountService.createAccount(accountDto), HttpStatus.CREATED);
    }

    @Operation(summary = "Deleting an account (transfer to REMOVE status)")
    @PutMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('user:write')")
    public ResponseEntity<AccountDto> deleteAccountById (@PathVariable("id") UUID id){
        return new ResponseEntity<>(accountService.updateStatusInAccountByIdToRemoved(id),HttpStatus.OK);
    }
}