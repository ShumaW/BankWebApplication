package com.example.bankwebapp.dto;

import lombok.Value;

@Value
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