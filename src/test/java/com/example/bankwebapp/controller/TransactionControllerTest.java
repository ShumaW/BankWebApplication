package com.example.bankwebapp.controller;

import com.example.bankwebapp.dto.TransactionDto;
import com.example.bankwebapp.entity.Transaction;
import com.example.bankwebapp.service.impl.TransactionServiceImpl;
import com.example.bankwebapp.util.CreatorDto;
import com.example.bankwebapp.util.CreatorEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@WebMvcTest({TransactionController.class})
class TransactionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    TransactionController transactionController;

    @MockBean
    private TransactionServiceImpl transactionService;

    @Test
    void testFindAllTransactionsWhereAccountIdIs() throws Exception {
        //given
        List<TransactionDto> transactionDtoList = new ArrayList<>();
        TransactionDto transactionDto = CreatorDto.getTransactionDto();
        transactionDtoList.add(transactionDto);
        Transaction transaction = CreatorEntity.getTransaction();
        UUID debitAccountId = transaction.getDebitAccountId().getId();
        UUID creditAccountId = transaction.getCreditAccountId().getId();
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
}