package com.example.bankwebapp.controller;

import com.example.bankwebapp.dto.AgreementDto;
import com.example.bankwebapp.service.interfaсes.AgreementService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/auth/agreements")
@RequiredArgsConstructor
@Tag(name = "Agreement Controller API")
public class AgreementController {

    private final AgreementService agreementService;

    @Operation(summary = "Get agreement by client id")
    @GetMapping("/client/{client_id}")
    @PreAuthorize("hasAuthority('user:read')")
    public List<AgreementDto> findAgreementsWhereClientIdIs(@PathVariable("client_id") UUID clientId){
        return agreementService.findAgreementsWhereClientIdIs(clientId);
    }
}