package com.project.SmartRental.hostel.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HostelReq {

    private String hostelName;
    private String hostelType;
    private String address;
    private String province;
    private String district;
    private Double latitude;
    private Double longtitude;
    private String description;
    private Integer totalRooms;
    private Integer availableRooms;
}
