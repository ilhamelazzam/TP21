package com.example.serviceclient.web;
import com.example.serviceclient.entities.Client;
import com.example.serviceclient.services.ClientService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/clients")
public class ClientController {

    private final ClientService service;

    public ClientController(ClientService service) {
        this.service = service;
    }

    @GetMapping
    public List<Client> findAll() {
        return service.listClients();
    }

    @GetMapping("/{id}")
    public Client findById(@PathVariable Long id) {
        return service.getClient(id).orElse(null);
    }

    @PostMapping
    public Client save(@RequestBody Client client) {
        return service.save(client);
    }
}
