package com.example.bankwebapp.util;

import com.example.bankwebapp.entity.*;
import com.example.bankwebapp.entity.enums.*;
import lombok.experimental.UtilityClass;

import java.math.BigDecimal;
import java.util.UUID;

@UtilityClass
public class CreatorEntity {

    public static Account getAccountOne() {
        Account account = new Account();
        account.setId(UUID.fromString("0c70afa1-c654-471d-9cb2-437ac289d62c"));
        account.setClient(getClientOne());
        account.setName("ChandlerSpawforth_debit");
        account.setType(AccountType.DEPOSIT);
        account.setStatus(Status.ACTIVE);
        account.setBalance(new BigDecimal("1000"));
        account.setCurrencyCode(Currencies.USD);
        return account;
    }

    public static Account getAccountTwo() {
        Account account = new Account();
        account.setId(UUID.fromString("8afd7502-1e80-4d11-9cc3-623d00cd73c8"));
        account.setClient(getClientTwo());
        account.setName("JimmiePinnere_credit");
        account.setType(AccountType.CREDIT);
        account.setStatus(Status.ACTIVE);
        account.setBalance(new BigDecimal("41000"));
        account.setCurrencyCode(Currencies.USD);
        return account;
    }

    public static Client getClientOne() {
        Client client = new Client();
        client.setId(UUID.fromString("09b90738-b37c-4035-abdc-550b28f43c33"));
        client.setStatus(Status.valueOf("ACTIVE"));
        client.setTaxСode("1234567899");
        client.setFirstName("Chandler");
        client.setLastName("Spawforth");
        client.setEmail("cspawforth0@jimdo.com");
        client.setPassword("$2a$12$I4an9qEkoix7pL2FdxJbx.fpCT84jwqmctkNN9xSk3.Nv53zf7f9u");
        client.setAddress("1782 Artisan Pass");
        client.setPhone("495-203-2229");
        client.setManager(getManager());
        return client;
    }

    public static Client getClientTwo() {
        Client client = new Client();
        client.setId(UUID.fromString("53de66be-9648-4545-b399-00970aec0a43"));
        client.setStatus(Status.valueOf("ACTIVE"));
        client.setTaxСode("3216549877");
        client.setFirstName("Jimmie");
        client.setLastName("Pinnere");
        client.setEmail("jpinnere1@globo.com");
        client.setAddress("1143 Coleman Street");
        client.setPhone("393-236-6107");
        client.setManager(getManager());
        return client;
    }

    public static Manager getManager(){
        Manager manager = new Manager();
        manager.setId(UUID.fromString("1763f054-5393-11ee-8c99-0242ac120002"));
        manager.setFirstName("Sem");
        manager.setLastName("Smith");
        manager.setStatus(Status.valueOf("ACTIVE"));
        manager.setEmail("semsmith@mail.com");
        return manager;
    }

    public static Agreement getAgreement() {
        Agreement agreement = new Agreement();
        agreement.setId(UUID.fromString("416c806e-769e-45f9-8aea-8c2527da7fa5"));
        agreement.setAccount(getAccountOne());
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

    public static Transaction getTransactionOne() {
        Transaction transaction = new Transaction();
        transaction.setId(null);
        transaction.setDebitAccount(getAccountOne());
        transaction.setCreditAccount(getAccountTwo());
        transaction.setType(TransactionType.TRANSFER);
        transaction.setCurrencyCode(Currencies.USD);
        transaction.setAmount(new BigDecimal("150.4525"));
        transaction.setDescription("pay");
        return transaction;
    }

    public static Transaction getTransactionTwo() {
        Transaction transaction = new Transaction();
        transaction.setId(null);
        transaction.setDebitAccount(getAccountOne());
        transaction.setCreditAccount(getAccountTwo());
        transaction.setType(TransactionType.TRANSFER);
        transaction.setCurrencyCode(Currencies.USD);
        transaction.setAmount(new BigDecimal("100000"));
        transaction.setDescription("pay");
        return transaction;
    }

    public static Transaction getTransactionThree() {
        Transaction transaction = new Transaction();
        transaction.setId(null);
        transaction.setDebitAccount(getAccountOne());
        transaction.setCreditAccount(getAccountTwo());
        transaction.setType(TransactionType.TRANSFER);
        transaction.setCurrencyCode(Currencies.EUR);
        transaction.setAmount(new BigDecimal("100.00"));
        transaction.setDescription("pay");
        return transaction;
    }

    public static User getUser() {
        User user = new User();
        user.setEmail("admin@mail.com");
        user.setPassword("admin");
        user.setStatus(Status.ACTIVE);
        user.setRole(Role.admin);
        return user;
    }
}
