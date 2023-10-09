package com.example.bankwebapp.service.impl;

import com.example.bankwebapp.dto.ClientDto;
import com.example.bankwebapp.entity.Client;
import com.example.bankwebapp.entity.Manager;
import com.example.bankwebapp.exceptions.NotFoundClientException;
import com.example.bankwebapp.mapper.ClientMapper;
import com.example.bankwebapp.repository.ClientRepository;
import com.example.bankwebapp.repository.ManagerRepository;
import com.example.bankwebapp.util.CreatorDto;
import com.example.bankwebapp.util.CreatorEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ClientServiceImplTest {

    @InjectMocks
    ClientServiceImpl clientService;

    @Mock
    ClientRepository clientRepository;

    @Mock
    ClientMapper clientMapper;

    @Mock
    ManagerRepository managerRepository;

    @Test
    void getClientByIdTest() {
        //given
        Client client = CreatorEntity.getClientOne();
        UUID clientId = client.getId();
        ClientDto clientDto = CreatorDto.getClientDto();
        //when
        when(clientRepository.findById(clientId)).thenReturn(Optional.of(client));
        when(clientMapper.mapToDto(client)).thenReturn(clientDto);
        //then
        ClientDto clientById = clientService.getClientById(clientId);
        assertEquals(client.getPhone(), clientById.getPhone());
        assertLinesMatch(String.valueOf(client.getId()).lines(), clientById.getId().lines());
        verify(clientRepository, times(1)).findById(clientId);
    }

    @Test
    void notFoundClientExceptionTest() {
        UUID randomId = UUID.randomUUID();
        String expected = "Client not found with id " + randomId;
        Exception exception = assertThrows(NotFoundClientException.class, () -> clientService.getClientById(randomId));
        assertEquals(expected, exception.getMessage());
    }

    @Test
    void createClientTest() {
        //given
        Client client = CreatorEntity.getClientOne();
        ClientDto clientDto = CreatorDto.getClientDto();
        Manager manager = CreatorEntity.getManager();
        //when
        when(managerRepository.findById(client.getManager().getId())).thenReturn(Optional.of(manager));
        when(clientRepository.save(client)).thenReturn(client);
        when(clientMapper.mapToEntity(clientDto)).thenReturn(client);
        when(clientMapper.mapToDto(client)).thenReturn(clientDto);
        //then
        ClientDto clientServiceClient = clientService.createClient(clientDto);
        assertNotNull(clientServiceClient);
        assertEquals(clientDto, clientServiceClient);
        verify(clientRepository, times(1)).save(client);
    }

    @Test
    void deleteClientTest() {
        //given
        Client client = CreatorEntity.getClientOne();
        //when
        doNothing().when(clientRepository).deleteById(client.getId());
        //then
        clientService.deleteClient(client.getId());
        verify(clientRepository, times(1)).deleteById(client.getId());
    }

    @Test
    void updateTest() {
        //given
        Client client = CreatorEntity.getClientOne();
        ClientDto clientDto = CreatorDto.getClientDto();
        ClientDto outputClientDto = new ClientDto(
                client.getId().toString(),
                "BLOCKED",
                client.getTaxСode(),
                client.getFirstName(),
                client.getLastName(),
                client.getEmail(),
                client.getAddress(),
                client.getPhone(),
                client.getManager().getId().toString()
                );
        Manager manager = new Manager(client.getManager().getId());
        //when
        when(managerRepository.findById(client.getManager().getId())).thenReturn(Optional.of(manager));
        when(clientRepository.findById(client.getId())).thenReturn(Optional.of(client));
        when(clientMapper.mapToDto(client)).thenReturn(outputClientDto);
        //then
        ClientDto update = clientService.update(clientDto);
        assertEquals(client.getEmail(), update.getEmail());
        assertEquals(client.getTaxСode(), update.getTaxСode());
        verify(clientRepository,times(1)).save(client);
    }

    @Test
    void testGetAllClientsWhereStatusIs() {
        //given
        List<ClientDto> clientDtoList = new ArrayList<>();
        clientDtoList.add(CreatorDto.getClientDto());
        List<Client> clientList = new ArrayList<>();
        Client client = CreatorEntity.getClientOne();
        clientList.add(client);
        //when
        when(clientRepository.findAllClientWhereStatusIs(client.getStatus())).thenReturn(clientList);
        when(clientMapper.mapToListDto(clientList)).thenReturn(clientDtoList);
        //then
        List<ClientDto> allClientsWhereStatusIs = clientService.getAllClientsWhereStatusIs(client.getStatus());
        assertEquals(1,allClientsWhereStatusIs.size());
        assertNotNull(allClientsWhereStatusIs);
        verify(clientRepository, times(1)).findAllClientWhereStatusIs(client.getStatus());
    }

    @Test
    void testGetAllClientsWhereBalanceMoreThan() {
        //given
        List<Client> clientList = new ArrayList<>();
        Client client = CreatorEntity.getClientOne();
        clientList.add(client);
        List<ClientDto> clientDtoList = new ArrayList<>();
        ClientDto clientDto = CreatorDto.getClientDto();
        clientDtoList.add(clientDto);
        BigDecimal sum = new BigDecimal(500);
        //when
        when(clientRepository.findAllClientWhereBalanceMoreThan(sum)).thenReturn(clientList);
        when(clientMapper.mapToListDto(clientList)).thenReturn(clientDtoList);
        //then
        List<ClientDto> allClientsWhereBalanceMoreThan = clientService.getAllClientsWhereBalanceMoreThan(sum);
        assertNotNull(allClientsWhereBalanceMoreThan);
        assertEquals(1, allClientsWhereBalanceMoreThan.size());
        verify(clientRepository, times(1)).findAllClientWhereBalanceMoreThan(sum);
    }
}