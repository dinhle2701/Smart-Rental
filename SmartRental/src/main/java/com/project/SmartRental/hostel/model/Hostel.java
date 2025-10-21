package com.project.SmartRental.hostel.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "hostel")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Hostel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    private String hostelName;

    private String hostelType;

    private String address;

    private String province;

    private String district;

    private Double latitude;

    private Double longtitude;

    @Column(length = 1000)
    private String description;

    private int totalRooms;

    private int availableRooms;

    private LocalDateTime createAt;

    private LocalDateTime updateAt;
}
