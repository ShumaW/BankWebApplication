package com.example.bankwebapp.service.impl;

import com.example.bankwebapp.dto.AgreementDto;
import com.example.bankwebapp.entity.Account;
import com.example.bankwebapp.entity.Agreement;
import com.example.bankwebapp.mapper.AgreementMapper;
import com.example.bankwebapp.repository.AgreementRepository;
import com.example.bankwebapp.util.CreatorDto;
import com.example.bankwebapp.util.CreatorEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AgreementServiceImplTest {

    @InjectMocks
    AgreementServiceImpl agreementService;

    @Mock
    AgreementRepository agreementRepository;

    @Mock
    AgreementMapper agreementMapper;

    @Test
    void testFindAgreementsWhereClientIdIs() {
        //given
        List<AgreementDto> agreementDtoList = new ArrayList<>();
        AgreementDto agreementDto = CreatorDto.getAgreementDto();
        agreementDtoList.add(agreementDto);

        List<Agreement> agreementList = new ArrayList<>();
        Agreement agreement = CreatorEntity.getAgreement();
        agreementList.add(agreement);

        Account account = CreatorEntity.getAccountOne();
        UUID clientId = account.getClient().getId();

        //when
        when(agreementRepository.findAllAccountByClientId(clientId)).thenReturn(agreementList);
        when(agreementMapper.mapToListDto(agreementList)).thenReturn(agreementDtoList);

        //then
        List<AgreementDto> agreementsWhereClientIdIs = agreementService.findAgreementsWhereClientIdIs(clientId);

        assertEquals(1, agreementsWhereClientIdIs.size());
        verify(agreementRepository, times(1)).findAllAccountByClientId(clientId);
    }
}