package com.example.bankwebapp.mapper;

import com.example.bankwebapp.dto.TransactionDto;
import com.example.bankwebapp.entity.Transaction;
import com.example.bankwebapp.util.CreatorDto;
import com.example.bankwebapp.util.CreatorEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class TransactionMapperTest {

    TransactionMapper transactionMapper = new TransactionMapperImpl();

    @Test
    void mapToDtoTest() {
        Transaction transaction = CreatorEntity.getTransactionOne();
        TransactionDto transactionDto = CreatorDto.getTransactionDtoOne();
        TransactionDto outputTransactionDto = transactionMapper.mapToDto(transaction);

        compareTwoDto(transactionDto, outputTransactionDto);
    }

    @Test
    void mapToListDtoTest() {
        List<Transaction> transactionList = new ArrayList<>();
        transactionList.add(CreatorEntity.getTransactionOne());
        List<TransactionDto> transactionDtoList = new ArrayList<>();
        transactionDtoList.add(CreatorDto.getTransactionDtoOne());

        List<TransactionDto> outputTransactionDtoList = transactionMapper.mapToListDto(transactionList);

        compareTwoListOfClientDto(transactionDtoList, outputTransactionDtoList);

    }

    private void compareTwoDto(TransactionDto transactionDto, TransactionDto outputTransactionDto){
        assertAll(
                () -> assertEquals(transactionDto.getId(), outputTransactionDto.getId()),
                () -> assertEquals(transactionDto.getDebitAccountId(), outputTransactionDto.getDebitAccountId()),
                () -> assertEquals(transactionDto.getCreditAccountId(), outputTransactionDto.getCreditAccountId()),
                () -> assertEquals(transactionDto.getCurrencyCode(), outputTransactionDto.getCurrencyCode()),
                () -> assertEquals(transactionDto.getType(), outputTransactionDto.getType()),
                () -> assertEquals(transactionDto.getAmount(), outputTransactionDto.getAmount()),
                () -> assertEquals(transactionDto.getDescription(), outputTransactionDto.getDescription())
        );
    }

    private void compareTwoListOfClientDto(List<TransactionDto> transactionDtoList, List<TransactionDto> outputTransactionDtoList){
        assertEquals(transactionDtoList.size(),outputTransactionDtoList.size());
        for (int i = 0; i < transactionDtoList.size(); i++) {
            compareTwoDto(transactionDtoList.get(i),outputTransactionDtoList.get(i));
        }
    }
}