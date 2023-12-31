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
import com.example.bankwebapp.service.interfaсes.TransactionService;
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
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;

    private final TransactionMapper transactionMapper;

    private final AccountRepository accountRepository;

    private final CurrencyController currencyController;

    /**
     * This public method allows you to find all transactions in the database by account ID.
     * @param accountId
     * @return List<TransactionDto>
     */
    @Override
    public List<TransactionDto> findAllTransactionsWhereAccountIdIs(UUID accountId) {
        log.info("Get all transactions, where account id is {}.", accountId);
        return transactionMapper.mapToListDto(transactionRepository.findAllByAccountId(accountId));
    }

    /**
     * This public method creates a transaction in which funds are transferred from one account to another in the recipient's currency.
     * If at least one of the conditions for creating a transaction is not met, the transaction will not be created.
     * This is achieved by using the @Transactional annotation.
     * @param transactionDto
     * @return transactionDto
     */
    @Transactional
    @Override
    public TransactionDto createNewTransaction(TransactionDto transactionDto) {
        log.info("Create new transaction.");

        BigDecimal amountOfTransaction = new BigDecimal(transactionDto.getAmount());

        Currencies currencyCodeOfTransaction = Currencies.valueOf(transactionDto.getCurrencyCode());

        UUID debitAccountId = UUID.fromString(transactionDto.getDebitAccountId());
        UUID creditAccountId = UUID.fromString(transactionDto.getCreditAccountId());

        Account debitAccount = accountRepository.findById(debitAccountId)
                .orElseThrow(() -> new NotFoundAccountException("Account not found with id " + debitAccountId));
        Account creditAccount = accountRepository.findById(creditAccountId)
                .orElseThrow(() -> new NotFoundAccountException("Account not found with id " + creditAccountId));
        BigDecimal resultAmountOfTransaction = amountOfTransaction;

        if (!currencyCodeOfTransaction.equals(creditAccount.getCurrencyCode())) {
            BigDecimal currencyRate = currencyController.getCurrencyRate(currencyCodeOfTransaction,
                    creditAccount.getCurrencyCode());
            resultAmountOfTransaction = amountOfTransaction.multiply(currencyRate);
        }
        if (creditAccount.getBalance().compareTo(amountOfTransaction) < 0) {
            throw new NotEnoughMoneyException("There is not enough money in the account!");
        }
        return transactionMapper.mapToDto(saveTransaction(creditAccount, debitAccount,
                resultAmountOfTransaction, transactionDto));

    }

    private Transaction saveTransaction(Account creditAccount, Account debitAccount,
                                        BigDecimal amountOfTransaction, TransactionDto transactionDto){
        BigDecimal resultCreditBalance = creditAccount.getBalance().subtract(amountOfTransaction);
        creditAccount.setBalance(resultCreditBalance);
        accountRepository.save(creditAccount);
        debitAccount.setBalance(debitAccount.getBalance().add(amountOfTransaction));
        accountRepository.save(debitAccount);
        Transaction transaction = new Transaction();
        transaction.setDebitAccount(debitAccount);
        transaction.setCreditAccount(creditAccount);
        transaction.setType(TransactionType.valueOf(transactionDto.getType()));
        transaction.setAmount(new BigDecimal(transactionDto.getAmount()));
        transaction.setCurrencyCode(Currencies.valueOf(transactionDto.getCurrencyCode()));
        transaction.setDescription(transactionDto.getDescription());
        transaction.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        transactionRepository.save(transaction);
        return transaction;
    }
}
