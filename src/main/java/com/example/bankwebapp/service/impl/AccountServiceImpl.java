package com.example.bankwebapp.service.impl;

import com.example.bankwebapp.dto.AccountDto;
import com.example.bankwebapp.entity.Account;
import com.example.bankwebapp.entity.enums.Status;
import com.example.bankwebapp.exceptions.NotFoundAccountException;
import com.example.bankwebapp.mapper.AccountMapper;
import com.example.bankwebapp.repository.AccountRepository;
import com.example.bankwebapp.service.interfases.AccountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Timestamp;
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
        return accountMapper.mapToDto(accountRepository.findById(id)
                .orElseThrow(() -> new NotFoundAccountException("Account not found with id " + id)));
    }

    @Override
    public List<AccountDto> getAllAccounts() {
        return accountMapper.mapToListDto(accountRepository.findAll());
    }

    @Override
    public Account update(AccountDto accountDto) {
        Account account = accountRepository.findById(UUID.fromString(accountDto.getId()))
                .orElseThrow(() -> new NotFoundAccountException("Account not found with id " + accountDto.getId()));
        account.setName(accountDto.getName());
        account.setBalance(new BigDecimal(accountDto.getBalance()));
        account.setStatus(Status.valueOf(accountDto.getStatus()));
        account.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        accountRepository.save(account);
        return account;
    }

    @Override
    public List<AccountDto> getAllAccountsWhereStatusIs(Status status) {
        return accountMapper.mapToListDto(accountRepository.findAllAccountsWhereStatusIs(status));
    }
}