package com.example.bankwebapp.controller;

import com.example.bankwebapp.dto.TransactionDto;
import com.example.bankwebapp.entity.Transaction;
import com.example.bankwebapp.security.filters.JwtAuthenticationFilter;
import com.example.bankwebapp.service.impl.TransactionServiceImpl;
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

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc(addFilters = false)
@WebMvcTest({TransactionController.class})
class TransactionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    TransactionController transactionController;

    @MockBean
    private TransactionServiceImpl transactionService;

    @MockBean
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @Test
    void testFindAllTransactionsWhereAccountIdIs() throws Exception {
        //given
        List<TransactionDto> transactionDtoList = new ArrayList<>();
        TransactionDto transactionDto = CreatorDto.getTransactionDtoOne();
        transactionDtoList.add(transactionDto);
        Transaction transaction = CreatorEntity.getTransactionOne();
        UUID debitAccountId = transaction.getDebitAccount().getId();
        UUID creditAccountId = transaction.getCreditAccount().getId();
        //when
        when(transactionService.findAllTransactionsWhereAccountIdIs(debitAccountId)).thenReturn(transactionDtoList);
        when(transactionService.findAllTransactionsWhereAccountIdIs(creditAccountId)).thenReturn(transactionDtoList);
        //then
        mockMvc.perform(get("/auth/transactions/all/" + (debitAccountId)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$", hasSize(1)))
                .andReturn();
        mockMvc.perform(get("/auth/transactions/all/" + (creditAccountId)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$", hasSize(1)))
                .andReturn();
        verify(transactionService, times(1)).findAllTransactionsWhereAccountIdIs(debitAccountId);
        verify(transactionService, times(1)).findAllTransactionsWhereAccountIdIs(creditAccountId);

    }

    @Test
    void createNewTransactionTest() throws Exception {
        //given
        TransactionDto transactionDto = CreatorDto.getTransactionDtoOne();
        //when
        when(transactionService.createNewTransaction(transactionDto)).thenReturn(transactionDto);
        //then
        mockMvc.perform(post("/auth/transactions/create")
                        .content(asJsonString(transactionDto))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andReturn();
        verify(transactionService, times(1)).createNewTransaction(transactionDto);
    }

    public static String asJsonString(TransactionDto transactionDto) {
        try {
            return new ObjectMapper().writeValueAsString(transactionDto);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}