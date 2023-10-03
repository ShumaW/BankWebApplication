package com.example.bankwebapp.mapper;

import com.example.bankwebapp.dto.ProductDto;
import com.example.bankwebapp.entity.Product;
import com.example.bankwebapp.util.CreatorDto;
import com.example.bankwebapp.util.CreatorEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ProductMapperTest {

    ProductMapper productMapper = new ProductMapperImpl();

    @Test
    void mapToDto() {
        Product product = CreatorEntity.getProduct();
        ProductDto productDto = CreatorDto.getProductDto();
        ProductDto outputProductDto = productMapper.mapToDto(product);
        compareTwoDto(productDto, outputProductDto);
    }

    private void compareTwoDto(ProductDto productDto, ProductDto outputProductDto){
        assertAll(
                () -> assertEquals(productDto.getId(), outputProductDto.getId()),
                () -> assertEquals(productDto.getName(), outputProductDto.getName()),
                () -> assertEquals(productDto.getStatus(), outputProductDto.getStatus()),
                () -> assertEquals(productDto.getCurrencyCode(), outputProductDto.getCurrencyCode()),
                () -> assertEquals(productDto.getInterestRate(), outputProductDto.getInterestRate()),
                () -> assertEquals(productDto.getLimit(), outputProductDto.getLimit()),
                () -> assertEquals(productDto.getManagerId(), outputProductDto.getManagerId())
        );
    }
}