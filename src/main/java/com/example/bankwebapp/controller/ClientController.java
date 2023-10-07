package com.example.bankwebapp.controller;

import com.example.bankwebapp.dto.ClientDto;
import com.example.bankwebapp.entity.enums.Status;
import com.example.bankwebapp.service.interfases.ClientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
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
    public ClientDto getClientById(@PathVariable("id") UUID id){
        return clientService.getClientById(id);
    }

    @Operation(summary = "Update client")
    @PutMapping("/update")
    public ClientDto updateClient(@RequestBody ClientDto clientDto){
        return clientService.update(clientDto);
    }

    @Operation(summary = "Create client")
    @PostMapping("/add")
    public ClientDto addClient(@RequestBody ClientDto clientDto){
        return clientService.createClient(clientDto);
    }

    @Operation(summary = "Delete client")
    @DeleteMapping("/delete/{id}")
    public void deleteClient(@PathVariable("id") UUID id){
        clientService.deleteClient(id);
    }

    @Operation(summary = "Get all client where status is ...")
    @GetMapping("/status/{status}")
    public List<ClientDto> getAllClientsWhereStatusIs(@PathVariable("status") Status status){
        return clientService.getAllClientsWhereStatusIs(status);
    }

    @Operation(summary = "Get all clients where balance more than ...")
    @GetMapping("/balance_more_than/{sum}")
    public List<ClientDto> getAllClientsWhereBalanceMoreThan(@PathVariable("sum") BigDecimal sum){
        return clientService.getAllClientsWhereBalanceMoreThan(sum);
    }
}