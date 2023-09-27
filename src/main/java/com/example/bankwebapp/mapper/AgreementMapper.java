package com.example.bankwebapp.mapper;


import com.example.bankwebapp.dto.AgreementDto;
import com.example.bankwebapp.entity.Agreement;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring", uses = {ProductMapper.class, AccountMapper.class})
public interface AgreementMapper {

    @Named("mapAgreementToDto")
    @Mapping(target = "accountDto", source = "account", qualifiedByName = "mapAccountToDto")
    @Mapping(target = "productDto", source = "product", qualifiedByName = "mapProductToDto")
    AgreementDto mapToDto(Agreement agreement);

    @IterableMapping(qualifiedByName = "mapAgreementToDto")
    List<AgreementDto> mapToListDto(List<Agreement> agreements);
}