package com.example.bankwebapp.controller;

import com.example.bankwebapp.dto.ManagerDto;
import com.example.bankwebapp.service.interfaсes.ManagerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/auth/managers")
@RequiredArgsConstructor
@Tag(name = "Manager Controller API")
public class ManagerController {

    private final ManagerService managerService;

    @Operation(summary = "Get manager by id")
    @GetMapping("/{id}")
    @SecurityRequirement(name = "JWT")
    @PreAuthorize("hasAuthority('user:write')")
    public ManagerDto managerById(@PathVariable("id") UUID id){
        return managerService.getManager(id);
    }

    @Operation(summary = "Create new manager")
    @PostMapping("/create")
    @SecurityRequirement(name = "JWT")
    @PreAuthorize("hasAuthority('user:write')")
    public ResponseEntity<ManagerDto> createNewManager(@RequestBody ManagerDto managerDto){
        return new ResponseEntity<>(managerService.createManager(managerDto),HttpStatus.CREATED);
    }
}
