package com.example.bankwebapp.mapper;

import com.example.bankwebapp.dto.ProductDto;
import com.example.bankwebapp.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    @Named("mapProductToDto")
    @Mapping(target = "managerId", source = "product.manager.id")
    ProductDto mapToDto(Product product);
}