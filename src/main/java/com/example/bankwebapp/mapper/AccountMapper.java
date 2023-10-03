package com.example.bankwebapp.mapper;

import com.example.bankwebapp.dto.AccountDto;
import com.example.bankwebapp.entity.Account;
import com.example.bankwebapp.entity.Client;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", imports = {Client.class}, uses = {AgreementMapper.class})
public interface AccountMapper {

    @Named("mapAccountToDto")
    @Mapping(target = "clientId", source = "account.client.id")
    AccountDto mapToDto(Account account);

    @IterableMapping(qualifiedByName = "mapAccountToDto")
    List<AccountDto> mapToListDto(List<Account> accounts);
}