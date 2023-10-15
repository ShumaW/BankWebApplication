package com.example.bankwebapp.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

@Setter
@Value
@Schema(description = "Account DTO")
public class AccountDto {

    String id;

    @Schema(description = "Client ID")
    @NotNull
    @NotBlank
    @Pattern(regexp = "[a-z0-9-]+", message = "Client id contains invalid characters!")
    @Size(max = 36, message = "Client id must be in UUID format and must be exactly 36 characters.")
    String clientId;

    @Schema(description = "Accounts name")
    @Pattern(regexp = "\\w+[^ ]", message = "Account name contains invalid characters!")
    @Size(max = 100, message = "Account name can not be longer than 100 characters.")
    @NotBlank
    @NotNull
    String name;

    @Schema(description = "Type of account", example = "DEPOSIT")
    @NotNull
    @NotBlank
    @Pattern(regexp = "[A-Z]+", message = "Account type can be only DEPOSIT, CREDIT or CURRENT.")
    String type;

    @Schema(description = "Status of account")
    @NotNull
    @NotBlank
    @Pattern(regexp = "[A-Z]+", message = "Account status can be only ACTIVE, PENDING, BLOCKED or REMOVED.")
    String status;

    @Schema(description = "Balance of account")
    @NotNull
    @NotBlank
    @Pattern(regexp = "[0-9.-]+", message = "Accounts balance must be only digit.")
    String balance;

    @Schema(description = "Currency code of account")
    @NotNull
    @NotBlank
    @Pattern(regexp = "[A-Z]{3}", message = "Accounts currency code contains invalid characters!")
    @Size(max = 3, min = 3, message = "Accounts currency code must be exactly 3 characters.")
    String currencyCode;

}