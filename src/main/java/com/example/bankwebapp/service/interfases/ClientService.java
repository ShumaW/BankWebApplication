package com.example.bankwebapp.service.interfases;

import com.example.bankwebapp.dto.ClientDto;
import com.example.bankwebapp.entity.Client;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public interface ClientService {
    ClientDto getClientById(UUID id);

    Client createClient(ClientDto client);

    void deleteClient(UUID id);

    Client update(ClientDto clientDto);

    List<ClientDto> getAllClientsWhereStatusIs(String status);


    List<ClientDto> getAllClientsWhereBalanceMoreThan(BigDecimal sum);
}