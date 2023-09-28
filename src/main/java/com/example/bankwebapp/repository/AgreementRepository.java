package com.example.bankwebapp.repository;

import com.example.bankwebapp.entity.Agreement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface AgreementRepository extends JpaRepository<Agreement, UUID> {

    @Query("from Agreement ag where ag.account.client.id =:clientId")
    List<Agreement> findAllAccountByClientId(@Param("clientId") UUID clientId);
}