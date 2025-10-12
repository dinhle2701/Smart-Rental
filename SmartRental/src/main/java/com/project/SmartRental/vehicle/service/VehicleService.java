package com.project.SmartRental.vehicle.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.project.SmartRental.vehicle.dto.req.VehicleRequest;
import com.project.SmartRental.vehicle.dto.res.VehicleResponse;

@Service
public interface VehicleService {

    Page<VehicleResponse> getAllVehicle(Pageable pageable);

    Optional<VehicleResponse> getVehicleById(Long id);

    VehicleResponse createVehicle(VehicleRequest vehicleRequest);

    VehicleResponse updateVehicleById(Long id, VehicleRequest vehicleRequest);

    void deleteVehicleById(Long id);
}
