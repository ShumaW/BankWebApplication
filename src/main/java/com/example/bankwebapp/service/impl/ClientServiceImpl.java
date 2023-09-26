package com.example.bankwebapp.service.impl;

import com.example.bankwebapp.dto.ClientDto;
import com.example.bankwebapp.entity.Client;
import com.example.bankwebapp.entity.Manager;
import com.example.bankwebapp.entity.enums.Status;
import com.example.bankwebapp.exceptions.NotFoundClientException;
import com.example.bankwebapp.exceptions.NotFoundManagerException;
import com.example.bankwebapp.mapper.ClientMapper;
import com.example.bankwebapp.repository.ClientRepository;
import com.example.bankwebapp.repository.ManagerRepository;
import com.example.bankwebapp.service.interfases.ClientService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;

    private final ManagerRepository managerRepository;

    private final ClientMapper clientMapper;
    @Override
    public ClientDto getClientById(UUID id) {
        return clientMapper.mapToDto(clientRepository.findById(id).orElseThrow(NotFoundClientException::new));
    }

    @Override
    public Client createClient(ClientDto clientDto) {
        Manager manager = managerRepository.findById(UUID.fromString(clientDto.getManagerId()))
                .orElseThrow(NotFoundClientException::new);
        Client client = clientMapper.mapToEntity(clientDto);
        client.setManager(manager);
        return clientRepository.save(client);
    }

    @Override
    public void deleteClient(UUID id) {
        clientRepository.deleteById(id);
    }

    @Override
    public Client update(ClientDto clientDto) {
        Manager manager = managerRepository.findById(UUID.fromString(clientDto.getManagerId()))
                .orElseThrow(NotFoundManagerException::new);
        Client client = clientRepository.findById(UUID.fromString(clientDto.getId()))
                .orElseThrow(NotFoundClientException::new);
        client.setManager(manager);
        client.setLastName(clientDto.getLastName());
        client.setStatus(Status.valueOf(clientDto.getStatus()));
        client.setAddress(clientDto.getAddress());
        client.setEmail(clientDto.getEmail());
        client.setPhone(clientDto.getPhone());
        client.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        clientRepository.save(client);
        return client;
    }
}