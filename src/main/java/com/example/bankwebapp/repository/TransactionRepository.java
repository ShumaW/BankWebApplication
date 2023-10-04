package com.example.bankwebapp.repository;

import com.example.bankwebapp.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface TransactionRepository extends JpaRepository<Transaction, UUID> {

    @Query(value = "select * from transactions where debit_account_id =:accountId or credit_account_id =:accountId",
            nativeQuery = true)
    List<Transaction> findAllByAccountId(UUID accountId);
}