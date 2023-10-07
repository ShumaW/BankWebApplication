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

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;
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
        return clientMapper.mapToDto(clientRepository.findById(id)
                .orElseThrow(() -> new NotFoundClientException("Client not found with id " + id)));
    }

    @Override
    public ClientDto createClient(ClientDto clientDto) {
        Manager manager = managerRepository.findById(UUID.fromString(clientDto.getManagerId()))
                .orElseThrow(() -> new NotFoundManagerException("Manager not found with id " + clientDto.getManagerId()));
        Client client = clientMapper.mapToEntity(clientDto);
        client.setManager(manager);
        return clientMapper.mapToDto(clientRepository.save(client));
    }

    @Override
    public void deleteClient(UUID id) {
        clientRepository.deleteById(id);
    }

    @Override
    public ClientDto update(ClientDto clientDto) {
        Manager manager = managerRepository.findById(UUID.fromString(clientDto.getManagerId()))
                .orElseThrow(() -> new NotFoundManagerException("Manager not found with id " + clientDto.getManagerId()));
        Client client = clientRepository.findById(UUID.fromString(clientDto.getId()))
                .orElseThrow(() -> new NotFoundClientException("Client not found with id " + clientDto.getId()));
        client.setManager(manager);
        client.setLastName(clientDto.getLastName());
        client.setStatus(Status.valueOf(clientDto.getStatus()));
        client.setAddress(clientDto.getAddress());
        client.setEmail(clientDto.getEmail());
        client.setPhone(clientDto.getPhone());
        client.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        clientRepository.save(client);
        return clientMapper.mapToDto(client);
    }

    @Override
    public List<ClientDto> getAllClientsWhereStatusIs(Status status) {
        return clientMapper.mapToListDto(clientRepository.findAllClientWhereStatusIs(status));
    }

    @Override
    public List<ClientDto> getAllClientsWhereBalanceMoreThan(BigDecimal sum) {
        return clientMapper.mapToListDto(clientRepository.findAllClientWhereBalanceMoreThan(sum));
    }
}