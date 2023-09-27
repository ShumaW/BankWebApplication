package com.example.bankwebapp.service.impl;

import com.example.bankwebapp.dto.AgreementDto;
import com.example.bankwebapp.mapper.AgreementMapper;
import com.example.bankwebapp.repository.AgreementRepository;
import com.example.bankwebapp.service.interfases.AgreementService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class AgreementServiceImpl implements AgreementService {

    private final AgreementRepository agreementRepository;

    private final AgreementMapper agreementMapper;

    @Override
    public List<AgreementDto> findAgreementsWhereClientIdIs(UUID clientId) {
        return agreementMapper.mapToListDto(agreementRepository.findAll().stream()
                .filter(agreement -> agreement.getAccount().getClient().getId().equals(clientId)).toList());
    }
}
