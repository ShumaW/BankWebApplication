package com.example.bankwebapp.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Value;

@Value
@Builder
@Data
@AllArgsConstructor
public class ClientDto {

    String id;

    String status;

    String taxСode;

    String firstName;

    String lastName;

    String email;

    String address;

    String phone;

    String managerId;
}