package com.example.bankwebapp.dto;

import lombok.Data;

@Data
public class SigninRequest {

    private String email;

    private String password;
}
