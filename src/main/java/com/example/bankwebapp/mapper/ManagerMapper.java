package com.example.bankwebapp.mapper;

import com.example.bankwebapp.dto.ManagerDto;
import com.example.bankwebapp.entity.Manager;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ManagerMapper {

    ManagerDto mapToDto(Manager manager);

    Manager mapToEntity(ManagerDto managerDto);
}
