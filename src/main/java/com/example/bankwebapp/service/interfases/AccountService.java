package com.example.bankwebapp.service.interfases;

import com.example.bankwebapp.dto.AccountDto;
import com.example.bankwebapp.entity.Account;
import com.example.bankwebapp.entity.enums.Status;

import java.util.List;
import java.util.UUID;

public interface AccountService {

    AccountDto getAccountById(UUID id);

    List<AccountDto> getAllAccounts();

    Account update(AccountDto accountDto);

    List<AccountDto> getAllAccountsWhereStatusIs(Status status);
}