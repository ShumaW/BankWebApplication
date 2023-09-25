package com.example.bankwebapp.service.impl;

import com.example.bankwebapp.dto.AccountDto;
import com.example.bankwebapp.entity.Account;
import com.example.bankwebapp.mapper.AccountMapper;
import com.example.bankwebapp.repository.AccountRepository;
import com.example.bankwebapp.service.interfases.AccountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.security.auth.login.AccountNotFoundException;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    private final AccountMapper accountMapper;

    @Override
    public AccountDto getAccountById(UUID id) {
        try {
            return accountMapper.mapToDto(accountRepository.findById(id).orElseThrow(() -> new AccountNotFoundException("Account was not found by this Id")));
        } catch (AccountNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }
}
