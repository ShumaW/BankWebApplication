package com.example.bankwebapp.controller;

import com.example.bankwebapp.dto.ClientDto;
import com.example.bankwebapp.entity.Account;
import com.example.bankwebapp.entity.Client;
import com.example.bankwebapp.entity.Manager;
import com.example.bankwebapp.entity.enums.Status;
import com.example.bankwebapp.repository.ClientRepository;
import com.example.bankwebapp.service.interfases.ClientService;
import com.example.bankwebapp.util.CreatorDto;
import com.example.bankwebapp.util.CreatorEntity;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.*;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@WebMvcTest({ClientController.class})
class ClientControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ClientController clientController;

    @MockBean
    private ClientService clientService;

    @MockBean
    private ClientRepository clientRepository;


    @Test
    void getClientByIdTest() throws Exception {
        // given
        ClientDto clientDto = CreatorDto.getClientDto();
        UUID clientId = UUID.fromString(clientDto.getId());
        // when
        when(clientService.getClientById(clientId)).thenReturn(clientDto);
        // then
        mockMvc.perform(get("/auth/clients/" + clientId))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.id", is(clientDto.getId())))
                .andExpect(jsonPath("$.status", is(clientDto.getStatus())))
                .andExpect(jsonPath("$.taxСode", is(clientDto.getTaxСode())))
                .andExpect(jsonPath("$.firstName", is(clientDto.getFirstName())))
                .andExpect(jsonPath("$.lastName", is(clientDto.getLastName())))
                .andExpect(jsonPath("$.email", is(clientDto.getEmail())))
                .andExpect(jsonPath("$.address", is(clientDto.getAddress())))
                .andExpect(jsonPath("$.phone", is(clientDto.getPhone())))
                .andExpect(jsonPath("$.managerId", is(clientDto.getManagerId())))
                .andReturn();
        verify(clientService, times(1)).getClientById(clientId);
    }

    @Test
    void updateClientByIdTest() throws Exception {
        // given
        ClientDto clientDto = CreatorDto.getClientDto();
        UUID clientId = UUID.fromString(clientDto.getId());

        Client client = new Client(clientId,
                Status.valueOf(clientDto.getStatus()),
                clientDto.getTaxСode(),
                clientDto.getFirstName(),
                clientDto.getLastName(),
                clientDto.getEmail(),
                clientDto.getAddress(),
                clientDto.getPhone(),
                new Timestamp(System.currentTimeMillis()),
                new Timestamp(System.currentTimeMillis()),
                Set.of(new Account()),
                new Manager(UUID.fromString("1763f054-5393-11ee-8c99-0242ac120002"))
        );

        ClientDto outputClientDto = new ClientDto(
                client.getId().toString(),
                client.getStatus().toString(),
                client.getTaxСode(),
                client.getFirstName(),
                client.getLastName(),
                client.getEmail(),
                client.getAddress(),
                client.getPhone(),
                client.getManager().getId().toString()
        );
        //when
        when(clientRepository.findById(clientId)).thenReturn(Optional.of(client));
        when(clientService.update(clientDto)).thenReturn(outputClientDto);
        //then
        mockMvc.perform(put("/auth/clients/update")
                        .content(asJsonString(clientDto))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();
        verify(clientService, times(1)).update(clientDto);
    }

    @Test
    void addClientTest() throws Exception {
        // given
        ClientDto clientDto = CreatorDto.getClientDto();
        //when
        when(clientService.createClient(clientDto)).thenReturn(clientDto);
        //then
        mockMvc.perform(post("/auth/clients/add")
                        .content(asJsonString(clientDto))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))

                .andExpect(status().isOk());
    }

    public static String asJsonString(ClientDto clientDto) {
        try {
            return new ObjectMapper().writeValueAsString(clientDto);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void deleteClientTest() throws Exception {
        //given
        Client client = CreatorEntity.getClientOne();
        UUID clientId = client.getId();
        //when
        doNothing().when(clientService).deleteClient(clientId);
        //then
        mockMvc.perform(delete("/auth/clients/delete/" + clientId))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();
        verify(clientService, times(1)).deleteClient(clientId);
    }

    @Test
    void testGetAllClientsWhereStatusIs() throws Exception {
        //given
        List<ClientDto> clientDtoList = new ArrayList<>();
        ClientDto clientDto = CreatorDto.getClientDto();
        Status clientStatus = Status.valueOf(clientDto.getStatus());
        clientDtoList.add(clientDto);
        //when
        when(clientService.getAllClientsWhereStatusIs(clientStatus)).thenReturn(clientDtoList);
        //then
        mockMvc.perform(get("/auth/clients/status/" + clientStatus))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(clientDto.getId())))
                .andExpect(jsonPath("$[0].email", is(clientDto.getEmail())))
                .andExpect(jsonPath("$[0].address", is(clientDto.getAddress())))
                .andReturn();
        verify(clientService, times(1)).getAllClientsWhereStatusIs(clientStatus);
    }

    @Test
    void testGetAllClientsWhereBalanceMoreThan() throws Exception {
        //given
        List<ClientDto> clientDtoList = new ArrayList<>();
        ClientDto clientDto = CreatorDto.getClientDto();
        clientDtoList.add(clientDto);
        BigDecimal sum = new BigDecimal(500);
        //when
        when(clientService.getAllClientsWhereBalanceMoreThan(sum)).thenReturn(clientDtoList);
        //then
        mockMvc.perform(get("/auth/clients/balance_more_than/" + sum))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(clientDto.getId())))
                .andReturn();
        verify(clientService, times(1)).getAllClientsWhereBalanceMoreThan(sum);
    }
}