package com.example.bankwebapp.service.interfases;

import com.example.bankwebapp.entity.Client;

import java.util.Optional;
import java.util.UUID;

public interface ClientService {
    Optional<Client> getClientById(UUID id);

    Client addOrUpdateClient(Client client, String managerId);

    void deleteClient(UUID id);
}
