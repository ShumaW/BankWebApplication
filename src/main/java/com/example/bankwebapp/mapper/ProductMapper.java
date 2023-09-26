package com.example.bankwebapp.mapper;

import com.example.bankwebapp.dto.ProductDto;
import com.example.bankwebapp.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    @Mapping(target = "managerId", source = "product.manager.id")
    ProductDto mapToDto(Product product);
}
