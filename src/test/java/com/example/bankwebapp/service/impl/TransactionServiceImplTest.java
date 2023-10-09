package com.example.bankwebapp.service.impl;

import com.example.bankwebapp.dto.TransactionDto;
import com.example.bankwebapp.entity.Account;
import com.example.bankwebapp.entity.Transaction;
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
    void createNewTransactionTest(){
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
}