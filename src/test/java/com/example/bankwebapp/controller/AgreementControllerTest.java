package com.example.bankwebapp.controller;

import com.example.bankwebapp.dto.AgreementDto;
import com.example.bankwebapp.service.interfaсes.AgreementService;
import com.example.bankwebapp.util.CreatorDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@AutoConfigureMockMvc(addFilters = false)
@WebMvcTest({AgreementController.class})
class AgreementControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    AgreementController agreementController;

    @MockBean
    private AgreementService agreementService;

    @Test
    void testFindAgreementsWhereClientIdIs() throws Exception {
        // given
        AgreementDto agreementDto = CreatorDto.getAgreementDto();
        List<AgreementDto> agreementDtoList = new ArrayList<>();
        agreementDtoList.add(agreementDto);
        UUID agreementId = UUID.fromString(agreementDto.getId());
        //when
        when(agreementService.findAgreementsWhereClientIdIs(agreementId)).thenReturn(agreementDtoList);
        // then
        mockMvc.perform(get("/auth/agreements/client/" + agreementId))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$[0].id", is(agreementDto.getId())))
                .andExpect(jsonPath("$[0].status", is(agreementDto.getStatus())))
                .andExpect(jsonPath("$[0].sum", is(agreementDto.getSum())))
                .andReturn();
        verify(agreementService, times(1)).findAgreementsWhereClientIdIs(agreementId);
    }
}