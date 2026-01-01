package com.example.servicecar.services;

import com.example.servicecar.entities.Client;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriBuilder;

import java.util.function.Function;

@Service
public class ClientApi {
    private final WebClient client;
    private final String clientsBaseUrl;

    public ClientApi(WebClient.Builder builder,
                     @Value("${clients.service-url:http://SERVICE-CLIENT}") String clientsBaseUrl) {
        this.clientsBaseUrl = clientsBaseUrl;
        this.client = builder
                .baseUrl(clientsBaseUrl)
                .build();
    }

    public Client findClientById(Long id) {
        return client.get()
                .uri(clientUri(id))
                .retrieve()
                .bodyToMono(Client.class)
                .block();
    }

    private Function<UriBuilder, java.net.URI> clientUri(Long id) {
        return uriBuilder -> uriBuilder
                .path("/api/clients/{id}")
                .build(id);
    }

    public String getClientsBaseUrl() {
        return clientsBaseUrl;
    }
}
