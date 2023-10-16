package com.example.bankwebapp.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.Value;

@Value
@Schema(description = "Client DTO")
public class ClientDto {

    String id;

    @Schema(description = "Status of client")
    @NotNull
    @NotBlank
    @Pattern(regexp = "[A-Z]+", message = "Client status can be only ACTIVE, PENDING, BLOCKED or REMOVED.")
    String status;

    @Schema(description = "Tax code of client", example = "12/345/67890")
    @NotNull
    @NotBlank
    @Pattern(regexp = "[0-9/]+", message = "Tax code of client contains invalid characters.")
    String taxСode;

    @Schema(description = "Client first name")
    @NotNull
    @NotBlank
    @Pattern(regexp = "[a-zA-Z-]+", message = "Client first name contains invalid characters!")
    @Size(max = 100, message = "Client first name can not be longer than 100 characters.")
    String firstName;

    @Schema(description = "Client last name")
    @NotNull
    @NotBlank
    @Pattern(regexp = "[a-zA-Z-]+", message = "Client last name contains invalid characters!")
    @Size(max = 100, message = "Client last name can not be longer than 100 characters.")
    String lastName;

    @Schema(description = "Email of client", example = "example@gmail.com")
    @Email
    @NotNull
    @NotBlank
    String email;

    @Schema(description = "Address of client")
    @NotNull
    @NotBlank
    @Pattern(regexp = "[a-zA-Z0-9 /,]+", message = "Address of client contains invalid characters!")
    @Size(max = 200, message = "Address of client can not be longer than 200 characters.")
    String address;

    @Schema(description = "Phone of client")
    @NotNull
    @NotBlank
    @Pattern(regexp = "[0-9-()]+", message = "Phone of client contains invalid characters!")
    @Size(max = 200, message = "Phone of client can not be longer than 200 characters.")
    String phone;

    @Schema(description = "Manager ID")
    @NotNull
    @NotBlank
    @Pattern(regexp = "[a-z0-9-]+", message = "Manager id contains invalid characters!")
    @Size(max = 36, message = "Manager id must be in UUID format and must be exactly 36 characters.")
    String managerId;
}