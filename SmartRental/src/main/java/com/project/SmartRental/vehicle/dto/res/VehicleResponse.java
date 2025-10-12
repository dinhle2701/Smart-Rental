package com.project.SmartRental.vehicle.dto.res;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VehicleResponse {
    private Long id;
    private String vehicleName;
    private String vehicleType;
    private String vehicleBrand;
    private String vehicleColor;
    private String vehicleLicensePlate;
}
