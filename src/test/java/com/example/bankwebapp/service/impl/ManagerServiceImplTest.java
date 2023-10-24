package com.example.bankwebapp.service.impl;

import com.example.bankwebapp.dto.ManagerDto;
import com.example.bankwebapp.entity.Manager;
import com.example.bankwebapp.exceptions.NotFoundManagerException;
import com.example.bankwebapp.mapper.ManagerMapper;
import com.example.bankwebapp.repository.ManagerRepository;
import com.example.bankwebapp.repository.UserRepository;
import com.example.bankwebapp.util.CreatorDto;
import com.example.bankwebapp.util.CreatorEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class ManagerServiceImplTest {

    @InjectMocks
    ManagerServiceImpl managerService;

    @Mock
    ManagerRepository managerRepository;

    @Mock
    ManagerMapper managerMapper;

    @Mock
    UserRepository userRepository;

    @Test
    void getManagerByIdTest(){
        Manager manager = CreatorEntity.getManager();
        UUID managerId = manager.getId();
        ManagerDto managerDto = CreatorDto.getManagerDto();

        when(managerRepository.findById(managerId)).thenReturn(Optional.of(manager));
        when(managerMapper.mapToDto(manager)).thenReturn(managerDto);

        ManagerDto managerById = managerService.getManager(managerId);
        assertNotNull(status().isOk());
        assertEquals(managerDto, managerById);
        verify(managerRepository, times(1)).findById(managerId);
    }

    @Test
    void notFoundManagerExceptionTest(){
        UUID random = UUID.randomUUID();
        String expected = "Manager not found with id " + random;
        Exception exception = assertThrows(NotFoundManagerException.class, () -> managerService.getManager(random));
        assertEquals(expected,exception.getMessage());
    }

    @Test
    void createNewManagerTest(){
        //given
        Manager manager = CreatorEntity.getManager();
        ManagerDto managerDto = CreatorDto.getManagerDto();
        //when
        when(managerRepository.save(manager)).thenReturn(manager);
        when(managerMapper.mapToEntity(managerDto)).thenReturn(manager);
        when(managerMapper.mapToDto(manager)).thenReturn(managerDto);
        //then
        ManagerDto outputManager = managerService.createManager(managerDto);
        assertNotNull(outputManager);
        assertEquals(managerDto, outputManager);
        verify(managerRepository, times(1)).save(manager);
    }
}