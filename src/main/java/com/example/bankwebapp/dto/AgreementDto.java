package com.example.bankwebapp.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Setter;
import lombok.Value;

@Setter
@Value
@Schema(description = "Agreement DTO")
public class AgreementDto {

    String id;

    @Schema(description = "Account ID")
    @NotNull
    @NotBlank
    @Pattern(regexp = "[a-z0-9-]+", message = "Account id contains invalid characters!")
    @Size(max = 36, message = "Account id must be in UUID format and must be exactly 36 characters.")
    String accountId;

    @Schema(description = "Product ID")
    @NotNull
    @NotBlank
    @Pattern(regexp = "[a-z0-9-]+", message = "Product id contains invalid characters!")
    @Size(max = 36, message = "Product id must be in UUID format and must be exactly 36 characters.")
    String productId;

    @Schema(description = "Products interest rate")
    @NotNull
    @NotBlank
    @Pattern(regexp = "[0-9.]+", message = "Interest rate must be only digit.")
    String interestRate;

    @Schema(description = "Status of agreement")
    @NotNull
    @NotBlank
    @Pattern(regexp = "[A-Z]+", message = "Agreement status can be only ACTIVE, PENDING, BLOCKED or REMOVED.")
    String status;

    @Schema(description = "Agreement sum")
    @NotNull
    @NotBlank
    @Pattern(regexp = "[0-9.]+", message = "Agreement sum must be only digit.")
    String sum;
}