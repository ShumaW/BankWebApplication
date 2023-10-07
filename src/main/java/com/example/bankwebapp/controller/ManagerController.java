package com.example.bankwebapp.controller;

import com.example.bankwebapp.dto.ManagerDto;
import com.example.bankwebapp.service.interfases.ManagerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/auth/managers")
@RequiredArgsConstructor
@Tag(name = "Manager Controller API")
public class ManagerController {

    private final ManagerService managerService;

    @Operation(summary = "Get manager by id")
    @GetMapping("/{id}")
    public ManagerDto managerById(@PathVariable("id") UUID id){
        return managerService.getManager(id);
    }
}
