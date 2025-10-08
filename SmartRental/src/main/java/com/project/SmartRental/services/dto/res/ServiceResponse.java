package com.project.SmartRental.services.dto.res;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ServiceResponse {

    private Long id;
    private String serviceName;
    private String serviceType;
}
