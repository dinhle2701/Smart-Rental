package com.project.SmartRental.tenant.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
public class TenantReq {
    private String tenantName;
    private String address;
}
