package com.example.bankwebapp.service.impl;

import com.example.bankwebapp.controller.CurrencyController;
import com.example.bankwebapp.dto.TransactionDto;
import com.example.bankwebapp.entity.Account;
import com.example.bankwebapp.entity.Transaction;
import com.example.bankwebapp.entity.enums.Currencies;
import com.example.bankwebapp.exceptions.NotEnoughMoneyException;
import com.example.bankwebapp.mapper.TransactionMapper;
import com.example.bankwebapp.repository.AccountRepository;
import com.example.bankwebapp.repository.TransactionRepository;
import com.example.bankwebapp.util.CreatorDto;
import com.example.bankwebapp.util.CreatorEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TransactionServiceImplTest {

    @InjectMocks
    TransactionServiceImpl transactionService;

    @Mock
    TransactionRepository transactionRepository;

    @Mock
    TransactionMapper transactionMapper;

    @Mock
    AccountRepository accountRepository;

    @Mock
    CurrencyController currencyController;

    @Test
    void testFindAllTransactionsWhereAccountIdIs() {
        //given
        List<TransactionDto> transactionDtoList = new ArrayList<>();
        transactionDtoList.add(CreatorDto.getTransactionDtoOne());
        List<Transaction> transactionList = new ArrayList<>();
        Transaction transaction = CreatorEntity.getTransactionOne();
        transactionList.add(transaction);
        //when
        when(transactionRepository.findAllByAccountId(transaction.getId())).thenReturn(transactionList);
        when(transactionMapper.mapToListDto(transactionList)).thenReturn(transactionDtoList);
        //then
        List<TransactionDto> allTransactionListWhereAccountIs =
                transactionService.findAllTransactionsWhereAccountIdIs(transaction.getId());
        assertEquals(1, allTransactionListWhereAccountIs.size());
        assertNotNull(allTransactionListWhereAccountIs);
        verify(transactionRepository, times(1)).findAllByAccountId(transaction.getId());

    }

    @Test
    void createNewTransactionWithSameCurrenciesTest(){
        //given
        Transaction transaction = CreatorEntity.getTransactionOne();
        TransactionDto transactionDto = CreatorDto.getTransactionDtoOne();
        UUID debitAccountId = UUID.fromString(transactionDto.getDebitAccountId());
        UUID creditAccountId = UUID.fromString(transactionDto.getCreditAccountId());
        Account debitAccount = CreatorEntity.getAccountOne();
        Account creditAccount = CreatorEntity.getAccountTwo();

        //when
        when(accountRepository.findById(debitAccountId)).thenReturn(Optional.of(debitAccount));
        when(accountRepository.findById(creditAccountId)).thenReturn(Optional.of(creditAccount));
        when(accountRepository.save(debitAccount)).thenReturn(debitAccount);
        when(accountRepository.save(creditAccount)).thenReturn(creditAccount);
        when(transactionRepository.save(transaction)).thenReturn(transaction);
        when(transactionMapper.mapToDto(transaction)).thenReturn(transactionDto);

        // then
        TransactionDto newTransaction = transactionService.createNewTransaction(transactionDto);
        assertEquals(transactionDto, newTransaction);
        assertNotNull(newTransaction);
        verify(accountRepository, times(1)).save(debitAccount);
        verify(accountRepository, times(1)).save(creditAccount);
        verify(transactionRepository, times(1)).save(transaction);
    }

    @Test
    void notEnoughMoneyExceptionTest(){
        //given
        TransactionDto transactionDto = CreatorDto.getTransactionDtoTwo();
        UUID debitAccountId = UUID.fromString(transactionDto.getDebitAccountId());
        UUID creditAccountId = UUID.fromString(transactionDto.getCreditAccountId());
        Account debitAccount = CreatorEntity.getAccountOne();
        Account creditAccount = CreatorEntity.getAccountTwo();
        //when
        when(accountRepository.findById(debitAccountId)).thenReturn(Optional.of(debitAccount));
        when(accountRepository.findById(creditAccountId)).thenReturn(Optional.of(creditAccount));
        //then
        assertThrows(NotEnoughMoneyException.class, () -> transactionService.createNewTransaction(transactionDto));
    }

    @Test
    void createNewTransactionWithDifferentCurrenciesTest(){
        //given
        Transaction transaction = CreatorEntity.getTransactionThree();
        TransactionDto transactionDto = CreatorDto.getTransactionDtoThree();
        UUID debitAccountId = UUID.fromString(transactionDto.getDebitAccountId());
        UUID creditAccountId = UUID.fromString(transactionDto.getCreditAccountId());
        Account debitAccount = CreatorEntity.getAccountOne();
        Account creditAccount = CreatorEntity.getAccountTwo();
        Currencies debitAccountCurrency = debitAccount.getCurrencyCode();
        Currencies transactionCurrency = transaction.getCurrencyCode();

        //when
        when(accountRepository.findById(debitAccountId)).thenReturn(Optional.of(debitAccount));
        when(accountRepository.findById(creditAccountId)).thenReturn(Optional.of(creditAccount));
        when(accountRepository.save(debitAccount)).thenReturn(debitAccount);
        when(accountRepository.save(creditAccount)).thenReturn(creditAccount);
        when(transactionRepository.save(transaction)).thenReturn(transaction);
        when(transactionMapper.mapToDto(transaction)).thenReturn(transactionDto);
        when(currencyController.getCurrencyRate(transactionCurrency,debitAccountCurrency))
                .thenReturn(new BigDecimal("1.057057"));
        //then
        TransactionDto newTransaction = transactionService.createNewTransaction(transactionDto);
        assertEquals(transactionDto, newTransaction);
        assertNotNull(newTransaction);
        verify(accountRepository, times(1)).save(debitAccount);
        verify(accountRepository, times(1)).save(creditAccount);
        verify(transactionRepository, times(1)).save(transaction);
    }

}