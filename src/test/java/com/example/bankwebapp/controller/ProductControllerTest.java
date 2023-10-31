package com.example.bankwebapp.controller;

import com.example.bankwebapp.dto.ProductDto;
import com.example.bankwebapp.entity.Agreement;
import com.example.bankwebapp.entity.Manager;
import com.example.bankwebapp.entity.Product;
import com.example.bankwebapp.entity.enums.Currencies;
import com.example.bankwebapp.entity.enums.Status;
import com.example.bankwebapp.security.config.JwtAuthenticationFilter;
import com.example.bankwebapp.service.impl.ProductServiceImpl;
import com.example.bankwebapp.util.CreatorDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Set;
import java.util.UUID;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc(addFilters = false)
@WebMvcTest({ProductController.class})
class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ProductController productController;

    @MockBean
    private ProductServiceImpl productService;

    @MockBean
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @Test
    void updateProductByIdTest() throws Exception {
        //given
        ProductDto productDto = CreatorDto.getProductDto();
        UUID productId = UUID.fromString(productDto.getId());
        Product product = new Product(
                productId,
                productDto.getName(),
                Status.valueOf(productDto.getStatus()),
                Currencies.valueOf(productDto.getCurrencyCode()),
                new BigDecimal(productDto.getInterestRate()),
                Double.parseDouble(productDto.getLimit()),
                new Timestamp(System.currentTimeMillis()),
                new Timestamp(System.currentTimeMillis()),
                new Manager(UUID.fromString("1763f054-5393-11ee-8c99-0242ac120002")),
                Set.of(new Agreement())
        );

        ProductDto outputProductDto = new ProductDto(
                product.getId().toString(),
                product.getName(),
                product.getStatus().toString(),
                product.getCurrencyCode().toString(),
                product.getInterestRate().toString(),
                String.valueOf(product.getLimit()),
                product.getManager().getId().toString()
        );
        //when
        when(productService.update(productDto)).thenReturn(outputProductDto);
        //then
        mockMvc.perform(put("/auth/products/update")
                        .content(asJsonString(outputProductDto))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();
        verify(productService, times(1)).update(productDto);
    }



    public static String asJsonString(ProductDto productDto) {
        try {
            return new ObjectMapper().writeValueAsString(productDto);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}