package com.example.bankwebapp.controller;

import com.example.bankwebapp.dto.AgreementDto;
import com.example.bankwebapp.service.interfases.AgreementService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/auth/agreements")
@RequiredArgsConstructor
public class AgreementController {

    private final AgreementService agreementService;

    @GetMapping("/client/{client_id}")
    public List<AgreementDto> findAgreementsWhereClientIdIs(@PathVariable("client_id") UUID clientId){
        return agreementService.findAgreementsWhereClientIdIs(clientId);
    }


}
