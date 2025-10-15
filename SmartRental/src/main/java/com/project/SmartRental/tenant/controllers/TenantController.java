package com.project.SmartRental.tenant.controllers;

import com.project.SmartRental.tenant.dto.TenantReq;
import com.project.SmartRental.tenant.dto.TenantRes;
import com.project.SmartRental.tenant.service.TenantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.extensions.Extension;
import io.swagger.v3.oas.annotations.extensions.ExtensionProperty;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.Optional;

@Tag(
        name = "api_tenant",
        description = "API xử lý các nghiệp vụ liên quan đến khách thuê"
)
@RestController
@RequestMapping("/api/v1/tenant")
public class TenantController {
    @Autowired
    private TenantService tenantService;

    @Operation(
            summary = "Get all tenants",
            description = "Lấy danh sách toàn bộ khách thuê",
            extensions = @Extension(properties = @ExtensionProperty(name = "x-order", value = "1"))
    )
    @GetMapping("")
    public ResponseEntity<Page<TenantRes>> getAllTenant(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "50") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir

    ) {
        Sort sort = sortDir.equalsIgnoreCase("asc")
                ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(page, size, sort);
        Page<TenantRes> tenantRes = tenantService.getTenants(pageable);
        return new ResponseEntity<>(tenantRes, HttpStatus.OK);
    }

    @Operation(
            summary = "Get tenant by ID",
            description = "Lấy thông tin chi tiết khách thuê theo ID",
            extensions = @Extension(properties = @ExtensionProperty(name = "x-order", value = "2"))
    )
    @GetMapping("/{id}")
    public ResponseEntity<Optional<TenantRes>> getTenantById(@PathVariable Long id) {
        Optional<TenantRes> tenantRes = tenantService.getById(id);
        return new ResponseEntity<>(tenantRes, HttpStatus.OK);
    }

    @Operation(
            summary = "Create new tenant",
            description = "Tạo mới khách thuê",
            extensions = @Extension(properties = @ExtensionProperty(name = "x-order", value = "3"))
    )
    @PostMapping("")
    public ResponseEntity<TenantRes> createTenant(@RequestBody TenantReq tenantReq) {
        TenantRes tenantRes = tenantService.createTenant(tenantReq);
        return new ResponseEntity<>(tenantRes, HttpStatus.OK);
    }

    @Operation(
            summary = "Update tenant",
            description = "Cập nhật toàn bộ thông tin khách thuê theo ID",
            extensions = @Extension(properties = @ExtensionProperty(name = "x-order", value = "4"))
    )
    @PutMapping("/{id}")
    public ResponseEntity<TenantRes> updateTenant(@PathVariable Long id, @RequestBody TenantReq tenantReq) {
        TenantRes tenantRes = tenantService.updateTenant(id, tenantReq);
        return new ResponseEntity<>(tenantRes, HttpStatus.OK);
    }

    @Operation(
            summary = "Update partial tenant",
            description = "Cập nhật một phần thông tin khách thuê theo ID",
            extensions = @Extension(properties = @ExtensionProperty(name = "x-order", value = "5"))
    )
    @PatchMapping("/{id}")
    public String partiallyUpdateTenant(@PathVariable String id) {
        return "Partially update tenant with ID: " + id;
    }

    @Operation(
            summary = "Delete tenant",
            description = "Xóa khách thuê theo ID",
            extensions = @Extension(properties = @ExtensionProperty(name = "x-order", value = "6"))
    )
    @DeleteMapping("/{id}")
    public void deleteTenant(@PathVariable Long id) {
        tenantService.deleteTenant(id);
    }
}
