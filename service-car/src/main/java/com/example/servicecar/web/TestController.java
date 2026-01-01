package com.example.servicecar.web;

import com.example.servicecar.services.ClientApi;
import org.springframework.web.bind.annotation.*;
import com.example.servicecar.entities.Client;

@RestController
@RequestMapping("/api/test")
public class TestController {
    private final ClientApi clientApi;

    public TestController(ClientApi clientApi) {
        this.clientApi = clientApi;
    }

    @GetMapping("/client/{id}")
    public Client testClient(@PathVariable Long id) {
        return clientApi.findClientById(id);
    }
}