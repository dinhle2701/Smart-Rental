package com.project.SmartRental.tenant.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TenantReq {

    private String tenantName;
    private String address;
}
