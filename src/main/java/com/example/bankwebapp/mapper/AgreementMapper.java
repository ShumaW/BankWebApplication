package com.example.bankwebapp.mapper;

import com.example.bankwebapp.dto.AgreementDto;
import com.example.bankwebapp.entity.Account;
import com.example.bankwebapp.entity.Agreement;
import com.example.bankwebapp.entity.Product;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring", imports = {Account.class, Product.class},
        uses = {ProductMapper.class, AccountMapper.class})
public interface AgreementMapper {

    @Named("mapAgreementToDto")
    @Mapping(target = "accountId", source = "agreement.account.id")
    @Mapping(target = "productId", source = "agreement.product.id")
    AgreementDto mapToDto(Agreement agreement);

    @IterableMapping(qualifiedByName = "mapAgreementToDto")
    List<AgreementDto> mapToListDto(List<Agreement> agreements);
}