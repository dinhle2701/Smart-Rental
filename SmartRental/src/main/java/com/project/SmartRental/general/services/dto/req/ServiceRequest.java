package com.project.SmartRental.general.services.dto.req;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ServiceRequest {
    private String serviceName;
    private String serviceType;
    private Double price;
}
