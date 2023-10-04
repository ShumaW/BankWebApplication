package com.example.bankwebapp.service.impl;

import com.example.bankwebapp.dto.TransactionDto;
import com.example.bankwebapp.entity.Transaction;
import com.example.bankwebapp.mapper.TransactionMapper;
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

    @Test
    void testFindAllTransactionsWhereAccountIdIs() {
        List<TransactionDto> transactionDtoList = new ArrayList<>();
        transactionDtoList.add(CreatorDto.getTransactionDto());
        List<Transaction> transactionList = new ArrayList<>();
        Transaction transaction = CreatorEntity.getTransaction();
        transactionList.add(transaction);

        when(transactionRepository.findAllByAccountId(transaction.getId())).thenReturn(transactionList);
        when(transactionMapper.mapToListDto(transactionList)).thenReturn(transactionDtoList);

        List<TransactionDto> allTransactionListWhereAccountIs =
                transactionService.findAllTransactionsWhereAccountIdIs(transaction.getId());
        assertEquals(1, allTransactionListWhereAccountIs.size());
        assertNotNull(allTransactionListWhereAccountIs);
        verify(transactionRepository, times(1)).findAllByAccountId(transaction.getId());

    }
}