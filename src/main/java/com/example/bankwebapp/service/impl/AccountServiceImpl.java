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
import com.example.bankwebapp.service.interfaсes.AccountService;
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

    /**
     * This public method allows you to get information about account by ID in the database.
     * @param id
     * @return accountDto
     */
    @Override
    public AccountDto getAccountById(UUID id) {
        log.info("Get account by id {}", id);
        return accountMapper.mapToDto(accountRepository.findById(id)
                .orElseThrow(() -> new NotFoundAccountException("Account not found with id " + id)));
    }

    /**
     * This public method allows you to get information about all account in the database.
     * @return List<AccountDto>
     */
    @Override
    public List<AccountDto> getAllAccounts() {
        log.info("Get all accounts.");
        return accountMapper.mapToListDto(accountRepository.findAll());
    }

    /**
     * This public method allows you to update information about account in the database by ID.
     * @param accountDto
     * @return accountDto
     */
    @Override
    @Transactional
    public AccountDto update(AccountDto accountDto) {
        log.info("Update account with id {}.", accountDto.getClientId());
        Account account = accountRepository.findById(UUID.fromString(accountDto.getId()))
                .orElseThrow(() -> new NotFoundAccountException("Account not found with id " + accountDto.getId()));
        account.setName(accountDto.getName());
        account.setBalance(new BigDecimal(accountDto.getBalance()));
        account.setStatus(Status.valueOf(accountDto.getStatus()));
        account.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        accountRepository.save(account);
        return accountMapper.mapToDto(account);
    }

    /**
     * This public method allows you to get a list of all accounts with a given status.
     * @param status
     * @return List<AccountDto>
     */
    @Override
    public List<AccountDto> getAllAccountsWhereStatusIs(Status status) {
        log.info("Get all accounts, where status is {}.", status);
        return accountMapper.mapToListDto(accountRepository.findAllAccountsWhereStatusIs(status));
    }

    /**
     * This public method allows you to save information about the new account to the database.
     * @param accountDto
     * @return accountDto
     */
    @Override
    public AccountDto createAccount(AccountDto accountDto) {
        log.info("Create new account.");
        Client client = clientRepository.findById(UUID.fromString(accountDto.getClientId()))
                .orElseThrow(() -> new NotFoundClientException("Client not found with id " + accountDto.getClientId()));
        Account account = accountMapper.mapToEntity(accountDto);
        account.setClient(client);
        account.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        account.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        return accountMapper.mapToDto(accountRepository.save(account));
    }

    /**
     * This public method allows you to update information about account in the database by ID
     * and made this account with status REMOVED.
     * @param id
     * @return accountDto
     */
    @Override
    public AccountDto updateStatusInAccountByIdToRemoved(UUID id) {
        log.info("Update status to REMOVED with account id {}", id);
        Account account = accountRepository.findById(id)
                .orElseThrow(() -> new NotFoundAccountException("Account not found with id " + id));
        account.setStatus(Status.REMOVED);
        account.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        return accountMapper.mapToDto(accountRepository.save(account));
    }
}