package com.example.bankwebapp.service.impl;

import com.example.bankwebapp.entity.Product;
import com.example.bankwebapp.repository.ProductRepository;
import com.example.bankwebapp.service.interfases.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public Product addOrUpdateProduct(Product product) {
        return productRepository.save(product);
    }
}
