package com.example.bankwebapp.service.impl;

import com.example.bankwebapp.entity.Client;
import com.example.bankwebapp.entity.Manager;
import com.example.bankwebapp.repository.ClientRepository;
import com.example.bankwebapp.repository.ManagerRepository;
import com.example.bankwebapp.service.interfases.ClientService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;

    private final ManagerRepository managerRepository;
    @Override
    public Optional<Client> getClientById(UUID id) {
        return clientRepository.findById(id);
    }

    @Override
    public Client addOrUpdateClient(Client client, String managerId) {
        Manager manager = managerRepository.findById(UUID.fromString(managerId)).orElse(null);
        client.setManager(manager);
        return clientRepository.save(client);
    }
}