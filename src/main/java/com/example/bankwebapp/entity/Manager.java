package com.example.bankwebapp.entity;

import com.example.bankwebapp.entity.enums.ManagerStatus;
import lombok.*;

import java.sql.Timestamp;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Manager {
    private UUID id;
    private String firstName;
    private String lastName;
    private ManagerStatus status;
    private String description;
    private Timestamp createdAt;

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
