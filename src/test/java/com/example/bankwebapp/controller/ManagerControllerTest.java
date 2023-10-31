package com.example.bankwebapp.controller;

import com.example.bankwebapp.dto.ManagerDto;
import com.example.bankwebapp.security.config.JwtAuthenticationFilter;
import com.example.bankwebapp.service.interfaсes.ManagerService;
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

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc(addFilters = false)
@WebMvcTest({ManagerController.class})
class ManagerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ManagerController managerController;

    @MockBean
    ManagerService managerService;

    @MockBean
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @Test
    void managerByIdTest() throws Exception {
        //given
        ManagerDto managerDto = CreatorDto.getManagerDto();
        UUID managerId = UUID.fromString(managerDto.getId());
        //when
        when(managerService.getManager(managerId)).thenReturn(managerDto);
        //then
        mockMvc.perform(get("/auth/managers/" + managerId))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();
        verify(managerService, times(1)).getManager(managerId);
    }

    @Test
    void createNewManagerTest() throws Exception {
        //given
        ManagerDto managerDto = CreatorDto.getManagerDto();
        //when
        when(managerService.createManager(managerDto)).thenReturn(managerDto);
        //then
        mockMvc.perform(post("/auth/managers/create")
                .content(asJsonString(managerDto))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful());
        verify(managerService, times(1)).createManager(managerDto);
    }

    public static String asJsonString(ManagerDto managertDto) {
        try {
            return new ObjectMapper().writeValueAsString(managertDto);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}