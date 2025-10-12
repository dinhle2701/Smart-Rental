package com.project.SmartRental.vehicle.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "vehicle")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(nullable = true)
    private Long id;

    @Column(name = "vehicleName")
    @Schema(example = "Sirius 115cc", description = "Tên phương tiện")
    private String vehicleName;

    @Schema(example = "Motobike", description = "Loại xe")
    @Column(name = "vehicleType")
    private String vehicleType;

    @Schema(example = "Yamaha", description = "Hãng xe")
    @Column(name = "vehicleBrand")
    private String vehicleBrand;

    @Schema(example = "Dark blue", description = "Màu xe")
    @Column(name = "vehicleColor")
    private String vehicleColor;

    @Schema(example = "81P1-32231", description = "Biển số xe")
    @Column(name = "vehicleLicensePlate", unique = true)
    private String vehicleLicensePlate;

}
