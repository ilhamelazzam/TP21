package com.example.serviceclient.services;

import com.example.serviceclient.entities.Client;
import com.example.serviceclient.repositories.ClientRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    private final ClientRepository repository;

    public ClientService(ClientRepository repository) {
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    public List<Client> listClients() {
        return repository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<Client> getClient(Long id) {
        return repository.findById(id);
    }

    @Transactional
    public Client save(Client client) {
        return repository.save(client);
    }
}
