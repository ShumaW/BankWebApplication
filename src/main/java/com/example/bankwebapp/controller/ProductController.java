package com.example.bankwebapp.controller;

import com.example.bankwebapp.dto.ProductDto;
import com.example.bankwebapp.service.interfases.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth/products")
@Tag(name = "Product Controller API")
public class ProductController {

    private final ProductService productService;

    @Operation(summary = "Update product")
    @PutMapping("/update")
    public ProductDto updateProduct(@RequestBody ProductDto productDto){
        return productService.update(productDto);
    }
}