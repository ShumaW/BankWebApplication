package com.example.bankwebapp.controller;

import com.example.bankwebapp.dto.AccountDto;
import com.example.bankwebapp.service.impl.AccountServiceImpl;
import com.example.bankwebapp.util.CreatorDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.*;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;
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

//    @MockBean
//    private DtoConverter dtoConverter;

    @Test
    void getAccountByIdTest() throws Exception {
        // given
        AccountDto accountDto = CreatorDto.getAccountDto();
        UUID accountId = UUID.fromString(accountDto.getId());
        //when
        when(accountService.getAccountById(accountId)).thenReturn(accountDto);
        //then
        mockMvc.perform(MockMvcRequestBuilders.get("/auth/accounts/" + accountId))
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
        List<AccountDto> accountDtoList = new ArrayList<>();
        AccountDto accountDto = CreatorDto.getAccountDto();
        accountDtoList.add(accountDto);

        when(accountService.getAllAccounts()).thenReturn(accountDtoList);

        mockMvc.perform(MockMvcRequestBuilders.get("/auth/accounts/"))
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

//    @Test
//    void updateAccountByIdTest() throws Exception {
//        AccountDto accountDto = CreatorDto.getAccountDto();
//
//        Account account = new Account(UUID.fromString(accountDto.getId()),
//                new Client(UUID.fromString("09b90738-b37c-4035-abdc-550b28f43c33")),
//                accountDto.getName(),
//                AccountType.valueOf(accountDto.getType()),
//                Status.valueOf(accountDto.getStatus()),
//                new BigDecimal(accountDto.getBalance()),
//                Currencies.valueOf(accountDto.getCurrencyCode()),null,
//                null,
//                List.of(new Agreement()),
//                Set.of(new Transaction()), Set.of(new Transaction()));
//
//        AccountDto outpuitAccountDto = new AccountDto(account.getId().toString(),
//                account.getClient().getId().toString(),
//                account.getName(),
//                account.getType().toString(),
//                account.getStatus().toString(),
//                account.getBalance().toString(),
//                account.getCurrencyCode().toString());
//
//        when(dtoConverter.toEntity(accountDto)).thenReturn(account);
//        when(accountService.update(Mockito.any(AccountDto.class))).thenReturn(account);
//        when(dtoConverter.toDto(account)).thenReturn(accountDto);
//
//
//        mockMvc.perform(MockMvcRequestBuilders.get("/auth/accounts/update"))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.id", is(outpuitAccountDto.getId())))
//                .andExpect(jsonPath("$.name", is(outpuitAccountDto.getName())))
//                .andExpect(jsonPath("$.balance", is(outpuitAccountDto.getBalance())))
//                .andDo(print())
//                .andReturn();
//        verify(accountService, times(1)).update(accountDto);
//
//    }

//    @Test
//    void getAllAccountsWhereStatusIs() {
//    }
}