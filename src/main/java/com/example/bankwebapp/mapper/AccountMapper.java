package com.example.bankwebapp.mapper;

import com.example.bankwebapp.dto.AccountDto;
import com.example.bankwebapp.entity.Account;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AccountMapper {

    @Mapping(target = "clientId", source = "account.client.id")
    AccountDto mapToDto(Account account);
}
