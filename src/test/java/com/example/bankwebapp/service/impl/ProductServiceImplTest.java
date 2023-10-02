package com.example.bankwebapp.service.impl;

import com.example.bankwebapp.dto.ProductDto;
import com.example.bankwebapp.entity.Manager;
import com.example.bankwebapp.entity.Product;
import com.example.bankwebapp.mapper.ProductMapper;
import com.example.bankwebapp.repository.ManagerRepository;
import com.example.bankwebapp.repository.ProductRepository;
import com.example.bankwebapp.util.CreatorDto;
import com.example.bankwebapp.util.CreatorEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {

    @InjectMocks
    ProductServiceImpl productService;

    @Mock
    ProductRepository productRepository;

    @Mock
    ProductMapper productMapper;

    @Mock
    ManagerRepository managerRepository;

    @Test
    void updateTest() {
        //given
        Product product = CreatorEntity.getProduct();
        ProductDto productDto = CreatorDto.getProductDto();
        ProductDto outputProductDto = new ProductDto(
                product.getId().toString(),
                product.getName(),
                "REMOVED",
                product.getCurrencyCode().toString(),
                product.getInterestRate().toString(),
                String.valueOf(product.getLimit()),
                product.getManager().getId().toString()
        );
        Manager manager = new Manager(product.getManager().getId());
        //when
        when(managerRepository.findById(product.getManager().getId())).thenReturn(Optional.of(manager));
        when(productRepository.findById(product.getId())).thenReturn(Optional.of(product));
        when(productMapper.mapToDto(product)).thenReturn(outputProductDto);
        //then
        ProductDto updateProduct = productService.update(productDto);
        assertNotEquals(productDto.getStatus(),updateProduct.getStatus());
        verify(productRepository, times(1)).save(product);
    }
}