package com.example.bankwebapp.controller;

import com.example.bankwebapp.dto.ClientDto;
import com.example.bankwebapp.entity.enums.Status;
import com.example.bankwebapp.service.interfases.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/auth/clients")
@RequiredArgsConstructor
public class ClientController {

    private final ClientService clientService;

    @GetMapping("/{id}")
    public ClientDto getClientById(@PathVariable("id") UUID id){
        return clientService.getClientById(id);
    }

    @PutMapping("/update")
    public ClientDto updateClientById(@RequestBody ClientDto clientDto){
        return clientService.update(clientDto);
    }
    @PostMapping("/add")
    public ClientDto addClient(@RequestBody ClientDto clientDto){
        return clientService.createClient(clientDto);
    }

    @DeleteMapping("/{id}")
    public void deleteClient(@PathVariable("id") UUID id){
        clientService.deleteClient(id);
    }

    @GetMapping("/status/{status}")
    public List<ClientDto> getAllClientsWhereStatusIs(@PathVariable("status") Status status){
        return clientService.getAllClientsWhereStatusIs(status);
    }

    @GetMapping("/balance_more_than/{sum}")
    public List<ClientDto> getAllClientsWhereBalanceMoreThan(@PathVariable("sum") BigDecimal sum){
        return clientService.getAllClientsWhereBalanceMoreThan(sum);
    }
}