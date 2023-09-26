package com.example.bankwebapp.service.interfases;

import com.example.bankwebapp.dto.ClientDto;
import com.example.bankwebapp.entity.Client;

import java.util.Optional;
import java.util.UUID;

public interface ClientService {
    Optional<Client> getClientById(UUID id);

    Client createClient(ClientDto client);

    void deleteClient(UUID id);
}
