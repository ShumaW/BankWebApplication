package com.example.bankwebapp.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.sql.Timestamp;

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

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    Timestamp createdAt;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    Timestamp updatedAt;

    ManagerDto manager;
}
