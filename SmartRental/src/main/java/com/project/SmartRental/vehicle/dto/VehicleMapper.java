package com.project.SmartRental.vehicle.dto;

import com.project.SmartRental.vehicle.dto.req.VehicleRequest;
import com.project.SmartRental.vehicle.dto.res.VehicleResponse;
import com.project.SmartRental.vehicle.model.Vehicle;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface VehicleMapper {

    Vehicle toEntity(VehicleRequest request);

    VehicleResponse toResponse(Vehicle entity);
}

