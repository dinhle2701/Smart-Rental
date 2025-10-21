package com.project.SmartRental.tenant.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.project.SmartRental.exception.custom.ResourceNotFoundException;
import com.project.SmartRental.tenant.dto.TenantReq;
import com.project.SmartRental.tenant.dto.TenantRes;
import com.project.SmartRental.tenant.model.Tenant;
import com.project.SmartRental.tenant.repository.TenantRepository;

@Service
public class TenantServiceImpl implements TenantService {

    @Autowired
    private TenantRepository tenantRepository;

    @Override
    public Page<TenantRes> getTenants(Pageable pageable) {
        return tenantRepository.findAll(pageable)
                .map(tenant -> TenantRes.builder()
                .id(tenant.getId())
                .tenantName(tenant.getTenantName())
                .address(tenant.getAddress())
                .build());
    }

    @Override
    public TenantRes createTenant(TenantReq tenantReq) {
        Tenant tenant = new Tenant();

        tenant.setTenantName(tenantReq.getTenantName());
        tenant.setAddress(tenant.getAddress());

        Tenant created = tenantRepository.save(tenant);

        return TenantRes.builder()
                .id(created.getId())
                .tenantName(created.getTenantName())
                .build();
    }

    @Override
    public Optional<TenantRes> getById(Long id) {
        // Tenant tenant = tenantRepository.findById(id).orElseThrow(
        //         () -> new ResourceNotFoundException(
        //                 "Not found tenant with id: " + id,
        //                 "/api/v1/tenant" + id
        //         )
        // );
        return tenantRepository.findById(id)
                .map(tenant -> TenantRes.builder()
                .id(tenant.getId())
                .address(tenant.getAddress())
                .tenantName(tenant.getTenantName())
                .build());
    }

    @Override
    public TenantRes updateTenant(Long id, TenantReq tenantReq) {
        Tenant tenant = tenantRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException(
                        "Not found tenant with id: " + id,
                        "/api/v1/tenant" + id
                )
        );

        if (tenantReq.getTenantName() != null
                && !tenantReq.getTenantName().equals(tenantReq.getTenantName())) {
            tenant.setTenantName(tenantReq.getTenantName());
        }

        if (tenantReq.getAddress() != null
                && !tenantReq.getAddress().equals(tenantReq.getAddress())) {
            tenant.setAddress(tenantReq.getAddress());
        }

        Tenant updated = tenantRepository.save(tenant);
        return TenantRes.builder()
                .id(updated.getId())
                .tenantName(updated.getTenantName())
                .address(updated.getAddress())
                .build();
    }

    @Override
    public void deleteTenant(Long id) {
        tenantRepository.deleteById(id);
    }
}
