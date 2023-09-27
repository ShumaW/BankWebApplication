package com.example.bankwebapp.mapper;

import com.example.bankwebapp.dto.AccountDto;
import com.example.bankwebapp.entity.Account;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AccountMapper {

    @Named("mapAccountToDto")
    @Mapping(target = "clientId", source = "account.client.id")
    AccountDto mapToDto(Account account);

    @IterableMapping(qualifiedByName = "mapAccountToDto")
    List<AccountDto> mapToListDto(List<Account> accounts);
}
