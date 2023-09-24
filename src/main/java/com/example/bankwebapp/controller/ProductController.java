package com.example.bankwebapp.controller;

import com.example.bankwebapp.entity.Product;
import com.example.bankwebapp.service.interfases.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("auth/products")
public class ProductController {

    private final ProductService productService;
    @PutMapping("/update")
    public ResponseEntity<Product> updateProduct(@RequestBody Product product){
        UUID productId = product.getId();
        Product updatedProduct = productService.addOrUpdateProduct(product);
        boolean isUpdated = updatedProduct.getId().equals(productId);
        return new ResponseEntity<>(updatedProduct, isUpdated ? HttpStatus.OK : HttpStatus.CREATED);
    }
}
