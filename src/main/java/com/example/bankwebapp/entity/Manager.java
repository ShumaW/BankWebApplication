package com.example.bankwebapp.entity;

import com.example.bankwebapp.entity.enums.Role;
import com.example.bankwebapp.entity.enums.Status;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "password")
    private String password;

    @Column(name = "email")
    private String email;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(name = "description")
    private String description;

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private Role role = Role.ADMIN;

    @Column(name = "created_at", updatable = false)
    private Timestamp createdAt;

    @OneToMany(cascade = {MERGE, REFRESH, PERSIST}, fetch = FetchType.LAZY, mappedBy = "manager",
            orphanRemoval = true)
    private Set<Client> clientSet;

    @OneToMany(cascade = {MERGE, REFRESH, PERSIST}, fetch = FetchType.LAZY, mappedBy = "manager",
            orphanRemoval = true)
    @JsonIgnore
    private Set<Product> productSet;

    public Manager(UUID managerId) {
        this.id = managerId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Manager manager = (Manager) o;
        return Objects.equals(id, manager.id) && Objects.equals(firstName, manager.firstName) && Objects.equals(lastName, manager.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName);
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