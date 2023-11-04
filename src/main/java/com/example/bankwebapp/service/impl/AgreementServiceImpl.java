package com.example.bankwebapp.service.impl;

import com.example.bankwebapp.dto.AgreementDto;
import com.example.bankwebapp.mapper.AgreementMapper;
import com.example.bankwebapp.repository.AgreementRepository;
import com.example.bankwebapp.service.interfaсes.AgreementService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class AgreementServiceImpl implements AgreementService {

    private final AgreementRepository agreementRepository;

    private final AgreementMapper agreementMapper;

    @Override
    @Transactional
    public List<AgreementDto> findAgreementsWhereClientIdIs(UUID clientId) {
        log.info("Get agreement, where client id is {}.", clientId);
        return agreementMapper.mapToListDto(agreementRepository.findAllAccountByClientId(clientId));
    }
}