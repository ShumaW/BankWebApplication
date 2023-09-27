package com.example.bankwebapp.service.interfases;

import com.example.bankwebapp.dto.ProductDto;
import com.example.bankwebapp.entity.Product;

public interface ProductService {
    Product update(ProductDto productDto);
}