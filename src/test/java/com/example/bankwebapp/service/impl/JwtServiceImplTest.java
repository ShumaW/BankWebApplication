package com.example.bankwebapp.service.impl;

import com.example.bankwebapp.controller.AuthenticationController;
import com.example.bankwebapp.entity.User;
import com.example.bankwebapp.security.filters.JwtAuthenticationFilter;
import com.example.bankwebapp.security.service.AuthenticationService;
import com.example.bankwebapp.security.service.JwtService;
import com.example.bankwebapp.util.CreatorEntity;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UserDetails;

import static org.junit.jupiter.api.Assertions.*;

@AutoConfigureMockMvc(addFilters = false)
@WebMvcTest({AuthenticationController.class})
class JwtServiceImplTest {

    @MockBean
    private UserDetails userDetails;

    @MockBean
    private JwtService jwtService;

    @MockBean
    private AuthenticationService authenticationService;

    @MockBean
    private JwtAuthenticationFilter jwtAuthenticationFilter;


    @Test
    void generateToken() {
        User user = CreatorEntity.getUser();
        String token = jwtService.generateToken(userDetails);
        String outputToken = jwtService.generateToken(user);

        assertEquals(token,outputToken);
    }

}