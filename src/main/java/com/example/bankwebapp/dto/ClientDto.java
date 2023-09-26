package com.example.bankwebapp.dto;

import lombok.Data;

@Data
public class ClientDto {

    String id;

    String status;

    String taxCode;

    String firstName;

    String lastName;

    String email;

    String address;

    String phone;

    String managerId;
}
