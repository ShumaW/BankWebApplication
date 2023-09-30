package com.example.bankwebapp.util;

import com.example.bankwebapp.dto.*;
import com.example.bankwebapp.entity.*;
import lombok.experimental.UtilityClass;

@UtilityClass
public class CreatorDto {

    public static AccountDto getAccountDto() {
        Account account = CreatorEntity.getAccount();
        return new AccountDto(
                account.getId().toString(),
                getClientDto().getId(),
                account.getName(),
                String.valueOf(account.getType()),
                String.valueOf(account.getStatus()),
                String.valueOf(account.getBalance()),
                String.valueOf(account.getCurrencyCode()));
    }

    public static ClientDto getClientDto() {
        Client client = CreatorEntity.getClient();
        return new ClientDto(
                String.valueOf(client.getId()),
                String.valueOf(client.getStatus()),
                client.getTaxСode(),
                client.getFirstName(),
                client.getLastName(),
                client.getEmail(),
                client.getAddress(),
                client.getPhone(),
                getManagerDto().getId()
        );
    }

    public static ManagerDto getManagerDto() {
        Manager manager = CreatorEntity.getManager();
        return new ManagerDto(
              manager.getId().toString(),
              manager.getFirstName(),
              manager.getLastName(),
              String.valueOf(manager.getStatus())
        );
    }
}
