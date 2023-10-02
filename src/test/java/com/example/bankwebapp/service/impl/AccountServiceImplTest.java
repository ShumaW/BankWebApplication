package com.example.bankwebapp.service.impl;

import com.example.bankwebapp.dto.AccountDto;
import com.example.bankwebapp.entity.Account;
import com.example.bankwebapp.entity.enums.Status;
import com.example.bankwebapp.exceptions.NotFoundAccountException;
import com.example.bankwebapp.mapper.AccountMapper;
import com.example.bankwebapp.repository.AccountRepository;
import com.example.bankwebapp.util.CreatorDto;
import com.example.bankwebapp.util.CreatorEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AccountServiceImplTest {

    @InjectMocks
    AccountServiceImpl accountService;

    @Mock
    AccountRepository accountRepository;

    @Mock
    AccountMapper accountMapper;

    @Test
    void getAccountByIdTest() {
        //given
        Account account = CreatorEntity.getAccount();
        UUID accountId = account.getId();
        AccountDto accountDto = CreatorDto.getAccountDto();
        //when
        when(accountRepository.findById(accountId)).thenReturn(Optional.of(account));
        when(accountMapper.mapToDto(account)).thenReturn(accountDto);
        //then
        AccountDto outputAccountDto = accountService.getAccountById(accountId);
        assertEquals(accountDto,outputAccountDto);
        verify(accountRepository, times(1)).findById(accountId);
    }

    @Test
    void notFoundAccountByIdException(){
        UUID random = UUID.randomUUID();
        String expected = "Account not found with id " + random;
        Exception exception = assertThrows(NotFoundAccountException.class, () -> accountService.getAccountById(random));
        assertEquals(expected, exception.getMessage());
    }

    @Test
    void getAllAccountsTest() {
        //given
        List<AccountDto> accountDtoList = new ArrayList<>();
        accountDtoList.add(CreatorDto.getAccountDto());
        List<Account> accountList = new ArrayList<>();
        accountList.add(CreatorEntity.getAccount());
        //when
        when(accountRepository.findAll()).thenReturn(accountList);
        when(accountMapper.mapToListDto(accountList)).thenReturn(accountDtoList);
        //then
        List<AccountDto> allAccounts = accountService.getAllAccounts();
        assertEquals(accountDtoList,allAccounts);
        verify(accountRepository, times(1)).findAll();
    }

    @Test
    void updateTest() {
        //given
        AccountDto accountDto = CreatorDto.getAccountDto();
        Account account = CreatorEntity.getAccount();
        AccountDto outpuitAccountDto = new AccountDto(account.getId().toString(),
                account.getClient().getId().toString(),
                account.getName(),
                account.getType().toString(),
                "BLOCKED",
                account.getBalance().toString(),
                account.getCurrencyCode().toString());
        //when
        when(accountRepository.findById(account.getId())).thenReturn(Optional.of(account));
        when(accountMapper.mapToDto(account)).thenReturn(outpuitAccountDto);
        //then
        AccountDto update = accountService.update(accountDto);
        assertEquals(account.getName(), update.getName());
        assertNotEquals(account.getStatus(), Status.valueOf(update.getStatus()));
        verify(accountRepository, times(1)).save(account);
    }

    @Test
    void getAllAccountsWhereStatusIsTest() {
        //given
        List<Account> accountList = new ArrayList<>();
        Account account = CreatorEntity.getAccount();
        accountList.add(account);
        List<AccountDto> accountDtoList = new ArrayList<>();
        AccountDto accountDto = CreatorDto.getAccountDto();
        accountDtoList.add(accountDto);
        //when
        when(accountRepository.findAllAccountsWhereStatusIs(account.getStatus())).thenReturn(accountList);
        when(accountMapper.mapToListDto(accountList)).thenReturn(accountDtoList);
        //then
        List<AccountDto> allAccountsWhereStatusIs = accountService.getAllAccountsWhereStatusIs(account.getStatus());
        assertEquals(1,allAccountsWhereStatusIs.size());
        assertNotNull(allAccountsWhereStatusIs);
        verify(accountRepository, times(1)).findAllAccountsWhereStatusIs(account.getStatus());
    }
}