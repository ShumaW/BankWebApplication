package com.example.bankwebapp.controller;

import com.example.bankwebapp.dto.ProductDto;
import com.example.bankwebapp.entity.Product;
import com.example.bankwebapp.service.interfases.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("auth/products")
public class ProductController {

    private final ProductService productService;

    @PutMapping("/update")
    public Product updateProductById(@RequestBody ProductDto productDto){
        return productService.update(productDto);
    }
}
