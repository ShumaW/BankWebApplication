package com.example.bankwebapp.service.impl;

import com.example.bankwebapp.dto.TransactionDto;
import com.example.bankwebapp.mapper.TransactionMapper;
import com.example.bankwebapp.repository.TransactionRepository;
import com.example.bankwebapp.service.interfases.TransactionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;

    private final TransactionMapper transactionMapper;

    @Override
    public List<TransactionDto> findAllTransactionsWhereAccountIdIs(UUID accountId) {
        return transactionMapper.mapToListDto(transactionRepository.findAllByAccountId(accountId));
    }
}
