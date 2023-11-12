package com.example.bankwebapp.controller;

import com.example.bankwebapp.dto.SigninRequest;
import com.example.bankwebapp.entity.User;
import com.example.bankwebapp.security.filters.JwtAuthenticationFilter;
import com.example.bankwebapp.security.service.AuthenticationService;
import com.example.bankwebapp.security.service.JwtService;
import com.example.bankwebapp.util.CreatorEntity;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc(addFilters = false)
@WebMvcTest({AuthenticationController.class})
class AuthenticationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    AuthenticationController authenticationController;

    @MockBean
    private AuthenticationService authenticationService;

    @MockBean
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @MockBean
    private JwtService jwtService;

    @Test
    void signin() throws Exception {
        User user = CreatorEntity.getUser();

        SigninRequest signinRequest = new SigninRequest();
        signinRequest.setEmail(user.getEmail());
        signinRequest.setPassword(user.getPassword());

        mockMvc.perform(post("/auth/signin")
                        .content(asJsonString(user))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful());
        verify(authenticationService, times(1)).signin(signinRequest);
    }

    @Test
    void refresh() throws Exception {
        User user = CreatorEntity.getUser();

        mockMvc.perform(post("/auth/refresh")
                        .content(asJsonString(user))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful());

    }

    public static String asJsonString(User user) {
        try {
            return new ObjectMapper().writeValueAsString(user);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}