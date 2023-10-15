package com.example.bankwebapp.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Value;

@Value
@Schema(description = "Product DTO")
public class ProductDto {

    String id;

    @Schema(description = "Product name")
    @NotNull
    @NotBlank
    @Pattern(regexp = "[a-zA-z]", message = "Product name contains invalid characters!")
    @Size(max = 100, message = "Product name can not be longer than 100 characters.")
    String name;

    @Schema(description = "Status of product")
    @NotNull
    @NotBlank
    @Pattern(regexp = "[A-Z]+", message = "Product status can be only ACTIVE, PENDING, BLOCKED or REMOVED.")
    String status;

    @Schema(description = "Currency code of product")
    @NotNull
    @NotBlank
    @Pattern(regexp = "[A-Z]{3}", message = "Product currency code contains invalid characters!")
    @Size(max = 3, min = 3, message = "Product currency code must be exactly 3 characters.")
    String currencyCode;

    @Schema(description = "Products interest rate")
    @NotNull
    @NotBlank
    @Pattern(regexp = "[0-9.]+", message = "Interest rate must be only digit.")
    String interestRate;

    @Schema(description = "Limit of product")
    @NotNull
    @NotBlank
    @Pattern(regexp = "[0-9.]+", message = "Limit must be only digit.")
    String limit;

    @Schema(description = "Manager ID")
    @NotNull
    @NotBlank
    @Pattern(regexp = "[a-z0-9-]+", message = "Manager id contains invalid characters!")
    @Size(max = 36, message = "Manager id must be in UUID format and must be exactly 36 characters.")
    String managerId;
}