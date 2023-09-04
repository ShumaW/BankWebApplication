package com.example.bankwebapp.entity;

import com.example.bankwebapp.entity.enums.AgreementStatus;
import lombok.*;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Agreement {
    private int id;
    private int accountId;
    private int productId;
    private BigDecimal interestRate;
    private AgreementStatus status;
    private BigDecimal sum;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Agreement agreement = (Agreement) o;
        return id == agreement.id && accountId == agreement.accountId && productId == agreement.productId && Objects.equals(sum, agreement.sum) && Objects.equals(createdAt, agreement.createdAt) && Objects.equals(updatedAt, agreement.updatedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, accountId, productId, sum, createdAt, updatedAt);
    }

    @Override
    public String toString() {
        return "Agreement[ " +
                "id: " + id +
                ", accountId: " + accountId +
                ", productId: " + productId +
                ", interestRate: " + interestRate +
                ", status: " + status +
                ", sum: " + sum +
                ", createdAt: " + createdAt +
                ", updatedAt: " + updatedAt +
                "]" + "\n";
    }
}
