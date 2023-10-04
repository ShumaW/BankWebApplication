package com.example.bankwebapp.util;

import com.example.bankwebapp.entity.*;
import com.example.bankwebapp.entity.enums.AccountType;
import com.example.bankwebapp.entity.enums.Currencies;
import com.example.bankwebapp.entity.enums.Status;
import com.example.bankwebapp.entity.enums.TransactionType;
import lombok.experimental.UtilityClass;

import java.math.BigDecimal;
import java.util.UUID;

@UtilityClass
public class CreatorEntity {

    public static Account getAccount() {
        Account account = new Account();
        account.setId(UUID.fromString("0c70afa1-c654-471d-9cb2-437ac289d62c"));
        account.setClient(getClient());
        account.setName("ChandlerSpawforth_debit");
        account.setType(AccountType.DEPOSIT);
        account.setStatus(Status.ACTIVE);
        account.setBalance(new BigDecimal("2500.98"));
        account.setCurrencyCode(Currencies.USD);
        return account;
    }

    public static Client getClient() {
        Client client = new Client();
        client.setId(UUID.fromString("09b90738-b37c-4035-abdc-550b28f43c33"));
        client.setStatus(Status.valueOf("ACTIVE"));
        client.setTaxСode("1234567899");
        client.setFirstName("Chandler");
        client.setLastName("Spawforth");
        client.setEmail("cspawforth0@jimdo.com");
        client.setAddress("1782 Artisan Pass");
        client.setPhone("495-203-2229");
        client.setManager(getManager());
        return client;
    }

    public static Manager getManager(){
        Manager manager = new Manager();
        manager.setId(UUID.fromString("1763f054-5393-11ee-8c99-0242ac120002"));
        manager.setFirstName("Sem");
        manager.setLastName("Smith");
        manager.setStatus(Status.valueOf("ACTIVE"));
        return manager;
    }

    public static Agreement getAgreement() {
        Agreement agreement = new Agreement();
        agreement.setId(UUID.fromString("416c806e-769e-45f9-8aea-8c2527da7fa5"));
        agreement.setAccount(getAccount());
        agreement.setProduct(getProduct());
        agreement.setInterestRate(new BigDecimal("2.7500"));
        agreement.setStatus(Status.valueOf("PENDING"));
        agreement.setSum(new BigDecimal("50000.00"));
        return agreement;
    }

    public static Product getProduct(){
        Product product = new Product();
        product.setId(UUID.fromString("261241cb-f206-4b29-a779-6fe116538763"));
        product.setName("credit");
        product.setStatus(Status.valueOf("ACTIVE"));
        product.setCurrencyCode(Currencies.valueOf("EUR"));
        product.setInterestRate(new BigDecimal("2.7500"));
        product.setLimit(100000.00);
        product.setManager(new Manager(UUID.fromString("1763f054-5393-11ee-8c99-0242ac120002")));
        return product;
    }

    public static Transaction getTransaction() {
        Transaction transaction = new Transaction();
        transaction.setId(UUID.fromString("d965c068-4d54-4382-ac62-7f2db627dc6c"));
        transaction.setDebitAccountId(new Account(UUID.fromString("f3deef15-86da-46ec-9d27-8305dc37e6a4")));
        transaction.setCreditAccountId(new Account(UUID.fromString("d62c0f9c-50b4-48ab-96b9-7c81dd04ac48")));
        transaction.setType(TransactionType.TRANSFER);
        transaction.setCurrencyCode(Currencies.USD);
        transaction.setAmount(new BigDecimal("4045.12000"));
        transaction.setDescription("pay");
        return transaction;
    }
}
