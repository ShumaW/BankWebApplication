package com.example.bankwebapp.entity;

import com.example.bankwebapp.entity.enums.Status;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

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
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id", referencedColumnName = "id")
    private Account account;

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

    @Column(name = "updated_at")
    private Timestamp updatedAt;

    @OneToMany(cascade = {MERGE, REFRESH, PERSIST}, fetch = FetchType.LAZY, orphanRemoval = true)
    private Set<Product> productsSet;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Agreement agreement = (Agreement) o;
        return id == agreement.id && productId == agreement.productId && Objects.equals(account, agreement.account);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, account, productId);
    }

    @Override
    public String toString() {
        return "Agreement[ " +
                "id: " + id +
                ", productId: " + productId +
                ", interestRate: " + interestRate +
                ", status: " + status +
                ", sum: " + sum +
                ", createdAt: " + createdAt +
                ", updatedAt: " + updatedAt +
                "]" + "\n";
    }
}
