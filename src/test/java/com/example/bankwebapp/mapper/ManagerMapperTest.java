package com.example.bankwebapp.mapper;

import com.example.bankwebapp.dto.ManagerDto;
import com.example.bankwebapp.entity.Manager;
import com.example.bankwebapp.util.CreatorDto;
import com.example.bankwebapp.util.CreatorEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ManagerMapperTest {

    ManagerMapper managerMapper = new ManagerMapperImpl();

    @Test
    void mapToDtoTest() {
        Manager manager = CreatorEntity.getManager();
        ManagerDto managerDto = CreatorDto.getManagerDto();
        ManagerDto outputManagerDto = managerMapper.mapToDto(manager);
        compareTwoDto(managerDto, outputManagerDto);
    }

    @Test
    void mapToEntityTest(){
        ManagerDto managerDto = CreatorDto.getManagerDto();
        Manager manager = CreatorEntity.getManager();
        Manager outputManager = managerMapper.mapToEntity(managerDto);
        compareTwoEntity(manager, outputManager);
    }

    private void compareTwoDto(ManagerDto managerDto, ManagerDto outputManagerDto){
        assertAll(
                () -> assertEquals(managerDto.getId(), outputManagerDto.getId()),
                () -> assertEquals(managerDto.getStatus(), outputManagerDto.getStatus()),
                () -> assertEquals(managerDto.getLastName(), outputManagerDto.getLastName()),
                () -> assertEquals(managerDto.getFirstName(), outputManagerDto.getFirstName())
        );
    }

    private void compareTwoEntity(Manager manager, Manager outpitManager){
        assertAll(
                () -> assertEquals(manager.getId(), outpitManager.getId()),
                () -> assertEquals(manager.getFirstName(), outpitManager.getFirstName()),
                () -> assertEquals(manager.getLastName(), outpitManager.getLastName()),
                () -> assertEquals(manager.getPassword(), outpitManager.getPassword()),
                () -> assertEquals(manager.getEmail(), outpitManager.getEmail()),
                () -> assertEquals(manager.getStatus(), outpitManager.getStatus())
        );
    }
}