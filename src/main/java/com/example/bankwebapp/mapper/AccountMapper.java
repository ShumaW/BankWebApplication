package com.example.bankwebapp.mapper;

import com.example.bankwebapp.dto.AccountDto;
import com.example.bankwebapp.entity.Account;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AccountMapper {

    AccountDto mapToDto(Account account);

    Account mapToEntity(AccountDto accountDto);

    List<AccountDto> accountsToAccountsDto(List<Account> accounts);
}
