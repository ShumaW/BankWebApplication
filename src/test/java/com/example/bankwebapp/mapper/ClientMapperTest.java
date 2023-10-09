package com.example.bankwebapp.mapper;

import com.example.bankwebapp.dto.ClientDto;
import com.example.bankwebapp.entity.Client;
import com.example.bankwebapp.util.CreatorDto;
import com.example.bankwebapp.util.CreatorEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ClientMapperTest {

    ClientMapper clientMapper = new ClientMapperImpl();

    @Test
    void mapToEntityTest() {
        ClientDto clientDto = CreatorDto.getClientDto();
        Client client = CreatorEntity.getClientOne();
        Client outputClient = clientMapper.mapToEntity(clientDto);

        compareTwoEntity(client, outputClient);
    }
    @Test
    void mapToDtoTest() {
        Client client = CreatorEntity.getClientOne();
        ClientDto clientDto = CreatorDto.getClientDto();
        ClientDto outputClientDto = clientMapper.mapToDto(client);

        compareTwoDto(clientDto, outputClientDto);
    }

    @Test
    void mapToListDtoTest() {
        List<Client> clientList = new ArrayList<>();
        clientList.add(CreatorEntity.getClientOne());
        List<ClientDto> clientDtoList = new ArrayList<>();
        clientDtoList.add(CreatorDto.getClientDto());

        List<ClientDto> outputListClientDto = clientMapper.mapToListDto(clientList);

        compareTwoListOfClientDto(clientDtoList, outputListClientDto);

    }

    private void compareTwoEntity(Client client, Client outputClient){
        assertAll(
                () -> assertEquals(client.getId(), outputClient.getId()),
                () -> assertEquals(client.getStatus(), outputClient.getStatus()),
                () -> assertEquals(client.getTaxСode(), outputClient.getTaxСode()),
                () -> assertEquals(client.getFirstName(), outputClient.getFirstName()),
                () -> assertEquals(client.getLastName(), outputClient.getLastName()),
                () -> assertEquals(client.getEmail(), outputClient.getEmail()),
                () -> assertEquals(client.getAddress(), outputClient.getAddress()),
                () -> assertEquals(client.getPhone(), outputClient.getPhone())
        );
    }

    private void compareTwoDto(ClientDto clientDto, ClientDto outputClientDto){
        assertAll(
                () -> assertEquals(clientDto.getId(), outputClientDto.getId()),
                () -> assertEquals(clientDto.getStatus(), outputClientDto.getStatus()),
                () -> assertEquals(clientDto.getTaxСode(), outputClientDto.getTaxСode()),
                () -> assertEquals(clientDto.getFirstName(), outputClientDto.getFirstName()),
                () -> assertEquals(clientDto.getLastName(), outputClientDto.getLastName()),
                () -> assertEquals(clientDto.getEmail(), outputClientDto.getEmail()),
                () -> assertEquals(clientDto.getAddress(), outputClientDto.getAddress()),
                () -> assertEquals(clientDto.getPhone(), outputClientDto.getPhone()),
                () -> assertEquals(clientDto.getManagerId(), outputClientDto.getManagerId())
        );
    }

    private void compareTwoListOfClientDto(List<ClientDto> clientDtoList, List<ClientDto> outputAgreementDtoList){
        assertEquals(clientDtoList.size(),outputAgreementDtoList.size());
        for (int i = 0; i < clientDtoList.size(); i++) {
            compareTwoDto(clientDtoList.get(i),outputAgreementDtoList.get(i));
        }
    }
}