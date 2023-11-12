package com.example.bankwebapp.security.service;

import com.example.bankwebapp.dto.JwtAuthenticationResponse;
import com.example.bankwebapp.dto.RefreshTokenRequest;
import com.example.bankwebapp.dto.SigninRequest;

public interface AuthenticationService {


    JwtAuthenticationResponse signin(SigninRequest signinRequest);

    JwtAuthenticationResponse refreshToken(RefreshTokenRequest refreshTokenRequest);
}