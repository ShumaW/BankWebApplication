package com.example.bankwebapp.controller;

import com.example.bankwebapp.dto.ClientDto;
import com.example.bankwebapp.entity.enums.Status;
import com.example.bankwebapp.service.interfaсes.ClientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/auth/clients")
@RequiredArgsConstructor
@Tag(name = "Client Controller API")
public class ClientController {

    private final ClientService clientService;

    @Operation(summary = "Get client by id")
    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('user:read')")
    public ClientDto getClientById(@PathVariable("id") UUID id){
        return clientService.getClientById(id);
    }

    @Operation(summary = "Update client")
    @PutMapping("/update")
    @PreAuthorize("hasAuthority('user:write')")
    public ResponseEntity<ClientDto> updateClient(@RequestBody ClientDto clientDto){
        return new ResponseEntity<>(clientService.update(clientDto), HttpStatus.OK);
    }

    @Operation(summary = "Create client")
    @PostMapping("/add")
    @PreAuthorize("hasAuthority('user:write')")
    public ResponseEntity<ClientDto> addClient(@RequestBody ClientDto clientDto){
        return new ResponseEntity<>(clientService.createClient(clientDto), HttpStatus.CREATED);
    }

    @Operation(summary = "Delete client")
    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('user:write')")
    public void deleteClient(@PathVariable("id") UUID id){
        clientService.deleteClient(id);
    }

    @Operation(summary = "Get all client where status is ...")
    @GetMapping("/status/{status}")
    @PreAuthorize("hasAuthority('user:write')")
    public List<ClientDto> getAllClientsWhereStatusIs(@PathVariable("status") Status status){
        return clientService.getAllClientsWhereStatusIs(status);
    }

    @Operation(summary = "Get all clients where balance more than ...")
    @GetMapping("/balance_more_than/{sum}")
    @PreAuthorize("hasAuthority('user:write')")
    public List<ClientDto> getAllClientsWhereBalanceMoreThan(@PathVariable("sum") BigDecimal sum){
        return clientService.getAllClientsWhereBalanceMoreThan(sum);
    }
}