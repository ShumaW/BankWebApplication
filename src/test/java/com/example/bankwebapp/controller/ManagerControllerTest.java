package com.example.bankwebapp.controller;

import com.example.bankwebapp.dto.ManagerDto;
import com.example.bankwebapp.service.interfases.ManagerService;
import com.example.bankwebapp.util.CreatorDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@WebMvcTest({ManagerController.class})
class ManagerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ManagerController managerController;

    @MockBean
    ManagerService managerService;

    @Test
    void managerByIdTest() throws Exception {
        //given
        ManagerDto managerDto = CreatorDto.getManagerDto();
        UUID managerId = UUID.fromString(managerDto.getId());
        //when
        when(managerService.getManager(managerId)).thenReturn(managerDto);
        //then
        mockMvc.perform(get("/auth/managers/all/" + managerId))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();
        verify(managerService, times(1)).getManager(managerId);
    }
}