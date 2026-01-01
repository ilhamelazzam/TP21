package com.example.servicecar.repositories;

import com.example.servicecar.entities.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CarRepository extends JpaRepository<Car, Long> {
    List<Car> findByClientId(Long clientId);
}