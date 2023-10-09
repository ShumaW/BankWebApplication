package com.example.bankwebapp.entity;

import com.example.bankwebapp.entity.enums.Currencies;
import com.example.bankwebapp.entity.enums.TransactionType;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Objects;
import java.util.UUID;


@Entity
@Table(name = "transactions")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "debit_account_id",referencedColumnName = "id")
    private Account debitAccount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "credit_account_id",referencedColumnName = "id")
    private Account creditAccount;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private TransactionType type;

    @Column(name = "currency_code")
    @Enumerated(EnumType.STRING)
    private Currencies currencyCode;

    @Column(name = "amount")
    private BigDecimal amount;

    @Column(name = "description")
    private String description;

    @Column(name = "created_at", updatable = false)
    private Timestamp createdAt;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transaction that = (Transaction) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Transaction[ " +
                "id: " + id +
                ", debitAccountId: " + debitAccount +
                ", creditAccountId: " + creditAccount +
                ", type: " + type +
                ", amount: " + amount +
                ", description: '" + description +
                ", createdAt: " + createdAt +
                "]" + "\n";
    }
}