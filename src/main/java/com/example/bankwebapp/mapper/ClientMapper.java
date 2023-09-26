package com.example.bankwebapp.mapper;

import com.example.bankwebapp.dto.ClientDto;
import com.example.bankwebapp.entity.Client;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ClientMapper {

    Client mapToEntity(ClientDto clientDto);
}
