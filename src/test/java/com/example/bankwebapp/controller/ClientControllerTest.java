package com.example.bankwebapp.controller;

import com.example.bankwebapp.dto.ClientDto;
import com.example.bankwebapp.service.interfases.ClientService;
import com.example.bankwebapp.util.CreatorDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

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
    void updateClientById() {
    }

    @Test
    void addClient() throws Exception {
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
    void deleteClient() {
    }

    @Test
    void getAllClientsWhereStatusIs() {
    }

    @Test
    void getAllClientsWhereBalanceMoreThan() {
    }
}