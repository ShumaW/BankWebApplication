package com.example.bankwebapp.entity;

import com.example.bankwebapp.entity.enums.TransactionType;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Objects;
import java.util.UUID;

import static jakarta.persistence.CascadeType.*;

@Entity
@Table(name = "transactions")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private UUID id;

    @Column(name = "debit_account_id")
    @ManyToOne(cascade = {MERGE, PERSIST, REFRESH, REMOVE}, fetch = FetchType.LAZY)
    @JoinColumn(referencedColumnName = "id")
    private Account debitAccountId;

    @Column(name = "credit_account_id")
    @ManyToOne(cascade = {MERGE, PERSIST, REFRESH, REMOVE}, fetch = FetchType.LAZY)
    @JoinColumn(referencedColumnName = "id")
    private Account creditAccountId;

    @Column(name = "type")
    private TransactionType type;

    @Column(name = "amount")
    private BigDecimal amount;

    @Column(name = "description")
    private String description;

    @Column(name = "created_at")
    private Timestamp createdAt;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transaction that = (Transaction) o;
        return Objects.equals(id, that.id) && Objects.equals(debitAccountId, that.debitAccountId) && Objects.equals(creditAccountId, that.creditAccountId) && Objects.equals(amount, that.amount) && Objects.equals(createdAt, that.createdAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, debitAccountId, creditAccountId, amount, createdAt);
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
