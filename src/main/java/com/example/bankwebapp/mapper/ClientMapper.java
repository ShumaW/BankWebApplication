package com.example.bankwebapp.mapper;

import com.example.bankwebapp.dto.ClientDto;
import com.example.bankwebapp.entity.Client;
import com.example.bankwebapp.entity.enums.Status;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.sql.Timestamp;
import java.util.List;

@Mapper(componentModel = "spring", imports = {Timestamp.class})
public interface ClientMapper {


    @Mapping(target = "createdAt", expression = "java(new Timestamp(System.currentTimeMillis()))")
    @Mapping(target = "updatedAt", expression = "java(new Timestamp(System.currentTimeMillis()))")
    @Mapping(target = "status", source = "status", qualifiedByName = "ToUpperCase")
    Client mapToEntity(ClientDto clientDto);

    @Named("ToUpperCase")
    default Status statusToUpperCase(String status) {
        return Status.valueOf(status.toUpperCase());
    }

    @Named("mapClientToDto")
    @Mapping(target = "managerId", source = "client.manager.id")
    ClientDto mapToDto(Client client);

    @IterableMapping(qualifiedByName = "mapClientToDto")
    List<ClientDto> mapToListDto(List<Client> clients);
}