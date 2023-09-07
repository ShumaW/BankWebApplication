package com.example.bankwebapp.entity;

import com.example.bankwebapp.entity.enums.Status;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Objects;
import java.util.Set;

import static jakarta.persistence.CascadeType.*;

@Entity
@Table(name = "agreements")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Agreement {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @Column(name = "account_id")
    private int accountId;

    @Column(name = "product_id")
    private int productId;

    @Column(name = "interest_rate")
    private BigDecimal interestRate;

    @Column(name = "status")
    private Status status;

    @Column(name = "sum")
    private BigDecimal sum;

    @Column(name = "created_at")
    private Timestamp createdAt;

    @Column(name = "update_at")
    private Timestamp updatedAt;

    @OneToMany(cascade = {MERGE, REFRESH, PERSIST}, fetch = FetchType.LAZY)
    private Set<Product> productsSet;

    @OneToOne(fetch = FetchType.LAZY)
    private Account account;

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
