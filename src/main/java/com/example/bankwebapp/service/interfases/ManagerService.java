package com.example.bankwebapp.service.interfases;

import com.example.bankwebapp.dto.ManagerDto;

import java.util.UUID;

public interface ManagerService {

    ManagerDto getManager(UUID id);

}
