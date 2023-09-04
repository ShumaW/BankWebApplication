package com.example.bankwebapp.entity;

import com.example.bankwebapp.entity.enums.Currencies;
import com.example.bankwebapp.entity.enums.ProductStatus;
import lombok.*;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    private UUID id;
    private int managerId;
    private String name;
    private ProductStatus status;
    private Currencies currencyCode;
    private BigDecimal interestRate;
    private int limit;
    private Timestamp createdAt;
    private Timestamp updatedAt;

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
