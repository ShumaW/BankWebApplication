package com.example.bankwebapp.mapper;

import com.example.bankwebapp.dto.AccountDto;
import com.example.bankwebapp.entity.Account;
import com.example.bankwebapp.util.CreatorDto;
import com.example.bankwebapp.util.CreatorEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class AccountMapperTest {

    AccountMapper accountMapper = new AccountMapperImpl();

    @Test
    void mapToDtoTest() {
        Account account = CreatorEntity.getAccountOne();
        AccountDto accountDto = CreatorDto.getAccountDto();

        AccountDto outputAccountDto = accountMapper.mapToDto(account);

        compareTwoDto(accountDto, outputAccountDto);
    }

    @Test
    void mapToListDtoTest() {
        List<AccountDto> accountDtoList = new ArrayList<>();
        accountDtoList.add(CreatorDto.getAccountDto());
        List<Account> accountList = new ArrayList<>();
        accountList.add(CreatorEntity.getAccountOne());

        List<AccountDto> outputListAccountDto = accountMapper.mapToListDto(accountList);

        compareTwoListOfAccountDto(accountDtoList, outputListAccountDto);
    }

    @Test
    void mapToEntityTest(){
        Account account = CreatorEntity.getAccountOne();
        AccountDto accountDto = CreatorDto.getAccountDto();
        Account outputAccount = accountMapper.mapToEntity(accountDto);

        compareToEntity(account, outputAccount);
    }

    private void compareTwoDto(AccountDto accountDto, AccountDto outputAccountDto){
        assertAll(
                () -> assertEquals(accountDto.getId(), outputAccountDto.getId()),
                () -> assertEquals(accountDto.getClientId(), outputAccountDto.getClientId()),
                () -> assertEquals(accountDto.getName(), outputAccountDto.getName()),
                () -> assertEquals(accountDto.getType(), outputAccountDto.getType()),
                () -> assertEquals(accountDto.getStatus(), outputAccountDto.getStatus()),
                () -> assertEquals(accountDto.getBalance(), outputAccountDto.getBalance()),
                () -> assertEquals(accountDto.getCurrencyCode(), outputAccountDto.getCurrencyCode())
        );
    }

    private void compareTwoListOfAccountDto(List<AccountDto> accountDtoList, List<AccountDto> outputListAccountDto){
        assertEquals(accountDtoList.size(),outputListAccountDto.size());
        for (int i = 0; i < accountDtoList.size(); i++) {
            compareTwoDto(accountDtoList.get(i),outputListAccountDto.get(i));
        }
    }

    private void compareToEntity(Account account, Account outputAccount){
        assertAll(
                () -> assertEquals(account.getId(), outputAccount.getId()),
                () -> assertEquals(account.getName(), outputAccount.getName()),
                () -> assertEquals(account.getType(), outputAccount.getType()),
                () -> assertEquals(account.getStatus(), outputAccount.getStatus()),
                () -> assertEquals(account.getBalance(), outputAccount.getBalance()),
                () -> assertEquals(account.getCurrencyCode(), outputAccount.getCurrencyCode())
        );
    }
}