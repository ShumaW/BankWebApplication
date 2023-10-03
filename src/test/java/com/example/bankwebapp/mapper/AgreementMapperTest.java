package com.example.bankwebapp.mapper;

import com.example.bankwebapp.dto.AgreementDto;
import com.example.bankwebapp.entity.Agreement;
import com.example.bankwebapp.util.CreatorDto;
import com.example.bankwebapp.util.CreatorEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class AgreementMapperTest {

    AgreementMapper agreementMapper = new AgreementMapperImpl();

    @Test
    void mapToDtoTest() {
        Agreement agreement = CreatorEntity.getAgreement();
        AgreementDto agreementDto = CreatorDto.getAgreementDto();
        AgreementDto outputAgreementDto = agreementMapper.mapToDto(agreement);

        compareTwoDto(agreementDto, outputAgreementDto);
    }

    @Test
    void mapToListDtoTest() {
        List<Agreement> agreementList = new ArrayList<>();
        agreementList.add(CreatorEntity.getAgreement());
        List<AgreementDto> agreementDtoList = new ArrayList<>();
        agreementDtoList.add(CreatorDto.getAgreementDto());

        List<AgreementDto> outputListAgreementDto = agreementMapper.mapToListDto(agreementList);

        compareTwoListOfAgreementDto(agreementDtoList, outputListAgreementDto);
    }

    private void compareTwoDto(AgreementDto agreementDto, AgreementDto outputAgreementDto){
        assertAll(
                () -> assertEquals(agreementDto.getId(), outputAgreementDto.getId()),
                () -> assertEquals(agreementDto.getAccountId(), outputAgreementDto.getAccountId()),
                () -> assertEquals(agreementDto.getProductId(), outputAgreementDto.getProductId()),
                () -> assertEquals(agreementDto.getInterestRate(), outputAgreementDto.getInterestRate()),
                () -> assertEquals(agreementDto.getStatus(), outputAgreementDto.getStatus()),
                () -> assertEquals(agreementDto.getSum(), outputAgreementDto.getSum())
        );
    }

    private void compareTwoListOfAgreementDto(List<AgreementDto> agreementDtoList, List<AgreementDto> outputAgreementDto){
        assertEquals(agreementDtoList.size(),outputAgreementDto.size());
        for (int i = 0; i < agreementDtoList.size(); i++) {
            compareTwoDto(agreementDtoList.get(i),outputAgreementDto.get(i));
        }
    }
}