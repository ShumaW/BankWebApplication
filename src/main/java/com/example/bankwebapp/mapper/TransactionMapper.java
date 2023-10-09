package com.example.bankwebapp.mapper;

import com.example.bankwebapp.dto.TransactionDto;
import com.example.bankwebapp.entity.Transaction;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TransactionMapper {

    @Named("mapToTransactionDto")
    @Mapping(target = "debitAccountId", source = "transaction.debitAccount.id")
    @Mapping(target = "creditAccountId", source = "transaction.creditAccount.id")
    TransactionDto mapToDto(Transaction transaction);

    @IterableMapping(qualifiedByName = "mapToTransactionDto")
    List<TransactionDto> mapToListDto(List<Transaction> transactionList);

    Transaction mapToEntity(TransactionDto transactionDto);
}
