package com.example.bankwebapp.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.Value;

@Value
@Schema(description = "Manager DTO")
public class ManagerDto {

    String id;

    @Schema(description = "Manager first name")
    @NotNull
    @NotBlank
    @Pattern(regexp = "[a-zA-Z-]", message = "Manager first name contains invalid characters!")
    @Size(max = 100, message = "Managers first name can not be longer than 100 characters.")
    String firstName;

    @Schema(description = "Manager last name")
    @NotNull
    @NotBlank
    @Pattern(regexp = "[a-zA-Z-]", message = "Manager last name contains invalid characters!")
    @Size(max = 200, message = "Managers last name can not be longer than 200 characters.")
    String lastName;

    @Schema(description = "Status of manager")
    @NotNull
    @NotBlank
    @Pattern(regexp = "[A-Z]+", message = "Manager status can be only ACTIVE, PENDING, BLOCKED or REMOVED.")
    String status;

    @Schema(description = "Managers password")
    @NotNull
    @NotBlank
    @Pattern(regexp = "(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9@#$%]).{8,}",
            message = "The password must contain uppercase and lowercase letters, " +
                    "numbers and special characters and be at least 8 characters long.")
    String password;

    @Schema(description = "Email of manager", example = "example@gmail.com")
    @Email
    @NotNull
    @NotBlank
    String email;
}