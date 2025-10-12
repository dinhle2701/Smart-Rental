package com.project.SmartRental.vehicle.service;

import java.util.Optional;

import com.project.SmartRental.exception.custom.ResourceNotFoundException;
import com.project.SmartRental.vehicle.dto.req.VehicleRequest;
import com.project.SmartRental.vehicle.dto.res.VehicleResponse;
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
    public Page<VehicleResponse> getAllVehicle(Pageable pageable) {
        return vehicleRepository.findAll(pageable)
                .map(vehicle -> VehicleResponse.builder()
                        .id(vehicle.getId())
                        .vehicleBrand(vehicle.getVehicleBrand())
                        .vehicleColor(vehicle.getVehicleColor())
                        .vehicleType(vehicle.getVehicleType())
                        .vehicleName(vehicle.getVehicleName())
                        .vehicleLicensePlate(vehicle.getVehicleLicensePlate())
                        .build()
                );
    }

    @Override
    public Optional<VehicleResponse> getVehicleById(Long id) {
        return vehicleRepository.findById(id)
                .map(vehicle -> VehicleResponse.builder()
                        .id(vehicle.getId())
                        .vehicleBrand(vehicle.getVehicleBrand())
                        .vehicleColor(vehicle.getVehicleColor())
                        .vehicleType(vehicle.getVehicleType())
                        .vehicleName(vehicle.getVehicleName())
                        .vehicleLicensePlate(vehicle.getVehicleLicensePlate())
                        .build()
                );
    }

    @Override
    public VehicleResponse createVehicle(VehicleRequest vehicleRequest) {
        Vehicle vehicle = new Vehicle();

        vehicle.setVehicleName(vehicleRequest.getVehicleName());
        vehicle.setVehicleColor(vehicleRequest.getVehicleColor());
        vehicle.setVehicleType(vehicleRequest.getVehicleType());
        vehicle.setVehicleBrand(vehicleRequest.getVehicleBrand());
        vehicle.setVehicleLicensePlate(vehicleRequest.getVehicleLicensePlate());

        Vehicle created = vehicleRepository.save(vehicle);

        return VehicleResponse.builder()
                .id(created.getId())
                .vehicleBrand(created.getVehicleBrand())
                .vehicleColor(created.getVehicleColor())
                .vehicleType(created.getVehicleType())
                .vehicleName(created.getVehicleName())
                .vehicleLicensePlate(created.getVehicleLicensePlate())
                .build();
    }

    @Override
    public VehicleResponse updateVehicleById(Long id, VehicleRequest vehicleRequest) {
        Vehicle vehicle = vehicleRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException(
                        "Account not found with id: " + id,
                        "/api/v1/account/" + id
                )
        );

        // ✅ kiểm tra và cập nhật từng field nếu có thay đổi
        if (vehicleRequest.getVehicleName() != null
                && !vehicleRequest.getVehicleName().equals(vehicle.getVehicleName())) {
            vehicle.setVehicleName(vehicleRequest.getVehicleName());
        }

        if (vehicleRequest.getVehicleBrand() != null
                && !vehicleRequest.getVehicleBrand().equals(vehicle.getVehicleBrand())) {
            vehicle.setVehicleBrand(vehicleRequest.getVehicleBrand());
        }

        if (vehicleRequest.getVehicleColor() != null
                && !vehicleRequest.getVehicleColor().equals(vehicle.getVehicleColor())) {
            vehicle.setVehicleColor(vehicleRequest.getVehicleColor());
        }

        if (vehicleRequest.getVehicleType() != null
                && !vehicleRequest.getVehicleType().equals(vehicle.getVehicleType())) {
            vehicle.setVehicleType(vehicleRequest.getVehicleType());
        }

        if (vehicleRequest.getVehicleLicensePlate() != null
                && !vehicleRequest.getVehicleLicensePlate().equals(vehicle.getVehicleLicensePlate())) {
            vehicle.setVehicleLicensePlate(vehicleRequest.getVehicleLicensePlate());
        }

        Vehicle updated = vehicleRepository.save(vehicle);

        return VehicleResponse.builder()
                .id(updated.getId())
                .vehicleBrand(updated.getVehicleBrand())
                .vehicleColor(updated.getVehicleColor())
                .vehicleType(updated.getVehicleType())
                .vehicleName(updated.getVehicleName())
                .vehicleLicensePlate(updated.getVehicleLicensePlate())
                .build();
    }

    @Override
    public void deleteVehicleById(Long id) {
        vehicleRepository.deleteById(id);
    }

}
