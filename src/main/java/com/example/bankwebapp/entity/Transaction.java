package com.example.bankwebapp.entity;

import com.example.bankwebapp.entity.enums.TransactionType;
import lombok.*;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {
    private UUID id;
    private Account debitAccountId;
    private Account creditAccountId;
    private TransactionType type;
    private BigDecimal amount;
    private String description;
    private Timestamp createdAt;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transaction that = (Transaction) o;
        return Objects.equals(id, that.id) && Objects.equals(debitAccountId, that.debitAccountId) && Objects.equals(creditAccountId, that.creditAccountId) && type == that.type && Objects.equals(amount, that.amount) && Objects.equals(description, that.description) && Objects.equals(createdAt, that.createdAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, debitAccountId, creditAccountId, type, amount, description, createdAt);
    }

    @Override
    public String toString() {
        return "Transaction[ " +
                "id: " + id +
                ", debitAccountId: " + debitAccountId +
                ", creditAccountId: " + creditAccountId +
                ", type: " + type +
                ", amount: " + amount +
                ", description: '" + description +
                ", createdAt: " + createdAt +
                "]" + "\n";
    }
}
