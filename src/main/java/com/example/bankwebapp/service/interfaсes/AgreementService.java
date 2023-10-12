package com.example.bankwebapp.service.interfaсes;

import com.example.bankwebapp.dto.AgreementDto;

import java.util.List;
import java.util.UUID;

public interface AgreementService {
    List<AgreementDto> findAgreementsWhereClientIdIs(UUID clientId);
}