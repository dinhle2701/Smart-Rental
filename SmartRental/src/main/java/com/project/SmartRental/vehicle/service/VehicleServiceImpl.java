package com.project.SmartRental.vehicle.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.project.SmartRental.vehicle.model.Vehicle;
import com.project.SmartRental.vehicle.repository.VehicleRepository;

@Service
public class VehicleServiceImpl implements VehicleService {

    @Autowired
    private VehicleRepository vehicleRepository;

    @Override
    public Page<Vehicle> getAllVehicle(Pageable pageable) {
        try {
            // TODO Auto-generated method stub
            return vehicleRepository.findAll(pageable);
        } catch (Exception e) {
            // TODO: handle exception
            return Page.empty();
        }
    }

    @Override
    public Optional<Vehicle> getVehicleById(Long id) {
        // TODO Auto-generated method stub
        Optional<Vehicle> vehicle = vehicleRepository.findById(id);
        return vehicle;
    }

    @Override
    public Vehicle createVehicle(Vehicle vehicle) {
        // TODO Auto-generated method stub
        Vehicle newVehicle = new Vehicle();
        newVehicle.setVehicleName(vehicle.getVehicleName());
        newVehicle.setVehicleBrand(vehicle.getVehicleBrand());
        newVehicle.setVehicleColor(vehicle.getVehicleColor());
        newVehicle.setVehicleType(vehicle.getVehicleType());
        newVehicle.setVehicleLicensePlate(vehicle.getVehicleLicensePlate());
        return vehicleRepository.save(newVehicle);
    }

    @Override
    public Vehicle updateVehicleById(Long id, Vehicle vehicleUpdate) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateVehicleById'");
    }

    @Override
    public void deleteVehicleById(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteVehicleById'");
    }

}
