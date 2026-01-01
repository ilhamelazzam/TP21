package com.example.servicecar.services;

import com.example.servicecar.entities.Car;
import com.example.servicecar.entities.Client;
import com.example.servicecar.repositories.CarRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class CarService {

    private final CarRepository repository;
    private final ClientApi clientApi;

    public CarService(CarRepository repository, ClientApi clientApi) {
        this.repository = repository;
        this.clientApi = clientApi;
    }

    @Transactional
    public Car save(Car car) {
        return repository.save(car);
    }

    @Transactional(readOnly = true)
    public List<Car> findAllWithClients() {
        return enrichWithRemoteClients(repository.findAll());
    }

    @Transactional(readOnly = true)
    public List<Car> findByClient(Long clientId) {
        List<Car> cars = repository.findByClientId(clientId);
        if (cars.isEmpty()) {
            return cars;
        }
        return attachSharedClient(cars, clientId);
    }

    private List<Car> enrichWithRemoteClients(List<Car> cars) {
        return cars.stream()
                .map(addClientDetails())
                .collect(Collectors.toList());
    }

    private Function<Car, Car> addClientDetails() {
        return car -> {
            if (car.hasClientId()) {
                car.setClient(clientApi.findClientById(car.getClientId()));
            }
            return car;
        };
    }

    private List<Car> attachSharedClient(List<Car> cars, Long clientId) {
        Client client = clientApi.findClientById(clientId);
        return cars.stream()
                .peek(car -> car.setClient(client))
                .collect(Collectors.toList());
    }
}
