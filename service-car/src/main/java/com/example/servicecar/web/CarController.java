package com.example.servicecar.web;


import com.example.servicecar.entities.Car;
import com.example.servicecar.services.CarService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/cars")
public class CarController {

    private final CarService service;

    public CarController(CarService service) {
        this.service = service;
    }

    @PostMapping
    public Car save(@RequestBody Car car) {
        return service.save(car);
    }

    @GetMapping
    public List<Car> findAll() {
        return service.findAllWithClients();
    }

    @GetMapping("/byClient/{clientId}")
    public List<Car> findByClient(@PathVariable Long clientId) {
        return service.findByClient(clientId);
    }
}
