package com.example.bankwebapp.repository;

import com.example.bankwebapp.entity.Client;
import com.example.bankwebapp.entity.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public interface ClientRepository extends JpaRepository<Client, UUID> {

    @Query("from Client cl where cl.status =:status")
    List<Client> findAllClientWhereStatusIs(@Param("status") Status status);

    @Query(value = "select * from clients where id in (select client_id from accounts where balance >:sum)", nativeQuery = true)
    List<Client> findAllClientWhereBalanceMoreThan(@Param("sum") BigDecimal sum);
}