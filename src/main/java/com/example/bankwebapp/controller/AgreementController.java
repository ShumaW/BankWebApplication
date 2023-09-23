package com.example.bankwebapp.controller;

import com.example.bankwebapp.service.interfases.AgreementService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth/agreements")
@RequiredArgsConstructor
public class AgreementController {
    private final AgreementService agreementService;

}
