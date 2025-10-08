package com.project.SmartRental.vehicle.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.project.SmartRental.vehicle.model.Vehicle;

@Service
public interface VehicleService {

    Page<Vehicle> getAllVehicle(Pageable pageable);

    Optional<Vehicle> getVehicleById(Long id);

    Vehicle createVehicle(Vehicle vehicle);

    Vehicle updateVehicleById(Long id, Vehicle vehicleUpdate);

    void deleteVehicleById(Long id);
}
