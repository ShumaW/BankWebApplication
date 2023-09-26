package com.example.bankwebapp.controller;

import com.example.bankwebapp.dto.ClientDto;
import com.example.bankwebapp.entity.Client;
import com.example.bankwebapp.service.interfases.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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
    public Client updateClientById(@RequestBody ClientDto clientDto){
        return clientService.update(clientDto);
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
