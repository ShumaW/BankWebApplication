package com.example.bankwebapp.service.impl;

import com.example.bankwebapp.dto.ManagerDto;
import com.example.bankwebapp.exceptions.NotFoundManagerException;
import com.example.bankwebapp.mapper.ManagerMapper;
import com.example.bankwebapp.repository.ManagerRepository;
import com.example.bankwebapp.service.interfaсes.ManagerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class ManagerServiceImpl implements ManagerService {

    private final ManagerRepository managerRepository;

    private final ManagerMapper managerMapper;

    @Override
    public ManagerDto getManager(UUID id) {
        return managerMapper.mapToDto(managerRepository.findById(id)
                .orElseThrow(() -> new NotFoundManagerException("Manager not found with id " + id)));
    }
}
