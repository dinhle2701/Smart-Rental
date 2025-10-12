package com.project.SmartRental.vehicle.dto.req;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VehicleRequest {

    private String vehicleName;
    private String vehicleType;
    private String vehicleBrand;
    private String vehicleColor;
    private String vehicleLicensePlate;
}
