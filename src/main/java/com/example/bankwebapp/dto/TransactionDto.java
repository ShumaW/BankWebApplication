package com.example.bankwebapp.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Value;

@Value
@Schema(description = "Transaction DTO")
public class TransactionDto {

    String id;

    @Schema(description = "Recipient ID")
    @NotNull
    @NotBlank
    @Pattern(regexp = "[a-z0-9-]+", message = "Debit id contains invalid characters!")
    @Size(max = 36, message = "Debit id must be in UUID format and must be exactly 36 characters.")
    String debitAccountId;

    @Schema(description = "Payer ID")
    @NotNull
    @NotBlank
    @Pattern(regexp = "[a-z0-9-]+", message = "Credit id contains invalid characters!")
    @Size(max = 36, message = "Credit id must be in UUID format and must be exactly 36 characters.")
    String creditAccountId;

    @Schema(description = "Type of transaction", example = "TRANSFER")
    @NotNull
    @NotBlank
    @Pattern(regexp = "[A-Z]+", message = "Transactions type can be only TRANSFER, PAYMENT, CASH or DEPOSIT.")
    String type;

    @Schema(description = "Amount of transaction")
    @NotNull
    @NotBlank
    @Pattern(regexp = "[0-9.]+", message = "Transactions amount must be only digit.")
    String amount;

    @Schema(description = "Currency code of recipient")
    @NotNull
    @NotBlank
    @Pattern(regexp = "[A-Z]{3}", message = "Transactions currency code contains invalid characters!")
    @Size(max = 3, min = 3, message = "Transactions currency code must be exactly 3 characters.")
    String currencyCode;

    @Schema(description = "Description of payment")
    @Pattern(regexp = "[a-zA-Z -_,.]", message = "Transactions description contains invalid characters!")
    @Size(max = 250, message = "Transactions description can not be longer than 250 characters.")
    String description;
}