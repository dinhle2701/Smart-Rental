package com.project.SmartRental.tenant.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TenantRes {
    private Long id;
    private String tenantName;
    private String address;
}
