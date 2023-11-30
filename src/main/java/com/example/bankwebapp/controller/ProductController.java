package com.example.bankwebapp.controller;

import com.example.bankwebapp.dto.ProductDto;
import com.example.bankwebapp.service.interfaсes.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth/products")
@Tag(name = "Product Controller API")
public class ProductController {

    private final ProductService productService;

    @Operation(summary = "Update product by id")
    @PutMapping("/update")
    @SecurityRequirement(name = "JWT")
    @PreAuthorize("hasAuthority('user:write')")
    public ResponseEntity<ProductDto> updateProduct(@RequestBody ProductDto productDto){
        return new ResponseEntity<>(productService.update(productDto), HttpStatus.OK);
    }
}