package com.example.bankwebapp.service.interfases;

import com.example.bankwebapp.entity.Account;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AccountService {

    Optional<Account> getAccountById(UUID id);

    List<Account> getAllAccounts();
}
