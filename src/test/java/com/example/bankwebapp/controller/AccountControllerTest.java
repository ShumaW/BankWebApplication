package com.example.bankwebapp.controller;

import com.example.bankwebapp.dto.AccountDto;
import com.example.bankwebapp.entity.Account;
import com.example.bankwebapp.entity.Agreement;
import com.example.bankwebapp.entity.Client;
import com.example.bankwebapp.entity.Transaction;
import com.example.bankwebapp.entity.enums.AccountType;
import com.example.bankwebapp.entity.enums.Currencies;
import com.example.bankwebapp.entity.enums.Status;
import com.example.bankwebapp.repository.AccountRepository;
import com.example.bankwebapp.service.impl.AccountServiceImpl;
import com.example.bankwebapp.util.CreatorDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.*;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@WebMvcTest({AccountController.class})
class AccountControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    AccountController accountController;

    @MockBean
    private AccountServiceImpl accountService;

    @MockBean
    private AccountRepository accountRepository;

    @Test
    void getAccountByIdTest() throws Exception {
        // given
        AccountDto accountDto = CreatorDto.getAccountDto();
        UUID accountId = UUID.fromString(accountDto.getId());
        //when
        when(accountService.getAccountById(accountId)).thenReturn(accountDto);
        //then
        mockMvc.perform(get("/auth/accounts/" + accountId))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.id", is(accountDto.getId())))
                .andExpect(jsonPath("$.clientId", is(accountDto.getClientId())))
                .andExpect(jsonPath("$.name", is(accountDto.getName())))
                .andExpect(jsonPath("$.type", is(accountDto.getType())))
                .andExpect(jsonPath("$.status", is(accountDto.getStatus())))
                .andExpect(jsonPath("$.balance", is(accountDto.getBalance())))
                .andExpect(jsonPath("$.currencyCode", is(accountDto.getCurrencyCode())))
                .andReturn();
        verify(accountService, times(1)).getAccountById(accountId);
    }

    @Test
    void getAllAccountsTest() throws Exception {
        // given
        List<AccountDto> accountDtoList = new ArrayList<>();
        AccountDto accountDto = CreatorDto.getAccountDto();
        accountDtoList.add(accountDto);
        //when
        when(accountService.getAllAccounts()).thenReturn(accountDtoList);
        //then
        mockMvc.perform(get("/auth/accounts/"))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(accountDto.getId())))
                .andExpect(jsonPath("$[0].clientId", is(accountDto.getClientId())))
                .andExpect(jsonPath("$[0].name", is(accountDto.getName())))
                .andExpect(jsonPath("$[0].type", is(accountDto.getType())))
                .andExpect(jsonPath("$[0].status", is(accountDto.getStatus())))
                .andExpect(jsonPath("$[0].balance", is(accountDto.getBalance())))
                .andExpect(jsonPath("$[0].currencyCode", is(accountDto.getCurrencyCode())))
                .andReturn();
        verify(accountService, times(1)).getAllAccounts();
    }

    @Test
    void getAllAccountsWhereStatusIs() throws Exception {
        // given
        List<AccountDto> accountDtoList = new ArrayList<>();
        AccountDto accountDto = CreatorDto.getAccountDto();
        accountDtoList.add(accountDto);
        Status status = Status.valueOf(accountDto.getStatus());
        // when
        when(accountService.getAllAccountsWhereStatusIs(status)).thenReturn(accountDtoList);
        // then
        mockMvc.perform(get("/auth/accounts/status/" + status))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id", is(accountDto.getId())))
                .andExpect(jsonPath("$[0].status", is(accountDto.getStatus())))
                .andExpect(jsonPath("$[0].balance", is(accountDto.getBalance())))
                .andDo(print())
                .andReturn();
        verify(accountService, times(1)).getAllAccountsWhereStatusIs(status);
    }

    @Test
    void updateAccountByIdTest() throws Exception {
        // given
        AccountDto accountDto = CreatorDto.getAccountDto();
        UUID accountId = UUID.fromString(accountDto.getId());

        Account account = new Account(accountId,
                new Client(UUID.fromString("09b90738-b37c-4035-abdc-550b28f43c33")),
                accountDto.getName(),
                AccountType.valueOf(accountDto.getType()),
                Status.valueOf(accountDto.getStatus()),
                new BigDecimal(accountDto.getBalance()),
                Currencies.valueOf(accountDto.getCurrencyCode()),null,
                null,
                List.of(new Agreement()),
                Set.of(new Transaction()), Set.of(new Transaction()));

        AccountDto outpuitAccountDto = new AccountDto(account.getId().toString(),
                account.getClient().getId().toString(),
                account.getName(),
                account.getType().toString(),
                account.getStatus().toString(),
                account.getBalance().toString(),
                account.getCurrencyCode().toString());
        //when
        when(accountRepository.findById(accountId)).thenReturn(Optional.of(account));
        when(accountService.update(accountDto)).thenReturn(outpuitAccountDto);

        //then
        mockMvc.perform(put("/auth/accounts/update")
                        .content(asJsonString(outpuitAccountDto))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();
        verify(accountService, times(1)).update(accountDto);
    }

    public static String asJsonString(AccountDto accountDto) {
        try {
            return new ObjectMapper().writeValueAsString(accountDto);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}