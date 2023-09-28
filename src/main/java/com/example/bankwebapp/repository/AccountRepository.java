package com.example.bankwebapp.repository;

import com.example.bankwebapp.entity.Account;
import com.example.bankwebapp.entity.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface AccountRepository extends JpaRepository<Account, UUID> {

    @Query("from Account acc where acc.status =:status")
    List<Account> findAllAccountsWhereStatusIs(Status status);
}