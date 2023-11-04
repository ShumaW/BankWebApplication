package com.example.bankwebapp.service.impl;

import com.example.bankwebapp.dto.ClientDto;
import com.example.bankwebapp.entity.Client;
import com.example.bankwebapp.entity.Manager;
import com.example.bankwebapp.entity.User;
import com.example.bankwebapp.entity.enums.Status;
import com.example.bankwebapp.exceptions.NotFoundClientException;
import com.example.bankwebapp.exceptions.NotFoundManagerException;
import com.example.bankwebapp.mapper.ClientMapper;
import com.example.bankwebapp.repository.ClientRepository;
import com.example.bankwebapp.repository.ManagerRepository;
import com.example.bankwebapp.repository.UserRepository;
import com.example.bankwebapp.service.interfaсes.ClientService;
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

    private final UserRepository userRepository;

    private final ClientMapper clientMapper;
    @Override
    public ClientDto getClientById(UUID id) {
        log.info("Get client with id {}.", id);
        return clientMapper.mapToDto(clientRepository.findById(id)
                .orElseThrow(() -> new NotFoundClientException("Client not found with id " + id)));
    }

    @Override
    public ClientDto createClient(ClientDto clientDto) {
        log.info("Create new client.");
        Manager manager = managerRepository.findById(UUID.fromString(clientDto.getManagerId()))
                .orElseThrow(() -> new NotFoundManagerException("Manager not found with id " + clientDto.getManagerId()));
        Client client = clientMapper.mapToEntity(clientDto);
        User user = new User();
        user.setEmail(client.getEmail());
        user.setRole(client.getRole());
        user.setPassword(client.getPassword());
        user.setStatus(client.getStatus());
        userRepository.save(user);
        client.setManager(manager);
        return clientMapper.mapToDto(clientRepository.save(client));
    }

    @Override
    public void deleteClient(UUID id) {
        log.info("Delete client with id {}", id);
        clientRepository.deleteById(id);
    }

    @Override
    public ClientDto update(ClientDto clientDto) {
        log.info("Update client with id {}", clientDto.getId());
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
        log.info("Get all clients, where status is {}.", status);
        return clientMapper.mapToListDto(clientRepository.findAllClientWhereStatusIs(status));
    }

    @Override
    public List<ClientDto> getAllClientsWhereBalanceMoreThan(BigDecimal sum) {
        log.info("Get all clients with balance more then {}.", sum);
        return clientMapper.mapToListDto(clientRepository.findAllClientWhereBalanceMoreThan(sum));
    }
}