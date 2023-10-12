package com.example.bankwebapp.service.interfaсes;

import com.example.bankwebapp.dto.AccountDto;
import com.example.bankwebapp.entity.enums.Status;

import java.util.List;
import java.util.UUID;

public interface AccountService {

    AccountDto getAccountById(UUID id);

    List<AccountDto> getAllAccounts();

    AccountDto update(AccountDto accountDto);

    List<AccountDto> getAllAccountsWhereStatusIs(Status status);

    AccountDto createAccount(AccountDto accountDto);

    AccountDto updateStatusInAccountByIdToRemoved(UUID id);
}