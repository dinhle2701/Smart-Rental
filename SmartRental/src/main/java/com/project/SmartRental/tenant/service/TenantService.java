package com.project.SmartRental.tenant.service;

import com.project.SmartRental.tenant.dto.TenantReq;
import com.project.SmartRental.tenant.dto.TenantRes;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface TenantService {
    Page<TenantRes> getTenants(Pageable pageable);

    TenantRes createTenant(TenantReq tenantReq);

    Optional<TenantRes> getById(Long id);

    TenantRes updateTenant(Long id, TenantReq tenantReq);

    void deleteTenant(Long id);
}
