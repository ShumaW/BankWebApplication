package com.example.bankwebapp.service.impl;

import com.example.bankwebapp.dto.AccountDto;
import com.example.bankwebapp.entity.Account;
import com.example.bankwebapp.entity.Client;
import com.example.bankwebapp.entity.enums.Status;
import com.example.bankwebapp.exceptions.NotFoundAccountException;
import com.example.bankwebapp.exceptions.NotFoundClientException;
import com.example.bankwebapp.mapper.AccountMapper;
import com.example.bankwebapp.repository.AccountRepository;
import com.example.bankwebapp.repository.ClientRepository;
import com.example.bankwebapp.service.interfases.AccountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    private final ClientRepository clientRepository;

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
    @Transactional
    public AccountDto update(AccountDto accountDto) {
        Account account = accountRepository.findById(UUID.fromString(accountDto.getId()))
                .orElseThrow(() -> new NotFoundAccountException("Account not found with id " + accountDto.getId()));
        account.setName(accountDto.getName());
        account.setBalance(new BigDecimal(accountDto.getBalance()));
        account.setStatus(Status.valueOf(accountDto.getStatus()));
        account.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        accountRepository.save(account);
        return accountMapper.mapToDto(account);
    }

    @Override
    public List<AccountDto> getAllAccountsWhereStatusIs(Status status) {
        return accountMapper.mapToListDto(accountRepository.findAllAccountsWhereStatusIs(status));
    }

    @Override
    public AccountDto createAccount(AccountDto accountDto) {
        Client client = clientRepository.findById(UUID.fromString(accountDto.getClientId()))
                .orElseThrow(() -> new NotFoundClientException("Client not found with id " + accountDto.getClientId()));
        Account account = accountMapper.mapToEntity(accountDto);
        account.setClient(client);
        account.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        account.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        return accountMapper.mapToDto(accountRepository.save(account));
    }

    @Override
    public AccountDto updateStatusInAccountByIdToRemoved(UUID id) {
        Account account = accountRepository.findById(id)
                .orElseThrow(() -> new NotFoundAccountException("Account not found with id " + id));
        account.setStatus(Status.REMOVED);
        account.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        return accountMapper.mapToDto(accountRepository.save(account));
    }
}