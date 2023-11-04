package com.example.bankwebapp.service.impl;

import com.example.bankwebapp.dto.ManagerDto;
import com.example.bankwebapp.entity.Manager;
import com.example.bankwebapp.entity.User;
import com.example.bankwebapp.exceptions.NotFoundManagerException;
import com.example.bankwebapp.mapper.ManagerMapper;
import com.example.bankwebapp.repository.ManagerRepository;
import com.example.bankwebapp.repository.UserRepository;
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

    private final UserRepository userRepository;

    @Override
    public ManagerDto getManager(UUID id) {
        log.info("Get manager with id {}.", id);
        return managerMapper.mapToDto(managerRepository.findById(id)
                .orElseThrow(() -> new NotFoundManagerException("Manager not found with id " + id)));
    }

    @Override
    public ManagerDto createManager(ManagerDto managerDto) {
        log.info("Create new manager.");
        Manager manager = managerMapper.mapToEntity(managerDto);
        User user = new User();
        user.setEmail(manager.getEmail());
        user.setRole(manager.getRole());
        user.setPassword(manager.getPassword());
        user.setStatus(manager.getStatus());
        userRepository.save(user);
        return managerMapper.mapToDto(managerRepository.save(manager));

    }
}
