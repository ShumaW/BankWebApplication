package com.example.bankwebapp.entity;

import com.example.bankwebapp.entity.enums.Status;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.sql.Timestamp;
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

    @OneToOne(fetch = FetchType.LAZY)
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

    @Column(name = "update_at")
    private Timestamp updatedAt;

    @OneToMany(cascade = {MERGE, REFRESH, PERSIST}, fetch = FetchType.LAZY)
    private Set<Product> productsSet;

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
