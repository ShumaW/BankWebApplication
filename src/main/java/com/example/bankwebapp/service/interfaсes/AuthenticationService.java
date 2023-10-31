package com.example.bankwebapp.service.interfaсes;

import com.example.bankwebapp.dto.JwtAuthenticationResponse;
import com.example.bankwebapp.dto.RefreshTokenRequest;
import com.example.bankwebapp.dto.SignUpRequest;
import com.example.bankwebapp.dto.SigninRequest;
import com.example.bankwebapp.entity.User;

public interface AuthenticationService {

    User signup(SignUpRequest signUpRequest);

    JwtAuthenticationResponse signin(SigninRequest signinRequest);

    JwtAuthenticationResponse refreshToken(RefreshTokenRequest refreshTokenRequest);
}