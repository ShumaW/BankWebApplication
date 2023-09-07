package com.example.bankwebapp.entity;

import com.example.bankwebapp.entity.enums.Status;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

import static jakarta.persistence.CascadeType.*;

@Entity
@Table(name = "managers")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Manager {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private UUID id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "status")
    private Status status;

    @Column(name = "description")
    private String description;

    @Column(name = "created_at")
    private Timestamp createdAt;

    @OneToMany(cascade = {MERGE, REFRESH, PERSIST}, fetch = FetchType.LAZY, mappedBy = "manager")
    private Set<Client> clientSet;

    @OneToMany(cascade = {MERGE, REFRESH, PERSIST}, fetch = FetchType.LAZY, mappedBy = "manager")
    private Set<Product> productSet;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Manager manager = (Manager) o;
        return Objects.equals(id, manager.id) && Objects.equals(firstName, manager.firstName) && Objects.equals(lastName, manager.lastName) && Objects.equals(createdAt, manager.createdAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, createdAt);
    }

    @Override
    public String toString() {
        return "Manager[ " +
                "id: " + id +
                ", firstName: " + firstName +
                ", lastName: " + lastName +
                ", status: " + status +
                ", description: " + description +
                ", createdAt: " + createdAt +
                "]" + "\n";
    }
}
