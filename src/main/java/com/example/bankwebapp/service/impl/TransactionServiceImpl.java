package com.example.bankwebapp.service.impl;

import com.example.bankwebapp.controller.CurrencyController;
import com.example.bankwebapp.dto.TransactionDto;
import com.example.bankwebapp.entity.Account;
import com.example.bankwebapp.entity.Transaction;
import com.example.bankwebapp.entity.enums.Currencies;
import com.example.bankwebapp.entity.enums.TransactionType;
import com.example.bankwebapp.exceptions.NotEnoughMoneyException;
import com.example.bankwebapp.exceptions.NotFoundAccountException;
import com.example.bankwebapp.mapper.TransactionMapper;
import com.example.bankwebapp.repository.AccountRepository;
import com.example.bankwebapp.repository.TransactionRepository;
import com.example.bankwebapp.service.interfases.TransactionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;

    private final TransactionMapper transactionMapper;

    private final AccountRepository accountRepository;

    private final CurrencyController currencyController;

    @Override
    public List<TransactionDto> findAllTransactionsWhereAccountIdIs(UUID accountId) {
        return transactionMapper.mapToListDto(transactionRepository.findAllByAccountId(accountId));
    }

    @Transactional
    @Override
    public TransactionDto createNewTransaction(TransactionDto transactionDto) {

        BigDecimal amountOfTransaction = new BigDecimal(transactionDto.getAmount());

        Currencies currenciesOfTransaction = Currencies.valueOf(transactionDto.getCurrencyCode());

        UUID debitAcoountId = UUID.fromString(transactionDto.getDebitAccountId());
        UUID creditAccountId = UUID.fromString(transactionDto.getCreditAccountId());

        Account debitAccount = accountRepository.findById(debitAcoountId)
                .orElseThrow(() -> new NotFoundAccountException("Account not found with id " + debitAcoountId));
        Account creditAccount = accountRepository.findById(creditAccountId)
                .orElseThrow(() -> new NotFoundAccountException("Account not found with id " + creditAccountId));

        if (currenciesOfTransaction.equals(debitAccount.getCurrencyCode())) {
            Transaction transaction = saveTransaction(creditAccount, debitAccount, amountOfTransaction, transactionDto);
            return transactionMapper.mapToDto(transaction);
        } else {
            BigDecimal currencyRate = currencyController.getCurrencyRate(currenciesOfTransaction, creditAccount.getCurrencyCode());
            BigDecimal multiply = amountOfTransaction.multiply(currencyRate);
            Transaction transaction = saveTransaction(creditAccount, debitAccount, multiply, transactionDto);
            return transactionMapper.mapToDto(transaction);
        }
    }

    private void checkCreditAccountBalance(Account creditAccount, BigDecimal amountOfTransaction) {
        if (creditAccount.getBalance().compareTo(amountOfTransaction) < 0) {
            throw new NotEnoughMoneyException("There is not enough money in the account!");
        }
    }

    private Transaction saveTransaction(Account creditAccount, Account debitAccount,
                                        BigDecimal amountOfTransaction, TransactionDto transactionDto){
        checkCreditAccountBalance(creditAccount, amountOfTransaction);
        creditAccount.setBalance(creditAccount.getBalance().divide(amountOfTransaction, 6, RoundingMode.HALF_DOWN));
        accountRepository.save(creditAccount);
        debitAccount.setBalance(debitAccount.getBalance().add(amountOfTransaction));
        accountRepository.save(debitAccount);
        Transaction transaction = new Transaction();
        transaction.setDebitAccountId(accountRepository.findById(UUID.fromString(transactionDto.getDebitAccountId()))
                .get());
        transaction.setCreditAccountId(accountRepository.findById(UUID.fromString(transactionDto.getCreditAccountId()))
                .get());
        transaction.setType(TransactionType.valueOf(transactionDto.getType()));
        transaction.setAmount(new BigDecimal(transactionDto.getAmount()));
        transaction.setCurrencyCode(Currencies.valueOf(transactionDto.getCurrencyCode()));
        transaction.setDescription(transactionDto.getDescription());
        transaction.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        transactionRepository.save(transaction);
        return transaction;
    }
}
