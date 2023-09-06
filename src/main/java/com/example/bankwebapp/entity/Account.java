package com.example.bankwebapp.entity;

import com.example.bankwebapp.entity.enums.AccountStatus;
import com.example.bankwebapp.entity.enums.AccountType;
import com.example.bankwebapp.entity.enums.Currencies;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

import static jakarta.persistence.CascadeType.*;

@Entity
@Table(name="accounts")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private UUID id;

    @Column(name = "client_id")
    private int clientId;

    @Column(name = "name")
    private String name;

    @Column(name = "type")
    private AccountType type;

    @Column(name = "status")
    private AccountStatus status;

    @Column(name = "balance")
    private BigDecimal balance;

    @Column(name = "currency_code")
    private Currencies currencyCode;

    @Column(name = "create_at")
    private Timestamp createdAt;

    @Column(name = "update_at")
    private Timestamp updatedAt;

    @ManyToOne(cascade = {MERGE, PERSIST, REFRESH}, fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id", referencedColumnName = "id")
    private Client client;

    @OneToOne(cascade = {MERGE, REFRESH, PERSIST}, fetch = FetchType.LAZY)
    private Agreement agreement;

    @OneToMany(cascade = {MERGE, REFRESH, PERSIST}, fetch = FetchType.LAZY, mappedBy = "account")
    private Set<Transaction> debitTransaction;

    @OneToMany(cascade = {MERGE, REFRESH, PERSIST}, fetch = FetchType.LAZY, mappedBy = "account")
    private Set<Transaction> creditTransaction;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return clientId == account.clientId && Objects.equals(id, account.id) && Objects.equals(name, account.name) && Objects.equals(createdAt, account.createdAt) && Objects.equals(updatedAt, account.updatedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, clientId, name, createdAt, updatedAt);
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
