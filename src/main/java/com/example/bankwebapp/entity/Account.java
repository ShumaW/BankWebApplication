package com.example.bankwebapp.entity;

import com.example.bankwebapp.entity.enums.AccountStatus;
import com.example.bankwebapp.entity.enums.AccountType;
import com.example.bankwebapp.entity.enums.Currencies;
import lombok.*;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Account {
    private UUID id;
    private int clientId;
    private String name;
    private AccountType type;
    private AccountStatus status;
    private BigDecimal balance;
    private Currencies currencyCode;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return id == account.id && clientId == account.clientId && Objects.equals(name, account.name) && type == account.type && status == account.status && Objects.equals(balance, account.balance) && currencyCode == account.currencyCode && Objects.equals(createdAt, account.createdAt) && Objects.equals(updatedAt, account.updatedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, clientId, name, type, status, balance, currencyCode, createdAt, updatedAt);
    }

    @Override
    public String toString() {
        return "Account[ " +
                "name: " + name +
                ", type: " + type +
                ", status: " + status +
                ", balance: " + balance +
                ", currencyCode: " + currencyCode +
                ", createdAt: " + createdAt +
                ", updatedAt: " + updatedAt +
                "]" + "\n";
    }
}
