package com.project.SmartRental.tenant.controllers;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.extensions.Extension;
import io.swagger.v3.oas.annotations.extensions.ExtensionProperty;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(
        name = "api_tenant",
        description = "API xử lý các nghiệp vụ liên quan đến khách thuê"
)
@RestController
@RequestMapping("/api/v1/tenant")
public class TenantController {

    @Operation(
            summary = "Get all tenants",
            description = "Lấy danh sách toàn bộ khách thuê",
            extensions = @Extension(properties = @ExtensionProperty(name = "x-order", value = "1"))
    )
    @GetMapping("")
    public String getAllTenant() {
        return "Get all tenants";
    }

    @Operation(
            summary = "Get tenant by ID",
            description = "Lấy thông tin chi tiết khách thuê theo ID",
            extensions = @Extension(properties = @ExtensionProperty(name = "x-order", value = "2"))
    )
    @GetMapping("/{id}")
    public String getTenantById(@PathVariable String id) {
        return "Get tenant with ID: " + id;
    }

    @Operation(
            summary = "Create new tenant",
            description = "Tạo mới khách thuê",
            extensions = @Extension(properties = @ExtensionProperty(name = "x-order", value = "3"))
    )
    @PostMapping("")
    public String createTenant() {
        return "Create a new tenant";
    }

    @Operation(
            summary = "Update tenant",
            description = "Cập nhật toàn bộ thông tin khách thuê theo ID",
            extensions = @Extension(properties = @ExtensionProperty(name = "x-order", value = "4"))
    )
    @PutMapping("/{id}")
    public String updateTenant(@PathVariable String id) {
        return "Update tenant with ID: " + id;
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
    public String deleteTenant(@PathVariable String id) {
        return "Delete tenant with ID: " + id;
    }
}
