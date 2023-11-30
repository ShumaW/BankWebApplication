package com.example.bankwebapp.service.impl;

import com.example.bankwebapp.dto.ProductDto;
import com.example.bankwebapp.entity.Manager;
import com.example.bankwebapp.entity.Product;
import com.example.bankwebapp.entity.enums.Status;
import com.example.bankwebapp.exceptions.NotFoundProductException;
import com.example.bankwebapp.mapper.ProductMapper;
import com.example.bankwebapp.repository.ManagerRepository;
import com.example.bankwebapp.repository.ProductRepository;
import com.example.bankwebapp.service.interfaсes.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    private final ManagerRepository managerRepository;

    private final ProductMapper productMapper;

    /**
     * This public method allows you to update information about a banking product by ID.
     * @param productDto
     * @return productDto
     */
    @Override
    public ProductDto update(ProductDto productDto) {
        log.info("Update product with id {}.", productDto.getId());
        Product product = productRepository.findById(UUID.fromString(productDto.getId()))
                .orElseThrow(() -> new NotFoundProductException("Product not found with id " + productDto.getId()));
        Manager manager = managerRepository.findById(UUID.fromString(productDto.getManagerId()))
                .orElseThrow(() -> new NotFoundProductException("Manager not fount with id " + productDto.getManagerId()));
        product.setManager(manager);
        product.setName(productDto.getName());
        product.setLimit(Double.parseDouble(productDto.getLimit()));
        product.setStatus(Status.valueOf(productDto.getStatus()));
        product.setInterestRate(new BigDecimal(productDto.getInterestRate()));
        product.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        productRepository.save(product);
        return productMapper.mapToDto(product);
    }
}