package com.example.bankwebapp.entity;

import com.example.bankwebapp.entity.enums.Currencies;
import com.example.bankwebapp.entity.enums.ProductStatus;
import jakarta.persistence.*;
import jakarta.security.auth.message.config.ClientAuthContext;
import lombok.*;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Objects;
import java.util.UUID;

import static jakarta.persistence.CascadeType.*;

@Entity
@Table(name = "products")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private UUID id;

    @Column(name = "manager_id")
    private int managerId;

    @Column(name = "name")
    private String name;

    @Column(name = "status")
    private ProductStatus status;

    @Column(name = "currency_code")
    private Currencies currencyCode;

    @Column(name = "interest_rate")
    private BigDecimal interestRate;

    @Column(name = "limit")
    private int limit;

    @Column(name = "created_at")
    private Timestamp createdAt;

    @Column(name = "update_at")
    private Timestamp updatedAt;

    @ManyToOne(cascade = {MERGE, REFRESH, PERSIST}, fetch = FetchType.LAZY)
    @JoinColumn(name = "id")
    private Manager manager;

    @OneToOne(cascade = {MERGE, REFRESH, PERSIST}, fetch = FetchType.LAZY)
    private Agreement agreement;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return managerId == product.managerId && limit == product.limit && Objects.equals(id, product.id) && Objects.equals(name, product.name) && Objects.equals(createdAt, product.createdAt) && Objects.equals(updatedAt, product.updatedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, managerId, name, limit, createdAt, updatedAt);
    }

    @Override
    public String toString() {
        return "Product[ " +
                "name: " + name +
                ", status: " + status +
                ", currencies: " + currencyCode +
                ", interestRate: " + interestRate +
                ", limit: " + limit +
                ", createdAt: " + createdAt +
                ", updatedAt: " + updatedAt +
                "]";
    }
}
