package com.example.bankwebapp.entity;

import com.example.bankwebapp.entity.enums.ClientStatus;
import lombok.*;

import java.sql.Timestamp;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Client {

    private UUID id;
    private int managerId;
    private ClientStatus status;
    private String taxСode;
    private String firstName;
    private String lastName;
    private String email;
    private String address;
    private String phone;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return Objects.equals(id, client.id) && Objects.equals(taxСode, client.taxСode) && Objects.equals(email, client.email) && Objects.equals(address, client.address) && Objects.equals(phone, client.phone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, taxСode, email, address, phone);
    }

    @Override
    public String toString() {
        return "Client[ " +
                "id: " + id +
                ", firstName: " + firstName +
                ", lastName: " + lastName +
                ", email: " + email +
                ", address: " + address +
                ", phone: " + phone +
                ", status: " + status +
                ", taxСode: " + taxСode +
                ", manager_id: " + managerId +
                ", createdAt: " + createdAt +
                ", updatedAt: " + updatedAt +
                "]" + "\n";
    }
}
