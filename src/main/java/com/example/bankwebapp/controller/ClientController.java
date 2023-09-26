package com.example.bankwebapp.controller;

import com.example.bankwebapp.dto.ClientDto;
import com.example.bankwebapp.entity.Client;
import com.example.bankwebapp.service.interfases.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/auth/clients")
@RequiredArgsConstructor
public class ClientController {

    private final ClientService clientService;

    @GetMapping("/{id}")
    public Optional<Client> getClientById(@PathVariable("id") UUID id){
        return clientService.getClientById(id);
    }

    @PostMapping("/add")
    public Client addClient(@RequestBody ClientDto clientDto){
        return clientService.createClient(clientDto);
    }

    @DeleteMapping("/{id}")
    public void deleteClient(@PathVariable("id") UUID id){
        clientService.deleteClient(id);
    }
}
